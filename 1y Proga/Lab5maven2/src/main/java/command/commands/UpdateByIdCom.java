package command.commands;

import command.Receiver;
import command.utility.RouteBuilder;
import command.exceptions.InvalidDataException;

public class UpdateByIdCom implements BasicCommand{

    /**
     * Command 'Update by id'
     * Updates the object by it's id
     */

    Receiver receiver;
    public UpdateByIdCom(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args){
        try{
            long id = Long.parseLong(args[1]);
            receiver.updateById(id , new RouteBuilder().create());
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
