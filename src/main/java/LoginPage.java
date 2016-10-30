import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by ramzes on 27.10.2016.
 */
public class LoginPage {
    private WebDriver driver;
    //    private WebElement webElement;
    private Date date = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
//        System.setProperty("webdriver.gecko.driver", "geckodriver");
    }

//
//    public LoginPage(FirefoxDriver driverFirefox) {
//        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
//        this.driver = new FirefoxDriver();
//    }

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

    public void clickCreateNewIssue() {
        //Search element
        driver.findElement(By.xpath("//*[@id='create_link']")).click();
        // sleep
    }

    public void enterProjectName() {
        WebElement projectNameInput = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='project-field']")));
        projectNameInput.click();
        projectNameInput.sendKeys("QAAutomation2 (QAAUT)");
        projectNameInput.submit();
    }

    public void enterSummary(String issueName) {
        WebElement summary = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='summary']")));
        summary.sendKeys(issueName);
    }

    public void enterReporter(String reporterName) {
        WebElement reporterInputField = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='reporter-field']")));
        reporterInputField.click();
        reporterInputField.sendKeys(reporterName);
    }

    public void enterDescription(String description) {
        driver.findElement(By.xpath("//*[@id='description']")).sendKeys(description);
    }

    public void clickSubmit() {
        driver.findElement(By.xpath("//*[@id='create-issue-submit']")).click();
        // Thread.sleep(2000);
    }

    public void createIssue(String issueName, String reporterName) throws InterruptedException {
        clickCreateNewIssue();
        enterProjectName();
        String fullIssueNameWithDate = "ISSUE  " + issueName + " at: " + dateFormat.format(date);
        enterSummary(fullIssueNameWithDate);
        enterReporter(reporterName);
        enterDescription("Sample description");
        clickSubmit();
    }

    public boolean popupWindowIsPresent() {
        boolean createIssue = false;

        if (driver.findElement(By.xpath("//*[@id='aui-flag-container']/div/div")).isEnabled()) {
            WebElement popupWindow = driver.findElement(By.xpath("//*[@id='aui-flag-container']/div/div"));
//            System.out.println("Создали Issue  = " + fullIssueNamewithDate);
            createIssue = true;
        }

//        System.out.println("You successfully have created new ISSUE by NAME: " + fullIssueNamewithDate);

        return createIssue;
    }

}//class
