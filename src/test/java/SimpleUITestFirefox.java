import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ramzes on 26.10.2016.
 */
public class SimpleUITestFirefox {


    protected WebDriver driver;

    @Test
    public void simpleTest(){
        System.setProperty("webdriver.chrome.driver", "geckodriver.exe");

        driver = new FirefoxDriver();

        String  eTitle = "Meet Guru99",
                aTitle = "";

        //start browser and go to adres
        driver.get("http://www.guru99.com/");

        //full-open browser window
        driver.manage().window().maximize();

        //get value of page title
        aTitle = driver.getTitle();

        //do inspection
        Assert.assertEquals(aTitle, eTitle);

        //close browser window
        driver.close();

    }


}//class 
