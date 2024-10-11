package command.utility;

import command.Receiver;

import java.util.ArrayList;

public class IdGenerate {

    /**
     * Class for generating unique ids for objects and storing them
     */
    private final Integer min = 10000;
    private final Integer max = 10000000;
    private ArrayList<Integer> IdListing;

    public IdGenerate(){
        this.IdListing = new ArrayList<>();
    }

    public Integer generateId(){
        Integer id = (int) Math.floor(Math.random() * (this.max - this.min + 1)) + min;
        while (IdListing.contains(id)) {
            id = (int) Math.floor(Math.random() * (max - min + 1)) + min;
        }
        IdListing.add(id);
        return id;
    }

    public boolean checkUniqueness(Integer id){
        return !IdListing.contains(id);
    }

    public void add(Integer id){
        IdListing.add(id);
    }

    public void remove(Integer id){
        IdListing.remove(id);
    }

}
