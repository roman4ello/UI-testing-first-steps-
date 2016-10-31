import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ramzes on 31.10.2016.
 */
public class WebElementInstruments {
    private static final String popUPWindow = "//*[@id='aui-flag-container']/div/div";
    private static final String userProfileImage = "//*[@id=\"header-details-user-fullname\"]/span/span/img";
    private static final String logOutWindow = "//*[@id='user-options']/a";
    private WebDriver driver;

    public WebElementInstruments(WebDriver driver) {
        this.driver = driver;

    }

    //short and comfortable form for find element
    public WebElement findElementByXpath(String xPath) {
        return this.driver.findElement(By.xpath(xPath));
    }

    //return true if webElement has appeared
    public boolean webElementIsPresent(String xPath) {
        return waitForWebElementUntilPresenceOfElementLocated(xPath).isEnabled();
    }

    //wait for webElement until that appears( wait during 10 sec)
    public WebElement waitForWebElementUntilPresenceOfElementLocated(String xPath) {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));

    }

    public WebElement waitForWebElementUntilElementToBeClickable(String xPath) {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));

    }

    //checks booleans methods (проверки)
    public boolean popupWindowIsPresent() {
        return webElementIsPresent(popUPWindow);
    }

    public boolean userProfileImageIsPresent() {
        return webElementIsPresent(userProfileImage);
    }

    public boolean isLogOutWindowPresent() {
        boolean isLogOutwindowPresent = webElementIsPresent(logOutWindow);
        this.driver.close();
        System.out.println("Удачно разлогинился и закрыл браузер");
        return isLogOutwindowPresent;
    }

}//class 
