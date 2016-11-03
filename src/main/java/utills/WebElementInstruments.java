package utills;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ramzes on 31.10.2016.
 */
public class WebElementInstruments {

    private WebDriver driver;

    public Date date = new Date();
    public DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


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
        return webElementIsPresent(MyXpath.POP_UP_WINDOW);
    }

    public boolean userProfileImageIsPresent() {
        return webElementIsPresent(MyXpath.USER_PROFILE_IMAGE);
    }

    public boolean isLogOutWindowPresent() {
        boolean isLogOutwindowPresent = webElementIsPresent(MyXpath.LOG_OUT_WINDOW);
        this.driver.close();
        System.out.println("Удачно разлогинился и закрыл браузер");
        return isLogOutwindowPresent;
    }

    public boolean isReporterFieldChanged(String newReporterName) throws InterruptedException {
        Thread.sleep(2000);
        boolean returnValue = false;
        String reporterValue = findElementByXpath(MyXpath.REPORTER_VALUE).getText();
        if (reporterValue.contains(newReporterName)) {
            returnValue = true;
        }

        return returnValue;
    }

    public boolean isPriorityChanged(String newPriotity) throws InterruptedException {
        Thread.sleep(2000);
        boolean returnValue = false;
        String priorityValue = findElementByXpath(MyXpath.PRIORITY_VALUE).getText();
        if (priorityValue.contains(newPriotity)) {
            returnValue = true;
        }
        return returnValue;
    }

    public boolean isNewCommentAdded(String newComment) throws InterruptedException {
        Thread.sleep(2000);
        boolean returnValue = false;
        String activityModuleValue = waitForWebElementUntilPresenceOfElementLocated(MyXpath.ACTIVE_MODULE).getText();
        String sample = "added a comment - Now\n" + newComment;
        if (activityModuleValue.contains(sample)) {
            returnValue = true;
        }

        return returnValue;
    }

    public boolean isIssueTitleUpdated(String newIssueTitle) throws InterruptedException {

        Thread.sleep(2000);
        boolean returnValue = false;
        String issueTitleValue = findElementByXpath(MyXpath.SUMMARY_VALUE).getText();
        System.out.println("SUMMARY_VALUE = " + issueTitleValue);

        String sample = newIssueTitle;
        if (issueTitleValue.contains(sample)) {
            returnValue = true;
        }
        return returnValue;
    }
}//class
