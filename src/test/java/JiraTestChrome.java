import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ramzes on 27.10.2016.
 */
public class JiraTestChrome {
    private ChromeDriver chromeDriver;
    private LoginPage homePage;
    private String textForLogin = "br777roman";
    private String textForPassw = "br777roman";
    private boolean flagOfresult = false;
    private String result;

    @Test
    public void testLogin() throws InterruptedException {
        homePage = new LoginPage(chromeDriver);
        result = homePage.login(textForLogin, textForPassw);
        Assert.assertTrue(result.contains("System Dashboard - JIRA"));
    }


    @Test(dependsOnMethods = "testLogin")
    public void testCreateIssue() throws InterruptedException {
        flagOfresult = homePage.createIssue("multilayer", textForLogin);
        Assert.assertEquals(flagOfresult, true);
    }

    @Test(dependsOnMethods = "testLogin")
    public void testDeleteIssue() throws InterruptedException {
        flagOfresult = homePage.createIssue("multilayer", textForLogin);
        Assert.assertEquals(flagOfresult, true);
    }




    //    @Test
//    @AfterTest
    public void testExit() throws InterruptedException {
        Assert.assertEquals(homePage.exit(), 0);
    }

}//class
