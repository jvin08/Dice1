import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class DropDownTest {

    private static final String DROPDOWN_MAIN_PAGE = "https://the-internet.herokuapp.com/dropdown";
    WebDriver driver;
    private Select select;
    private boolean selectedValueInDropDown;
    private boolean optionOneIsSelected;


    @BeforeSuite
    public void testSuiteSetup(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }
    @AfterSuite
    public void tearDown (){
        driver.quit();
    }

    //1.Open browser
    //2.Go to dropdown page
    //3.Select dropdown list
    //4.Select option 1
    //5.Assert first element
    //6.Close browser


    @Test
    public void test0001() {

        openDropDownPage();
        selectDropDownList();
        assertOption();
    }

    private void assertOption() {
        Assert.assertTrue(optionOneIsSelected);
    }


    private void selectDropDownList() {

            WebElement select = driver.findElement(By.id("dropdown"));

            List<WebElement> options = select.findElements(By.tagName("option"));

            for (WebElement option : options) {

              if("Option 1".equals(option.getText())) {
                  option.click();
                  System.out.println(option.isSelected());
                  optionOneIsSelected = option.isSelected();
              }
            }


    }

    private void openDropDownPage() {
        driver.get(DROPDOWN_MAIN_PAGE);
    }
}
