package pl.czak.minimal.dto.api;

public class LoginRequest {
    private String email;
    private String password;
    public LoginRequest(String username, String password) {
        this.email = username;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
