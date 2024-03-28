package src.command.Utility;

import src.command.exceptions.*;
import src.data.Location;
import src.data.Route;

import java.time.ZonedDateTime;

public class RouteBuilder extends Builder{

    /**
     * Class for building objects of class "Route"
     */
    public Route create() throws InvalidDataException {
        return new Route(
                buildString("Route name:"),
                new CoordinateBuilder().create(),
                ZonedDateTime.now(),
                new LocationBuilderFrom().create(),
                new LocationBuilderTo().create(),
                buildFloat("Route distance")
        );
    }
}
