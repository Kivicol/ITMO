package command.utility;

import command.exceptions.*;
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
}
