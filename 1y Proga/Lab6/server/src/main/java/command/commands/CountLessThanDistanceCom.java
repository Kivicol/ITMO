package command.commands;

import command.Receiver;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class CountLessThanDistanceCom implements BasicCommand {

    /**
     * Command 'Count_less_than_distance'
     * Counts the number of elements whose distance is less than the specified one
     */


    private float distance;

    @Serial
    private final static long serialVersionUID = 2L;

    public CountLessThanDistanceCom(float dist) {
        this.distance = dist;
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.receiver.countLessThanDistance(distance) + "");
    }

    @Override
    public String getName() {
        return "Count_less_than_distance";
    }
}
