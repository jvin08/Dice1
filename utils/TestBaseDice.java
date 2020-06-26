package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pagesDice.DicePOM;

public class TestBaseDice {

    public WebDriver driver;
    public DicePOM dice;

    @BeforeSuite
    public void testSuiteSetup(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
    }

    @BeforeMethod
    public void testStartMethod(){
        driver = new FirefoxDriver();
        initDicePages(driver);
    }

    private void initDicePages(WebDriver driver) {
        dice = new DicePOM(driver);
    }

    @AfterMethod
    public void finishTestMethod(){
        driver.quit();
    }

    @AfterSuite
    public void tearDown(){
        System.out.println("Dice profile updated");
    }
}
