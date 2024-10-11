package command.utility;

import data.Route;

public interface Readable {
    String readName();
    Integer readCoordinateX();
    Long readCoordinateY();
    Integer readLocationFromX();
    Long readLocationFromY();
    String readLocationFromName();
    Integer readLocationToX();
    Long readLocationToY();
    String readLocationToName();
    Float readDistance();
    String readLogin();
    String readPassword();
}
