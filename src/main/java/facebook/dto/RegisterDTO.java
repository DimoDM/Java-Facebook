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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordTest(String passwordTest) {
        this.passwordTest = passwordTest;
    }
}
