import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JiraTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private LogOutPage logOutPage;
    private WebElementInstruments instruments;
    private CreateIssuePage createIssuePage;
    private final static String login = "br777roman";
    private final static String password = "br777roman";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        logOutPage = new LogOutPage(driver);
        createIssuePage = new CreateIssuePage(driver);
        instruments = new WebElementInstruments(driver);
    }

    @AfterTest
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
        createIssuePage.createIssue("multilayer", login);
        Assert.assertEquals(instruments.popupWindowIsPresent(), true);
    }

    @Test(dependsOnMethods = "testLogin")
    public void testDeleteIssue() throws InterruptedException {
    }


}//class
