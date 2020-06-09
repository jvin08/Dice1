import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class XpathTest {


    private FirefoxDriver driver;

    @BeforeSuite
    public void testSuiteSetup(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void test0001() {

        driver.get("https://the-internet.herokuapp.com/login");
        String fullXpath = "/html/body/div[2]/div/div/form/button";

        String relativeXpath = "//form/div[1]/div/div[1]/div/div/input[1]";

        String xPath1 = "//button[@class='radius']";
        String xPath2 = "//button[@class='radius'][@type='submit']";
        String xPath3 = "//button[@class='radius' and @type='vbbbv']";
        String xPath4 = "//button[@class='radius' or @type='vbbbv']";
        String xPath5 = "//button[contains(@class, 'rad')]";
        String xPath6 = "//button[starts-with(@class, 'rad')]";
        String xPath7 = "//*[text()=' Login']";
        String xPath8 = "(//a)[last()-1]";
        String xPath9 = "//*[@class='row']/following::label[@for='password']";
        String xPath10 = "//*[@name='username']/following::*[@type='password']";
        String xPath11 = "//*[@name='username']/preceding::*[@type='password']";


        driver.findElement(By.xpath(xPath8));
    }
}
