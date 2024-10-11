package command.utility;

import command.exceptions.InvalidDataException;
import data.Location;

public class LocationBuilderTo extends Builder{

    /**
     * Class for building objects of class "Location" for "to" value of Route object
     */
    public Location create() throws InvalidDataException {
        return new Location(
                buildInt("Location's \"to\" x value (int not null): "), buildLong("Location's \"to\" y value (long not null): "), buildString("Location's \"to\" name (string not null): "));
    }
}
