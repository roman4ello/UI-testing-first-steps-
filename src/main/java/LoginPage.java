import org.openqa.selenium.WebDriver;

/**
 * Created by ramzes on 27.10.2016.
 */
public class LoginPage {
    private static final String loginURL = "http://soft.it-hillel.com.ua:8080/login.jsp";
    private static final String fieldLoginUserName = "//input[@id='login-form-username']";
    private static final String fieldLoginPassword = "//input[@id='login-form-password']";
    private static final String fieldLoginSubmit = "//input[@id='login-form-submit']";

    private WebDriver driver;
    private WebElementInstruments instruments;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        instruments = new WebElementInstruments(driver);
    }

    public void login(String loginName, String password) throws InterruptedException {
        this.driver.manage().window().maximize();
        this.driver.get(loginURL);
        instruments.findElementByXpath(fieldLoginUserName).sendKeys(loginName);
        instruments.findElementByXpath(fieldLoginPassword).sendKeys(password);
        instruments.findElementByXpath(fieldLoginSubmit).click();
    }




}//class
