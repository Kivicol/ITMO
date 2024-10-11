package se.ifmo.ru.places;

public enum Places {
    ROOM("комнатa"),
    DOOR("дверь"),
    SHELVES("полки"),
    LADDER( "лестницa"),
    CEILING("потолок");

    private String name;
    Places(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
