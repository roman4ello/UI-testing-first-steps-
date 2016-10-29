import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by ramzes on 27.10.2016.
 */
public class LoginPage {
    private WebDriver driver;
    private WebElement webElement;
    private Date date = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public LoginPage(ChromeDriver driver) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    public LoginPage(FirefoxDriver driverFirefox) {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        this.driver = new FirefoxDriver();
    }

    public String login(String loginName, String password) throws InterruptedException {
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();

        //Search element
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get("http://soft.it-hillel.com.ua:8080/login.jsp");
        this.driver.findElement(By.xpath("//input[@id='login-form-username']")).sendKeys(loginName);
        this.driver.findElement(By.xpath("//input[@id='login-form-password']")).sendKeys(password);
        this.driver.findElement(By.xpath("//input[@id='login-form-submit']")).click();

        while (!this.driver.getTitle().contains("System Dashboard - JIRA")) {
            Thread.sleep(100);
        }
        System.out.println(this.driver.getTitle());

        return this.driver.getTitle();
    }

    public String getTitle() {
        return this.driver.getTitle();
    }


    public long exit() {
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.findElement(By.xpath("//*[@id='header-details-user-fullname']")).click();
        this.driver.findElement(By.xpath("//*[@id='log_out']")).click();
        this.driver.close();
        System.out.println("Удачно разлогинился и закрыл браузер");
        return 0;
    }


    public boolean createIssue(String IssueName, String textForLogin) throws InterruptedException {

        boolean createIssue = false;
        //Search element
        driver.findElement(By.xpath("//*[@id='create_link']")).click();
        Thread.sleep(3000);

        webElement = driver.findElement(By.xpath("//*[@id='project-field']"));
        webElement.click();
        Thread.sleep(2000);
        webElement.sendKeys("QAAutomation2 (QAAUT)");
        webElement.submit();


        Thread.sleep(4000);
        String fullIssueNamewithDate = "ISSUE  " + IssueName + " at: " + dateFormat.format(date);
        webElement = driver.findElement(By.xpath("//*[@id='summary']"));
        webElement.sendKeys(IssueName);
        Thread.sleep(2000);

        webElement = driver.findElement(By.xpath("//*[@id='reporter-field']"));
        webElement.click();
        webElement.sendKeys(textForLogin);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='description']")).sendKeys(fullIssueNamewithDate);
        driver.findElement(By.xpath("//*[@id='create-issue-submit']")).click();

        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(1000L, TimeUnit.MILLISECONDS);

        if (driver.findElement(By.xpath("//*[@id='aui-flag-container']/div/div")).isEnabled()) {
            webElement = driver.findElement(By.xpath("//*[@id='aui-flag-container']/div/div"));
            System.out.println("Создали Issue  = " + fullIssueNamewithDate);
            createIssue = true;
        }

        System.out.println("You successfully have created new ISSUE by NAME: " + fullIssueNamewithDate);

        return createIssue;
    }
}//class
