import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class DiceTest {
    private FirefoxDriver driver;
    private final static String DICE_LOGIN_PAGE = "https://www.dice.com/dashboard";
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
        driver.quit();
    }

    @Parameters({ "paramKey1" })
    @Test
    public void test0001(String Email) {
        String Password = "jD3$_wL0o";

        openDicePage();
        loginIntoAccount(Email, Password);
        clickEditProfile();
        switchToggleAndSave();
        clickEditProfile();
        switchToggleAndSave();
    }

    private void switchToggleAndSave() {
        By expectedElement = By.xpath("//div[contains(@data-ng-model, 'profile.willingToRelocate')]");
        WebElement webElement = waitForElement(expectedElement);
        webElement.click();
        By saveProfile = By.xpath(("//button[contains(@data-ng-if, 'isOnEditMode')]"));
        WebElement webElement1 = waitForElement(saveProfile);
        webElement1.click();
    }

    private void clickEditProfile() {
        By switchToggleElement = By.xpath("//button[contains(@id, 'editProfile')]");
        WebElement webElement = waitForElement(switchToggleElement);
        webElement.click();

    }

    private WebElement waitForElement(By expectedElement) {
        webDriverWait = new WebDriverWait(driver, 10);
        WebElement foundElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(expectedElement));
        return foundElement;
    }

    private void loginIntoAccount(String Email, String Password) {
        String xPathEmail = "//input[contains(@id, 'email')]";
        String xPathPassword = "//input[contains(@id, 'password')]";
        String xPathSignIn = "//button[contains(@type, 'submit')]";
        WebElement emailInput = driver.findElement(By.xpath(xPathEmail));
        emailInput.sendKeys(Email);
        WebElement passwordInput = driver.findElement(By.xpath(xPathPassword));
        passwordInput.sendKeys(Password);
        WebElement clickSignIn = driver.findElement(By.xpath(xPathSignIn));
        clickSignIn.click();
    }

    private void openDicePage() {
        driver.get(DICE_LOGIN_PAGE);
    }
}
