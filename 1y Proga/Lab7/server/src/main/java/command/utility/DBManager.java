package command.utility;

import data.Route;

import java.sql.*;
import java.util.HashSet;
import java.util.ResourceBundle;

public class DBManager {
    private DBQueries queries = new DBQueries();

    public static Connection connect() {
        ResourceBundle bundle = ResourceBundle.getBundle("Postgres_en");
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:9876/studs", bundle.getString("login"), bundle.getString("password"));
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error occurred while connecting to database");
            e.printStackTrace();
            return null;
        }
    }

    public Response registerUser(UserData userData) {
        try {
            Connection connection = connect();
            PreparedStatement findUser = connection.prepareStatement(queries.getUser);
            findUser.setString(1, userData.getLogin());
            ResultSet rs = findUser.executeQuery();
            if (!rs.next()) {
                DBPsswrdMngr passwordManager = new DBPsswrdMngr();
                PreparedStatement addUser = connection.prepareStatement(queries.addUser);
                addUser.setString(1, userData.getLogin());
                addUser.setString(2, userData.getPassword());
                addUser.setString(3, passwordManager.hashPassword(userData.getPassword()));
                addUser.execute();
                return new Response(ResponseStatuses.OK, "Registration successful");
            } else {
                return new Response(ResponseStatuses.ERROR, "User already exists, please try again: ");
            }
        } catch (SQLException | NullPointerException e) {
            return new Response(ResponseStatuses.ERROR, "Error occurred while connecting to database, please try again: ");
        }
    }

    public Response authorisationUser(UserData userData) {
        try {
            Connection connection = connect();
            PreparedStatement findUser = connection.prepareStatement(queries.getUser);
            findUser.setString(1, userData.getLogin());
            ResultSet rs = findUser.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals(userData.getPassword()))
                    return new Response(ResponseStatuses.OK, "Authorisation successful");
                return new Response(ResponseStatuses.ERROR, "Password is incorrect, please try again: ");
            } else {
                return new Response(ResponseStatuses.ERROR, "User not found, please try again: ");
            }
        } catch (SQLException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateElement(Route newRoute, String userlogin){
        try{
            Connection connection = connect();
            PreparedStatement update = connection.prepareStatement(queries.updateElement);
            update.setString(1, newRoute.getName());
            update.setInt(2, newRoute.getCoordinateX());
            update.setLong(3, newRoute.getCoordinateY());
            update.setString(4, newRoute.getCreationDate().toString());
            update.setInt(5, newRoute.getLocationFrom().getX());
            update.setLong(6, newRoute.getLocationFrom().getY());
            update.setString(7, newRoute.getLocationFrom().getName());
            update.setInt(8, newRoute.getLocationTo().getX());
            update.setLong(9, newRoute.getLocationTo().getY());
            update.setString(10, newRoute.getLocationTo().getName());
            update.setFloat(11, newRoute.getDistance());
            update.setString(12, userlogin);
            update.setInt(13, newRoute.getId());
            ResultSet resultSet = update.executeQuery();
            return (resultSet.next());
        } catch (SQLException | NullPointerException e){
            ServerLogger.getLogger().warning("Error while connecting to database / making query");
            System.err.println(e);
        }
        return false;
    }

    public int addElement(Route route) {
        try{
            Connection connection = connect();
            PreparedStatement addRoute = connection.prepareStatement(queries.addRoute);
            addRoute.setString(1, route.getName());
            addRoute.setInt(2, route.getCoordinateX());
            addRoute.setLong(3, route.getCoordinateY());
            addRoute.setString(4, route.getCreationDate().toString());
            addRoute.setInt(5, route.getLocationFrom().getX());
            addRoute.setLong(6, route.getLocationFrom().getY());
            addRoute.setString(7, route.getLocationFrom().getName());
            addRoute.setInt(8, route.getLocationTo().getX());
            addRoute.setLong(9, route.getLocationTo().getY());
            addRoute.setString(10, route.getLocationTo().getName());
            addRoute.setFloat(11, route.getDistance());
            addRoute.setString(12, route.getUserlogin());
            ResultSet rs = addRoute.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException | NullPointerException e) {
            ServerLogger.getLogger().warning("Error while connecting to database / making query");
            System.err.println(e);
        }
        return 0;
    }

    public boolean deleteElement(int id, String userlogin){
        try{
            Connection connection = connect();
            PreparedStatement delete = connection.prepareStatement(queries.deleteElement);
            delete.setString(1, userlogin);
            delete.setInt(2, id);
            ResultSet rs = delete.executeQuery();
            return (rs.next());
        } catch (SQLException | NullPointerException e) {
            ServerLogger.getLogger().warning("Error while connecting to database / making query");
        }
        return false;
    }

    public HashSet<Integer> clear(String userlogin){
        HashSet<Integer> ids = new HashSet<>();
        try{
            Connection connection = connect();
            PreparedStatement clear = connection.prepareStatement(queries.clearCollection);
            clear.setString(1, userlogin);
            ResultSet rs = clear.executeQuery();
            while (rs.next()){
                ids.add(rs.getInt("id"));
            }
            return ids;
        } catch (SQLException | NullPointerException e) {
            ServerLogger.getLogger().warning("Error while connecting to database / making query");
        }
        return ids;
    }
}