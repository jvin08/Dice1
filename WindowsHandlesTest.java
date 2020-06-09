import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Set;


public class WindowsHandlesTest {

    private static final String WINDOWS_MAIN_PAGE = "https://the-internet.herokuapp.com/windows";
    WebDriver driver;
    private String originalWindowHandle;
    private String newWindowHandle;
    private String clickLocator = "Click Here";
    private WebDriverWait webDriverWait;
    private int expectedAmountOfWindows;


    @BeforeSuite
    public void testSuiteSetup(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterSuite
    public void tearDown (){
        driver.quit();
    }

    //1.Open the browser
    //2.Go to 'windows' page
    //3.Click on "Click here" link
    //4.Verify amount of windows is 2
    //5.Verify new window title
    //6.Verify new window text
    //7.Switch to original window
    //8.Verify original window title
    @Test
    public void test0001() {

        String expectedNewWindowTitle = "New Window";
        String expectedNewWindowText = "New Window";
        String expectedOriginalWindowTitle = "The Internet";
        expectedAmountOfWindows = 2;

        openWindowsPage();
        clickOnLink();
        verifyAmountOfWindows(expectedAmountOfWindows);
        switchToNewWindow();
        verifyWindowText(expectedNewWindowText);
        verifyWindowTitle(expectedNewWindowTitle);


        switchBackToOriginalWindow();
        verifyWindowTitle(expectedOriginalWindowTitle);
    }


    //TODO: create a different scenario

    private void switchToNewWindow() {
        driver.switchTo().window(newWindowHandle);

    }

    private void switchBackToOriginalWindow() {
        driver.switchTo().window(originalWindowHandle);

    }

    private void verifyWindowText(String expectedText) {
        String webPage = driver.getPageSource();
        Assert.assertTrue(webPage.contains(expectedText));
    }

    private void verifyWindowTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

    }

    private void verifyAmountOfWindows(int expectedAmount) {
        //TODO: change this to explicit wait
        // we should avoid sleep:
        // 1.due to slow connection or smth. we need more than 5sec
        // 2.or we will click link and it will be immediately opened, and we hang 5sec for no reason

        String expectedCssSelector = "h3";
        By expectedElement = By.cssSelector(expectedCssSelector);
        WebElement webElement = waitForElement(expectedElement);
        webElement.isDisplayed();

        Set<String> windowHandles = driver.getWindowHandles();
        int actualAmountOfHandles = windowHandles.size();
        Assert.assertEquals(actualAmountOfHandles, expectedAmount); // assertions should be outside test method

        originalWindowHandle = (String) windowHandles.toArray()[0]; // extract windows from set to array and get first window

        newWindowHandle = (String) windowHandles.toArray()[1];

    }

    //purpose find and click
    private void clickOnLink() {
        //TODO: change this to class attribute


        By expectedElement = By.linkText(clickLocator);
        WebElement webElement = waitForElement(expectedElement);
        webElement.click();
    }

    private WebElement waitForElement(By expectedElement) {
        webDriverWait = new WebDriverWait(driver, 10);
        WebElement foundElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(expectedElement));
        return foundElement;

    }

    private void openWindowsPage() {
        driver.get(WINDOWS_MAIN_PAGE);

    }
}
