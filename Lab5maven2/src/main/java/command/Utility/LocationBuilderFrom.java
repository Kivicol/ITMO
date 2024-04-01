package command.Utility;

import command.exceptions.*;
import data.Location;

public class LocationBuilderFrom extends Builder{

    /**
     * Class for building objects of class "Location" for "from" value of Route object
     */
    public Location create() throws InvalidDataException {
        return new Location(
                buildInt("Location's \"from\" x value: "), buildLong("Location's \"from\" y value: "), buildString("Location's \"from\" name: "));
    }
}
