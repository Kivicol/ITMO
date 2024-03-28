package src.command.Utility;

import src.command.exceptions.*;
import src.data.Coordinates;

public class CoordinateBuilder extends Builder{

    /**
     * Class for building objects of class "Coordinates"
     */
    public Coordinates create() throws InvalidDataException {
        return new Coordinates(
                buildInt("Coordinate х value: "), buildLong("Coordinate y value: "));
    }
}
