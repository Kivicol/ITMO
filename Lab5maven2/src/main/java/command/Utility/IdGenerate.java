package command.Utility;

import java.util.ArrayList;

public class IdGenerate {

    /**
     * Class for generating unique ids for objects and storing them
     */
    private static final Integer min = 10000;
    private static final Integer max = 10000000;
    private static ArrayList<Integer> IdListing = new ArrayList<>();

    public IdGenerate(){
        IdListing = new ArrayList<>();
    }

    public static Integer generateId(){
        Integer id = (int) Math.floor(Math.random() * (max - min + 1)) + min;
        while (IdListing.contains(id)) {
            id = (int) Math.floor(Math.random() * (max - min + 1)) + min;
        }
        IdListing.add(id);
        return id;
    }

    public static boolean checkUniqueness(Integer id){
        if (IdListing.contains(id)) {
            return false;
        }
        return true;
    }

    public static void add(Integer id){
        IdListing.add(id);
    }

    public static void remove(Integer id){
        IdListing.remove(id);
    }

}
