package command.exceptions;

public class NoElementException extends Exception {


    /**
     * Exception for absence of an element with given id
     */

    public NoElementException(long id){
        System.out.println("No element in collection with this id:" + id);
    }
}
