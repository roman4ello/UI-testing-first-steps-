import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Created by ramzes on 26.10.2016.
 */
public class SimpleUITestChrome {

    private final String myLogin = "br777roman";
    private final String myPassword = "br777roman";

    //the Date for writting simple description
    private Date date =  new Date();
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    protected WebDriver driver;

    public SimpleUITestChrome( ) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver = new ChromeDriver();

    }

    @Test
    public void createIssueInJiraTest(){

//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        driver = new ChromeDriver();

        String  eTitle = "Log in - JIRA",
                aTitle = "";

        //start browser and go to adres
        driver.get("http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa");

        //full-open browser window
        driver.manage().window().maximize();


        //Search element
        driver.findElement(By.xpath("//*[@id='create_link']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String issueName = "ISSUE at: " + dateFormat.format(date);
        //Search element
        driver.findElement(By.xpath("//*[@id='summary']")).sendKeys(issueName);
        driver.findElement(By.xpath("//*[@id='description']")).sendKeys(issueName);
        driver.findElement(By.xpath("//*[@id='create-issue-submit']")).click();

        if ( driver.findElement(By.xpath("//*[@id='aui-flag-container']/div/div")).isEnabled())
            System.out.println("You successfully have created new ISSUE by NAME: " +issueName);

   }






    @Test
    public void simpleTest(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver = new ChromeDriver();

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


    @BeforeTest
    public void loginInJiraTest(){
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

//        driver = new ChromeDriver();

        String  eTitle = "Log in - JIRA",
                aTitle = "";

        //start browser and go to adres
        driver.get("http://soft.it-hillel.com.ua:8080/login.jsp");

        //full-open browser window
        driver.manage().window().maximize();

        //get value of page title
        aTitle = driver.getTitle();

        //do inspection
        Assert.assertEquals(aTitle, eTitle);


        //Search element
        driver.findElement(By.xpath("//input[@id='login-form-username']")).sendKeys(myLogin);
        driver.findElement(By.xpath("//input[@id='login-form-password']")).sendKeys(myPassword);
        driver.findElement(By.xpath("//input[@id='login-form-submit']")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //проверка входа после выполнения логина
        eTitle = "System Dashboard - JIRA";
        aTitle = driver.getTitle();

//        Assert.assertEquals(aTitle, eTitle);
        //close browser window
//        driver.close();

    }

    @Test
    public void exitFromJiraTest(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //close browser window
        driver.close();

    }




}//class 
