package pages;

import org.openqa.selenium.WebDriver;
import utills.MyXpath;
import utills.WebElementInstruments;

/**
 * Created by ramzes on 31.10.2016.
 */
public class LogOutPage {

    private WebDriver driver;
    private WebElementInstruments instruments;

    public LogOutPage(WebDriver driver) {
        this.driver = driver;
        instruments = new WebElementInstruments(driver);

    }

    public void logOut() {
        instruments.waitForWebElementUntilElementToBeClickable(MyXpath.headersUserFullName).click();
        instruments.waitForWebElementUntilPresenceOfElementLocated(MyXpath.buttonLogOut).click();
    }
}//class 
