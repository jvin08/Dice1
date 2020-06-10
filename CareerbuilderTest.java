import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class CareerbuilderTest {
    private FirefoxDriver driver;
    private final static String CAREER_BUILDER_PAGE = "https://www.careerbuilder.com/";
    private final static String CAREER_BUILDER_PROFILE = "https://www.careerbuilder.com/myprofile";
    private WebDriverWait webDriverWait;

    @BeforeSuite
    public void testSuiteSetup(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
    }

    /*
     * 1.Open Dice.com page profile
     * 2.Login into your account
     * 3.Click Edit Profile
     * 4.Switch Willing to Relocate
     * 5.Click Save
     * 6.Switch Willing to Relocate
     * 7.Click Save
     * 8.Wait for Edit button
     * 9.Close browser
     * */

    @BeforeMethod
    public void startTestMethod() {
        driver = new FirefoxDriver();
    }

    @AfterMethod
    public void finishTestMethod() {
        //driver.quit();
    }

    @Parameters({ "ParamKey1" })
    @Test
    public void test0001() {
        String Email = "ivan8qa@gmail.com";
        String Password = "jD3$_wL0o";

        openCareerBuilderPage();
        loginIntoAccount(Email, Password);
        goToProfile();
//        switchToggleAndSave();
//        clickEditProfile();
//        switchToggleAndSave();
    }

    private void goToProfile() {
        Actions action = new Actions(driver);
        String xPathSignInHover = "//*[@class, 'menu-links-right']";
        String xPathMyProfile = "//a[contains(@id, 'my-profile-link')]";

        By signInElement = By.xpath(xPathSignInHover);
        waitForElement(signInElement);

        WebElement hoverSignIn = driver.findElement(By.xpath(xPathSignInHover));
        //WebElement goToProfile = driver.findElement(By.xpath(xPathMyProfile));
        action.moveToElement(hoverSignIn);// new comment added

//        WebElement clickSignIn = driver.findElement(By.xpath(xPathMyProfile));
//        clickSignIn.click();



    }

    private WebElement waitForElement(By expectedElement) {
        webDriverWait = new WebDriverWait(driver, 10);
        WebElement foundElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(expectedElement));
        return foundElement;
    }

    private void loginIntoAccount(String Email, String Password) {
        String xPathButtonSignIn = "//a[contains(@id, 'signin-link')]";
        WebElement goToSignInPage = driver.findElement(By.xpath(xPathButtonSignIn));
        goToSignInPage.click();

        By emailFieldElement = By.xpath("//input[contains(@id, 'cbsys_login_email')]");
        waitForElement(emailFieldElement);

        String xPathPassword = "//input[contains(@id, 'cbsys_login_password')]";
        String xPathSignIn = "//input[contains(@type, 'submit')]";
        WebElement emailInput = driver.findElement(emailFieldElement);
        emailInput.sendKeys(Email);
        WebElement passwordInput = driver.findElement(By.xpath(xPathPassword));
        passwordInput.sendKeys(Password);
        WebElement clickSignIn = driver.findElement(By.xpath(xPathSignIn));
        clickSignIn.click();
    }

    private void openCareerBuilderPage() {
        driver.get(CAREER_BUILDER_PAGE);
    }


}
