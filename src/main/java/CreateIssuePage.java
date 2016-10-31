import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ramzes on 31.10.2016.
 */
public class CreateIssuePage {

    private WebDriver driver;
    private WebElementInstruments instruments;
    private Date date = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private static final String buttonCreateLink = "//*[@id='create_link']";
    private static final String projectField = "//*[@id='project-field']";
    private static final String fieldSummary = "//*[@id='summary']";
    private static final String reporterField = "//*[@id='reporter-field']";
    private static final String descriptionField = "//*[@id='description']";
    private static final String buttonCreateIssueSubmit = "//*[@id='create-issue-submit']";

    public CreateIssuePage(WebDriver driver) {
        this.driver = driver;
        instruments = new WebElementInstruments(driver);
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

    //private methods
    //for createIssue
    private void clickCreateNewIssue() {
        instruments.waitForWebElementUntilPresenceOfElementLocated(buttonCreateLink)
                .click();
    }

    private void enterProjectName() {
        WebElement projectNameInput =
                instruments.waitForWebElementUntilPresenceOfElementLocated(projectField);
        projectNameInput.click();
        projectNameInput.sendKeys("QAAutomation2 (QAAUT)");
        projectNameInput.submit();
    }

    private void enterSummary(String issueName) {
        instruments.waitForWebElementUntilElementToBeClickable(fieldSummary).
                sendKeys(issueName);
    }

    private void enterReporter(String reporterName) {
        WebElement reporterInputField =
                instruments.waitForWebElementUntilPresenceOfElementLocated(reporterField);
        reporterInputField.click();
        reporterInputField.sendKeys(reporterName);
    }

    private void enterDescription(String description) {
        instruments.waitForWebElementUntilPresenceOfElementLocated(descriptionField).
                sendKeys(description);
    }

    private void clickSubmit() {
        instruments.findElementByXpath(buttonCreateIssueSubmit).click();
    }


}//class 
