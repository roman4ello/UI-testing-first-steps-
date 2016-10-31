import org.openqa.selenium.WebDriver;

/**
 * Created by ramzes on 31.10.2016.
 */
public class LogOutPage {
    private static final String headersUserFullName = "//*[@id='header-details-user-fullname']";
    private static final String buttonLogOut = "//*[@id='log_out']";

    private WebDriver driver;
    private WebElementInstruments instruments;

    public LogOutPage(WebDriver driver) {
        this.driver = driver;
        instruments = new WebElementInstruments(driver);

    }

    public void logOut() {
        instruments.waitForWebElementUntilElementToBeClickable(headersUserFullName).click();
        instruments.waitForWebElementUntilPresenceOfElementLocated(buttonLogOut).click();
    }
}//class 
