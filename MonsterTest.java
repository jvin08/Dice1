import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class MonsterTest {
    private FirefoxDriver driver;
    private final static String MONSTER_LOGIN_PAGE = "https://www.monster.com/profile/profile?";
    private WebDriverWait webDriverWait;

    @BeforeSuite
    public void testSuiteSetup(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
    }

    /*
     * 1.Open Monster.com page profile
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

    @Parameters ({ "ParamKey1" })
    @Test
    public void test0001(String Email) {
        //String Email = "ivan8qa@gmail.com";//take from parameters
        String Password = "jD3$_wL0o";

        openMonsterPage();
        loginIntoAccount(Email, Password);
        clickEditProfile();
        clickSaveChanges();
        System.out.println("Monster updated");
    }

    private void clickSaveChanges() {
        By clickEditElement = By.xpath("//*[@class='form-group']/following::button[@value='SAVE_SUMMARY']");
        WebElement webElement = waitForElement(clickEditElement);
        webElement.click();
    }

    private void clickEditProfile() {
        By clickEditElement = By.xpath("//*[@class='bottomMargin']/following::a[@title='Edit summary']");
        WebElement webElement = waitForElement(clickEditElement);
        webElement.click();
    }

    private WebElement waitForElement(By expectedElement) {
        webDriverWait = new WebDriverWait(driver, 10);
        WebElement foundElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(expectedElement));
        return foundElement;
    }

    private void loginIntoAccount(String Email, String Password) {
        String xPathEmail = "//input[contains(@id, 'EmailAddress')]";
        String xPathPassword = "//input[contains(@id, 'Password')]";
        String xPathSignIn = "//button[contains(@type, 'submit')]";
        WebElement emailInput = driver.findElement(By.xpath(xPathEmail));
        emailInput.sendKeys(Email);
        WebElement passwordInput = driver.findElement(By.xpath(xPathPassword));
        passwordInput.sendKeys(Password);
        By clickEditElement = By.xpath(xPathSignIn);
        waitForElement(clickEditElement);
        WebElement clickSignIn = driver.findElement(By.xpath(xPathSignIn));
        clickSignIn.click();
    }

    private void openMonsterPage() {
        driver.get(MONSTER_LOGIN_PAGE);
    }

}
