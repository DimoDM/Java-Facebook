package facebook.dto;

public class RegisterDTO {

    private String username;
    private String email;
    private String phone;
    private String password;
    private String passwordTest;

    public RegisterDTO() {}

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordTest() {
        return passwordTest;
    }
}
