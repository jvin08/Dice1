import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;

public class GoogleSearchTest {
    private FirefoxDriver driver;

    @BeforeSuite
    public void testSuiteSetup() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
    }

    @AfterSuite
    public void tearDown(){
        System.out.println("ALL TESTS ARE DONE");
    }

    @BeforeMethod
    public void startTestMethod() {
        driver = new FirefoxDriver();
    }

    @AfterMethod
    public void finishTestMethod() {
        driver.quit();
    }

    /*
        1.Open the google.com web page
        2.In search query type string and submit the search
        3.Verify that result page is showing up
        4.Verify that amount of results is more than 100
     */

    @Test
    public void test0001() {
        String queryString = "programming";;

        openGooglePage();
        typeAndSubmitQueryString(queryString);
        verifyResultsPage();

    }

    @Parameters({ "paramKey1" })
    @Test
    public void test0002(String param1) {
        String queryString = param1;

        openGooglePage();
        typeAndSubmitQueryString(queryString);
        verifyResultsPage();
    }

    private void verifyResultsPage() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = numberStatGenerator();
        Assert.assertTrue(100 < result);

    }

    private void typeAndSubmitQueryString(String queryString) {
        WebElement textInput = driver.findElement(By.cssSelector(".gLFyf"));
        textInput.sendKeys(queryString);
        textInput.submit();
    }

    private void openGooglePage() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");

        System.out.println("Opening google page");
        driver.get("https://www.google.com/");
    }

    public int numberStatGenerator() {
            WebElement resultsNumber = driver.findElement(By.id("result-stats"));
            String resultsStatsTextValue = resultsNumber.getText();
            String[] stringsArray = resultsStatsTextValue.split(" ");
            String amountOfResults = stringsArray[1].replace(",", "");
            return Integer.parseInt(amountOfResults);
        }






    }

