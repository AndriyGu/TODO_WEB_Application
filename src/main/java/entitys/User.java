package entitys;

public class User {
    int id;
    String login;
    String pass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String password) {
        this.pass = pass;
    }
}