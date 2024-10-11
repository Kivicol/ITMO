package command.utility;

import command.exceptions.*;
import data.Location;

public class LocationBuilderFrom extends Builder{

    /**
     * Class for building objects of class "Location" for "from" value of Route object
     */
    public Location create() throws InvalidDataException {
        return new Location(
                buildInt("Location's \"from\" x value (int not null): "), buildLong("Location's \"from\" y value (long not null): "), buildString("Location's \"from\" name (string not null): "));
    }
}
