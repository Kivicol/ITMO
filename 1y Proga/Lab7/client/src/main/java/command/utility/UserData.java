package command.utility;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UserData implements Serializable {
    @Serial
    private final static long serialVersionUID = 27L;
    private String login;

    private String password;
    private  boolean isExists;

    public UserData(String login, String password, boolean status) {
        this.login = login;
        this.password = password;
        this.isExists = status;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData ud = (UserData) o;
        return Objects.equals(login, ud.login) && Objects.equals(password, ud.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
