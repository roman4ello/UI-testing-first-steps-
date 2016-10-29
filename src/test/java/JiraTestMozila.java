import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ramzes on 29.10.2016.
 */
public class JiraTestMozila {
    private FirefoxDriver firefoxDriver;
    private LoginPage homePage;
    private String result;
    private String textForLogin = "br777roman";
    private String textForPassw = "br777roman";
    private boolean flagOfresult = false;

    //Этот тетст еще работает с перебоями

    @Test
    public void testLogin() throws InterruptedException {
        homePage = new LoginPage(firefoxDriver);
        result = homePage.login(textForLogin, textForPassw);
        Assert.assertTrue(result.contains("System Dashboard - JIRA"));
    }

    @Test(dependsOnMethods = "testLogin")
    public void testCreateIssue() throws InterruptedException {
//        result = homePage.createIssue("multilayer",textForLogin);
//        Assert.assertTrue(result.getTitle().contains("VRC-63"));
    }

}//class 
