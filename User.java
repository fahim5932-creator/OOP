package Lab7;

public abstract class User {
    protected String name;
    protected String email;
    protected String password;
    protected String id;
    protected String userType;

    public User(String id, String name, String email, String password, String userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
        this.userType = userType;
    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}