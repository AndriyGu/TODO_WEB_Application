package accounts;


public class UserProfile {
    private final int id;
    private final String login;
    private final String pass;

    public UserProfile(int id, String login, String pass) {
        this.id = id;
        this.login = login;
        this.pass = pass;
    }



    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public int getId() { return id;  }
}
