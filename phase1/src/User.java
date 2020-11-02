public class User {
    String name;
    String username;
    String password;

    enum UserType { ATTENDEE, ORGANIZER, SPEAKER}
    UserType userType;

    public User(String name, String username, String password, UserType userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
}
