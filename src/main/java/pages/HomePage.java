package pages;

import base.UIActions;
import org.openqa.selenium.By;
import utility.LOG;

public class HomePage extends UIActions {
    private String url = "https://trello.com";
    // ---- Elements -----  //
    private By page_banner = css("h1");
    private By page_banner_img = xpath("//section[@id='hero']//img");
    private By login_link = xpath("//a[text()='Log in']");
    private By signup_link = xpath("//a[text()='Sign up']");


    // ---- User Actions ---- //
    public void open() {
        LOG.step("User is directed to the home page");
        gotoSite(url);
    }

    public boolean verifyPage() {
        highlight(page_banner_img);
        LOG.screenshot("Home page is displayed");
        return findElement(page_banner).isDisplayed();
    }

    public void visitLoginPage() {
        click(login_link);
        LOG.step("User is navigating to the login page");
    }

    public void visitSignupPage() {
        click(signup_link);
    }
}
