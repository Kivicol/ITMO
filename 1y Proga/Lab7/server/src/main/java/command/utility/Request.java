package command.utility;

import command.commands.BasicCommand;
import data.Route;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 21L;
    private BasicCommand command;
    private String args = "";
    private Route route = null;
    private UserData userdata;
    public Request(BasicCommand command){
        this.command = command;
        this.userdata = command.getUser();
        this.args = command.getIntArgument().toString();
        this.route = command.getRoute();
    }
    public boolean isEmpty(){
        return command == null && args.isEmpty() && route == null;
    }
    public BasicCommand getCommand(){
        return command;
    }
    public String getArgs() {
        return args;
    }
    public Route getRoute() {
        return route;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Request request)) return false;
        return Objects.equals(command, request.command) && Objects.equals(args, request.args) && Objects.equals(route, request.route);
    }
    @Override
    public int hashCode(){
        return Objects.hash(command, args, route);
    }
    @Override
    public String toString(){
        return "Request[" + command.getName() +
                (args.isEmpty()
                        ? ""
                        : ", " + args) +
                ((route == null)
                        ? "]"
                        : ", " + route + "]");
    }
}