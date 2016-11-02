import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.IssuePage;
import pages.LogOutPage;
import pages.LoginPage;
import utills.WebElementInstruments;

public class JiraTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private LogOutPage logOutPage;
    private WebElementInstruments instruments;
    private IssuePage issuePage;
    private final static String login = "br777roman";
    private final static String password = "br777roman";
    private final static String newReporterLogin = "a.a.piluck";
    private final static String newReporterName = "Artur Pilyuk";
    private final static String issueForUpdate = "QAAUT-1382";
    private final static String newPriority = "Medium";
    private final static String newComment = "First successful commit";
    private final static String newIssueTitle = "This is new title";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        logOutPage = new LogOutPage(driver);
        issuePage = new IssuePage(driver);
        instruments = new WebElementInstruments(driver);
    }

    //    @AfterTest
    public void tearDown() throws InterruptedException {
        logOutPage.logOut();
        Assert.assertEquals(instruments.isLogOutWindowPresent(), true);
    }


    @Test
    public void testLogin() throws InterruptedException {
        loginPage.login(login, password);
        Assert.assertEquals(instruments.userProfileImageIsPresent(), true);
    }


    @Test(dependsOnMethods = "testLogin")
    public void testCreateIssue() throws InterruptedException {
        issuePage.createIssue("multilayer", login);
        Assert.assertEquals(instruments.popupWindowIsPresent(), true);
    }


    @Test(dependsOnMethods = "testLogin")
    public void updateReporter() throws InterruptedException {
        issuePage.updateReporter(issueForUpdate, newReporterLogin);
        Assert.assertEquals(instruments.isReporterFieldChanged(newReporterName), true);
    }


    @Test(dependsOnMethods = "testLogin")
    public void updatePriority() throws InterruptedException {
        issuePage.updatePriority(issueForUpdate, newPriority);
        Assert.assertEquals(instruments.isPriorityChanged(newPriority), true);
    }

    @Test(dependsOnMethods = "testLogin")
    public void addComment() throws InterruptedException {
        issuePage.addComment(issueForUpdate, newComment);
        Assert.assertEquals(instruments.isNewCommentAdded(newComment), true);
    }

    @Test(dependsOnMethods = "testLogin")
    public void updateIssueTitle() throws InterruptedException {
        issuePage.updateIssueTitle(issueForUpdate, newIssueTitle);
        Assert.assertEquals(instruments.isIssueTitleUpdated(newIssueTitle), true);
    }


    @Test(dependsOnMethods = "testLogin")
    public void testDeleteIssue() throws InterruptedException {
    }


}//class
