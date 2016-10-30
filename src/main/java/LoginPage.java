import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ramzes on 27.10.2016.
 */
public class LoginPage {
    private WebDriver driver;
    private Date date = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private static final String loginURL = "http://soft.it-hillel.com.ua:8080/login.jsp";
    private static final String fieldLoginUserName = "//input[@id='login-form-username']";
    private static final String fieldLoginPassword = "//input[@id='login-form-password']";
    private static final String fieldLoginSubmit = "//input[@id='login-form-submit']";
    private static final String headersUserFullName = "//*[@id='header-details-user-fullname']";
    private static final String buttonLogOut = "//*[@id='log_out']";
    private static final String buttonCreateLink = "//*[@id='create_link']";
    private static final String projectField = "//*[@id='project-field']";
    private static final String fieldSummary = "//*[@id='summary']";
    private static final String reporterField = "//*[@id='reporter-field']";
    private static final String descriptionField = "//*[@id='description']";
    private static final String buttonCreateIssueSubmit = "//*[@id='create-issue-submit']";
    private static final String popUPWindow = "//*[@id='aui-flag-container']/div/div";
    private static final String userProfileImage = "//*[@id=\"header-details-user-fullname\"]/span/span/img";
    private static final String logOutWindow = "//*[@id='user-options']/a";


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String loginName, String password) throws InterruptedException {
        this.driver.manage().window().maximize();
        this.driver.get(loginURL);
        findElementByXpath(fieldLoginUserName).sendKeys(loginName);
        findElementByXpath(fieldLoginPassword).sendKeys(password);
        findElementByXpath(fieldLoginSubmit).click();

    }

    public void logOut() {
        waitForWebElementUntilElementToBeClickable(headersUserFullName).click();
        waitForWebElementUntilPresenceOfElementLocated(buttonLogOut).click();
    }

    public void createIssue(String issueName, String reporterName) throws InterruptedException {
        clickCreateNewIssue();
        enterProjectName();
        String fullIssueNameWithDate = "ISSUE  " + issueName + " at: " + dateFormat.format(date);
        enterSummary(fullIssueNameWithDate);
        enterReporter(reporterName);
        enterDescription("Sample description");
        clickSubmit();
    }


    //--------------------------------------------------------------------------------------------
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

    //private methods
    //for createIssue
    private void clickCreateNewIssue() {
        waitForWebElementUntilPresenceOfElementLocated(buttonCreateLink)
                .click();
    }

    private void enterProjectName() {
        WebElement projectNameInput =
                waitForWebElementUntilPresenceOfElementLocated(projectField);
        projectNameInput.click();
        projectNameInput.sendKeys("QAAutomation2 (QAAUT)");
        projectNameInput.submit();
    }

    private void enterSummary(String issueName) {
        waitForWebElementUntilElementToBeClickable(fieldSummary).
                sendKeys(issueName);
    }

    private void enterReporter(String reporterName) {
        WebElement reporterInputField =
                waitForWebElementUntilPresenceOfElementLocated(reporterField);
        reporterInputField.click();
        reporterInputField.sendKeys(reporterName);
    }

    private void enterDescription(String description) {
        waitForWebElementUntilPresenceOfElementLocated(descriptionField).
                sendKeys(description);
    }

    private void clickSubmit() {
        findElementByXpath(buttonCreateIssueSubmit).click();
    }


    //short and comfortable form for find element
    private WebElement findElementByXpath(String xPath) {
        return this.driver.findElement(By.xpath(xPath));
    }

    //return true if webElement has appeared
    private boolean webElementIsPresent(String xPath) {
        return waitForWebElementUntilPresenceOfElementLocated(xPath).isEnabled();
    }

    //wait for webElement until that appears( wait during 10 sec)
    private WebElement waitForWebElementUntilPresenceOfElementLocated(String xPath) {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));

    }

    private WebElement waitForWebElementUntilElementToBeClickable(String xPath) {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));

    }

}//class
