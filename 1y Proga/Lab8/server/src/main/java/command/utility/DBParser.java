package command.utility;

import command.CollectionManager;
import data.Coordinates;
import data.Location;
import data.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.LinkedList;

public class DBParser {
    private DBQueries queries = new DBQueries();
    private final Connection connection = DBManager.connect();

    public void save(){
        try{
            PreparedStatement deleteAll = connection.prepareStatement(queries.deleteAll);
            deleteAll.execute();
            LinkedList<Route> routes = CollectionManager.getTable();
            for (Route route : routes) {
                PreparedStatement addRoute = connection.prepareStatement(queries.addSpecificRoute);
                addRoute.setInt(1, route.getId());
                addRoute.setString(2, route.getName());
                addRoute.setInt(3, route.getCoordinateX());
                addRoute.setLong(4, route.getCoordinateY());
                addRoute.setString(5, route.getCreationDate().toString());
                addRoute.setInt(6, route.getLocationFrom().getX());
                addRoute.setLong(7, route.getLocationFrom().getY());
                addRoute.setString(8, route.getLocationFrom().getName());
                addRoute.setInt(9, route.getLocationTo().getX());
                addRoute.setLong(10, route.getLocationTo().getY());
                addRoute.setString(11, route.getLocationTo().getName());
                addRoute.setFloat(12, route.getDistance());
                addRoute.setString(13, route.getUserlogin());
                addRoute.executeQuery();
            }
            ServerLogger.getLogger().info("Table was successfully saved");
        } catch (SQLException | NullPointerException e) {
            ServerLogger.getLogger().warning("Error while connecting to database / making query, table was not saved");
        }
    }

    public LinkedList<Route> load(){
        LinkedList<Route> routes = new LinkedList<>();
        try{
            assert connection != null;
            PreparedStatement getAll = connection.prepareStatement(queries.getAll);
            ResultSet rs = getAll.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int coordinateX = rs.getInt("coordinate_x");
                long coordinateY = rs.getLong("coordinate_y");
                String creationDate = rs.getString("creation_date");
                int fromX = rs.getInt("location_from_x");
                long fromY = rs.getLong("location_from_y");
                String fromName = rs.getString("location_from_name");
                int toX = rs.getInt("location_to_x");
                long toY = rs.getLong("location_to_y");
                String toName = rs.getString("location_to_name");
                float distance = rs.getFloat("distance");
                String userlogin = rs.getString("user_login");
                new Coordinates(coordinateX, coordinateY);
                Route route = new Route(id, name, new Coordinates(coordinateX, coordinateY), ZonedDateTime.parse(creationDate), new Location(fromX, fromY, fromName), new Location(toX, toY, toName), distance, userlogin);
                routes.add(route);
            }
            ServerLogger.getLogger().info("Table was successfully loaded");
        } catch (SQLException | NullPointerException e) {
            ServerLogger.getLogger().warning("Error while connecting to database / making query, table was not loaded");
        }
        return routes;
    }

}
