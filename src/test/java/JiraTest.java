import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class JiraTest {
    private WebDriver driver;
    private LoginPage homePage;
    private final static String textForLogin = "br777roman";
    private final static String textForPassw = "br777roman";
    private boolean flagOfresult = false;
    private String result;

    @BeforeTest
    public void setUp() {
//        driver = new FirefoxDriver();
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("Firefox" );
        capability.setPlatform(Platform.LINUX);
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.0.62:4444/wd/hub"), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //driver = WebDriverFactory.getDriver(gridHubUrl, capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        homePage = new LoginPage(driver);
    }

    @Test
    public void testLogin() throws InterruptedException {
        result = homePage.login(textForLogin, textForPassw);
        Assert.assertTrue(result.contains("System Dashboard - JIRA"));
    }


    @Test(dependsOnMethods = "testLogin")
    public void testCreateIssue() throws InterruptedException {
        homePage.createIssue("multilayer", textForLogin);
        Assert.assertEquals(homePage.popupWindowIsPresent(), true);
    }

    @Test(dependsOnMethods = "testLogin")
    public void testDeleteIssue() throws InterruptedException {
        homePage.createIssue("multilayer", textForLogin);
        Assert.assertEquals(homePage.popupWindowIsPresent(), true);
    }

    @AfterTest
    public void testExit() throws InterruptedException {
        driver.close();
        Assert.assertEquals(homePage.exit(), 0);
    }

}//class
