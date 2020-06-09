import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class FileUpload {


    private FirefoxDriver driver;
    private static final String UPLOAD_MAIN_PAGE = "https://the-internet.herokuapp.com/upload";
    private String fileName = "Tan_2.mp4";
    private String filePath = "C:\\Users\\Ivan\\Videos\\Captures\\" + fileName;
    private String uploadedElement = "#uploaded-files";

    @BeforeSuite
    public void testSuiteSetup(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }


    //1.Open the browser
    //2.Go to 'upload' page
    //3.Click on "Browse" input
    //4.Inject the file path "XYZ" and press open
    //5.Press "Upload"
    //6.Wait for uploaded file
    //7.Assert fileName "XYZ" in the uploaded file
    //8.Close the browser

    @Test
    public void test0001() {

        openUploadPage();
        downloadFile();
        verifyFileUploaded();
    }

    private void verifyFileUploaded() {
        By expectedElement = By.cssSelector(uploadedElement);
        WebElement webElement = waitForElement(expectedElement);
        Assert.assertTrue((webElement.getText().contains(fileName)));
    }

    private WebElement waitForElement(By expectedElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        WebElement foundElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(expectedElement));
        return foundElement;

    }

    private void downloadFile() {
        WebElement upload = driver.findElement(By.name("file"));
        upload.sendKeys(filePath);
        driver.findElement(By.cssSelector("#file-submit")).click();
    }


    private void openUploadPage() {
        driver.get(UPLOAD_MAIN_PAGE);
    }
}
