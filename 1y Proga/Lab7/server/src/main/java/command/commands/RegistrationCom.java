package command.commands;

import command.utility.DBManager;
import command.utility.Response;
import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class RegistrationCom implements BasicCommand{
    @Serial
    public static final long serialVersionUID = 16L;
    private UserData userdata;
    public RegistrationCom(UserData userdata) {
        this.userdata = userdata;
    }
    @Override
    public Response execute() {
        DBManager dbManager = new DBManager();
        if (userdata.isExists()) return dbManager.authorisationUser(userdata);
        return dbManager.registerUser(userdata);
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Integer getIntArgument() {
        return 0;
    }

    @Override
    public Route getRoute() {
        return null;
    }
}
