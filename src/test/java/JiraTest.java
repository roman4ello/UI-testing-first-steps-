import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JiraTest {
    private WebDriver driver;
    private LoginPage homePage;
    private final static String login = "br777roman";
    private final static String password = "br777roman";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        homePage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        homePage.logOut();
        Assert.assertEquals(homePage.isLogOutWindowPresent(), true);
    }


    @Test
    public void testLogin() throws InterruptedException {
        homePage.login(login, password);
        Assert.assertEquals(homePage.userProfileImageIsPresent(), true);
    }



    @Test(dependsOnMethods = "testLogin")
    public void testCreateIssue() throws InterruptedException {
        homePage.createIssue("multilayer", login);
        Assert.assertEquals(homePage.popupWindowIsPresent(), true);
    }

    @Test(dependsOnMethods = "testLogin")
    public void testDeleteIssue() throws InterruptedException {
     }



}//class
