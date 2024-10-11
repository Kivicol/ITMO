package command.utility;

import command.exceptions.*;
import data.Coordinates;

public class CoordinateBuilder extends Builder{

    /**
     * Class for building objects of class "Coordinates"
     */
    public Coordinates create() throws InvalidDataException {
        return new Coordinates(
                buildInt("Coordinate \"x\" value (int not null): "), buildLong("Coordinate \"y\" value (long not null): "));
    }
}
