package command.commands;

import command.CollectionManager;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class CountLessThanDistanceCom implements BasicCommand {

    /**
     * Command 'Count_less_than_distance'
     * Counts the number of elements whose distance is less than the specified one
     */


    private float distance;

    @Serial
    private final static long serialVersionUID = 2L;
    private UserData userdata;

    public CountLessThanDistanceCom(float dist) {
        this.distance = dist;
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.collectionManager.countLessThanDistance(distance) + "");
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "Count_less_than_distance";
    }

    @Override
    public Integer getIntArgument() {
        return Integer.parseInt(String.valueOf(distance));
    }

    @Override
    public Route getRoute() {
        return null;
    }
}
