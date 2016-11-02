package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utills.MyXpath;
import utills.WebElementInstruments;

/**
 * Created by ramzes on 31.10.2016.
 */
public class IssuePage {

    private WebDriver driver;
    private WebElementInstruments instruments;


    public IssuePage(WebDriver driver) {
        this.driver = driver;
        instruments = new WebElementInstruments(driver);
    }

    public void createIssue(String issueName, String reporterName) throws InterruptedException {
        clickCreateNewIssue();
        enterProjectName();
        String fullIssueNameWithDate = "ISSUE  " + issueName + " at: " + instruments.dateFormat.format(instruments.date);
        enterSummary(fullIssueNameWithDate);
        enterReporter(reporterName);
        enterDescription("Sample description");
        clickSubmit();
    }

    //private methods
    //for createIssue
    private void clickCreateNewIssue() {
        instruments.waitForWebElementUntilPresenceOfElementLocated(MyXpath.buttonCreateLink)
                .click();
    }

    private void enterProjectName() {
        WebElement projectNameInput =
                instruments.waitForWebElementUntilPresenceOfElementLocated(MyXpath.projectField);
        projectNameInput.click();
        projectNameInput.sendKeys("QAAutomation2 (QAAUT)");
        projectNameInput.submit();
    }

    private void enterSummary(String issueName) {
        instruments.waitForWebElementUntilElementToBeClickable(MyXpath.summaryField).
                sendKeys(issueName);
    }

    private void enterReporter(String reporterName) {
        WebElement reporterInputField =
                instruments.waitForWebElementUntilPresenceOfElementLocated(MyXpath.reporterField);
        reporterInputField.click();
        reporterInputField.sendKeys(reporterName);
    }

    private void enterDescription(String description) {
        instruments.waitForWebElementUntilPresenceOfElementLocated(MyXpath.descriptionField).
                sendKeys(description);
    }

    private void clickSubmit() {
        instruments.findElementByXpath(MyXpath.buttonCreateIssueSubmit).click();
    }


    public void updateReporter(String issueForUpdate, String newReporter) throws InterruptedException {
        findIssue(issueForUpdate);
        setNewReporter(newReporter);
    }

    public void updatePriority(String issueForUpdate, String newPriority) {
        findIssue(issueForUpdate);
        setNewPriority(newPriority);
    }


    public void updateIssueTitle(String issueForUpdate, String newTitle) throws InterruptedException {
        findIssue(issueForUpdate);
        setNewIssueTitle(newTitle);
    }

    private void setNewIssueTitle(String newTitle) throws InterruptedException {

        WebElement issueTitleElement = instruments.findElementByXpath(MyXpath.summaryValue);
        issueTitleElement.click();

        Thread.sleep(3000);
        issueTitleElement = instruments.findElementByXpath(MyXpath.summaryField);

        issueTitleElement.sendKeys(Keys.DELETE);
        issueTitleElement.sendKeys(newTitle);
        issueTitleElement.submit();

    }

    public void addComment(String issueForUpdate, String newComment) {
        findIssue(issueForUpdate);
        addNewComment(newComment);

    }


    private void addNewComment(String newComment) {

        instruments.findElementByXpath(MyXpath.commentIssue).click();
        instruments.waitForWebElementUntilPresenceOfElementLocated(MyXpath.commentField).sendKeys(newComment);
        instruments.findElementByXpath(MyXpath.buttonAddCommentSubmit).click();

    }

    private void setNewPriority(String newPriority) {

        instruments.findElementByXpath(MyXpath.priorityValue).click();
        WebElement priorityFieldElement =
                instruments.waitForWebElementUntilPresenceOfElementLocated(MyXpath.priorityField);
        priorityFieldElement.sendKeys(Keys.DELETE);
        priorityFieldElement.sendKeys(newPriority);
        priorityFieldElement.submit();
    }

    private boolean findIssue(String issueForUpdate) {
        WebElement webElementSearch = instruments.findElementByXpath(MyXpath.searchField);
        webElementSearch.sendKeys(issueForUpdate);
        webElementSearch.submit();

        return instruments.waitForWebElementUntilPresenceOfElementLocated(MyXpath.keyValueField)
                .getAttribute("data-issue-key").equals(issueForUpdate);

    }

    private void setNewReporter(String newReporter) {

        instruments.findElementByXpath(MyXpath.reporterValue).click();


        WebElement reporterFieldElement =
                instruments.waitForWebElementUntilPresenceOfElementLocated(MyXpath.reporterField);
        reporterFieldElement.sendKeys(Keys.DELETE);
        reporterFieldElement.sendKeys(newReporter);
        reporterFieldElement.submit();
    }

}//class