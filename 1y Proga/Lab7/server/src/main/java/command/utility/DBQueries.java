package command.utility;

public class DBQueries {

    String getUser = "SELECT * FROM users WHERE login = ?; ";

    String getPassword = "SELECT hash FROM users WHERE login = ? ;";

    String addUser = "INSERT INTO users (login, password, hash) VALUES (?, ?, ?);";

    String addRoute = """
            INSERT INTO routes (name, coordinate_x, coordinate_y, creation_date, location_from_x, location_from_y, location_from_name, location_to_x, location_to_y, location_to_name, distance, user_login) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING id;
            """;

    String addSpecificRoute = """
            INSERT INTO routes (id, name, coordinate_x, coordinate_y, creation_date, location_from_x, location_from_y, location_from_name, location_to_x, location_to_y, location_to_name, distance, user_login) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING id;
            """;

    String clearCollection = "DELETE FROM routes WHERE (user_login = ?) RETURNING id;";

    String deleteElement = "DELETE FROM routes WHERE (user_login = ?) AND (id = ?) RETURNING id;";

    String updateElement = """
            UPDATE routes
            SET (name, coordinate_x, coordinate_y, creation_date, location_from_x, location_from_y, location_from_name, location_to_x, location_to_y, location_to_name, distance) = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            WHERE (user_login = ?) AND (id = ?) RETURNING id;
            """;

    String getCollection = "SELECT * FROM routes WHERE user_login = ?;";

    String getAll = "SELECT * FROM routes;";

    String deleteAll = "TRUNCATE routes;";
}
