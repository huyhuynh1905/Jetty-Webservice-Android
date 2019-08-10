package android.huyhuynh.jettyloginapp;

/**
 * Created by Huy Huynh on 08/11/19.
 */
public class LoginRequest {
    private String email;
    private String pass;

    public LoginRequest() {
        super();
    }
    public LoginRequest(String email, String pass) {
        super();
        this.email = email;
        this.pass = pass;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}
