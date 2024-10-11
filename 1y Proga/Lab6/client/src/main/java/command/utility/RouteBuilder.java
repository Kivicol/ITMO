package command.utility;

import command.exceptions.*;
import data.Coordinates;
import data.Location;
import data.Route;
import data.Validation;
import java.time.ZonedDateTime;

public class RouteBuilder extends Builder {

    /**
     * Class for building objects of class "Route"
     */
    public Route create() throws InvalidDataException {
        return new Route(
                buildString("Route name (not null):"),
                new CoordinateBuilder().create(),
                ZonedDateTime.now(),
                new LocationBuilderFrom().create(),
                new LocationBuilderTo().create(),
                buildFloat("Route distance (>1):", 1.0f)
        );
    }
    public Route create(String[] args) throws InvalidDataException {
        return new Route(
                args[0],
                new Coordinates(Integer.parseInt(args[1]), Long.parseLong(args[2])),
                ZonedDateTime.now(),
                new Location(Integer.parseInt(args[3]), Long.parseLong(args[4]), args[5]),
                new Location(Integer.parseInt(args[6]), Long.parseLong(args[7]), args[8]),
                Float.parseFloat(args[9])
        );
    }
}
