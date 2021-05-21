package pages;

import base.UIActions;
import org.openqa.selenium.By;
import utility.LOG;

public class LoginPage extends UIActions {
    // ---- Elements -----  //
    private By username_input = css("input#user");
    private By password_input = css("input#password");
    private By login_bttn = css("input#login");
    private By error_mssg = css("div#error > .error-message");

    // ---- User Actions ---- //
    public void enterCredentials(String user, String pass) {
        LOG.step("user is entering credentials");
        write(username_input, user);
        write(password_input, pass);
        click(login_bttn);
    }

    public String getErrorMessage() {
        LOG.screenshot_err("Error message is displayed to the user");
        return findElement(error_mssg).getText();
    }
}
