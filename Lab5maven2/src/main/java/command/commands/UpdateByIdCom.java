package command.commands;

import command.Receiver;
import command.Utility.RouteBuilder;
import command.exceptions.InvalidDataException;

public class UpdateByIdCom implements BasicCommand{

    /**
     * Command 'Update by id'
     * Updates the object by it's id
     */
    @Override
    public void execute(String[] args){
        try{
            long id = Long.parseLong(args[1]);
            Receiver.updateById(id , new RouteBuilder().create());
            System.out.println("Object successfully added");
        } catch (InvalidDataException a){
            System.out.println("Data provided is wrong, try again");
        }
    }


    @Override
    public String getName() {
        return "Update (id)";
    }

    @Override
    public String getDescription() {
        return "update the object's id";
    }
}
