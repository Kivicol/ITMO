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
    private UserData userData;
    public Request(BasicCommand command){
        this.command = command;
        this.userData = command.getUser();
        this.args = command.getInt() == null ? "" : command.getInt().toString();
        this.route = command.getRoute();
    }
    public Request(ResponseStatuses responseStatus, BasicCommand commandName, Route route){
        this.command = commandName;
    }
    public Request(BasicCommand commandName, String args){
        this.command = commandName;
        this.args = args.trim();
    }
    public Request(BasicCommand commandName, Route route){
        this.command = commandName;
        this.route = route;
    }
    public Request(BasicCommand commandName, String args, Route route){
        this.command = commandName;
        this.args = args.trim();
        this.route = route;
    }
    public boolean isEmpty(){
        return command == null && args.isEmpty() && route == null;
    }
    public BasicCommand getCommandName(){
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
        return "Request[" + command.toString() +
                (args.isEmpty()
                        ? ""
                        : ", " + args) +
                ((route == null)
                        ? "]"
                        : ", " + route + "]");
    }
}

