package by.aleksey.project.ibankingapp.model;

public class Account {

    private int id;
    private String login;
    private String password;
    private String userName;

    public Account(int id, String login, String password, String userName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
