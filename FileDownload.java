import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;

public class FileDownload {


    private FirefoxDriver driver;
    private static final String DOWNLOAD_MAIN_PAGE = "https://the-internet.herokuapp.com/download";
    private String DirectoryName = "testDir";


    @BeforeSuite
    public void testSuiteSetup(){

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");

//        driver = new FirefoxDriver();
    }



    //1.Load the browser
    //2.Visit the page
    //3.Create a uniquely named temp directory
    //4.Find and click the first download link on the page
    //  Automatically download the file to the temp directory without prompting
    //5.Check that the temp directory is not empty
    //6.Check that the downloaded file is not empty
    //7.Close the browser
    //8.Delete the temp directory and it's contents


    @Test
    public void test0001() {
        createTempDirectory();
        openUploadPage();
//        findLinkAndDownloadFile();
    }

    private void findLinkAndDownloadFile() throws InterruptedException {
        WebElement fileLink = driver.findElementByLinkText("5Lesson.txt");
        fileLink.click();

        FirefoxProfile profile = new FirefoxProfile();
        //Set Location to store files after downloading.
        profile.setPreference("browser.download.dir", "XXXXXXXXXXXXX");
        profile.setPreference("browser.download.folderList", 2);

        //Set Preference to not show file download confirmation dialogue using MIME types Of different file extension types.
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;");

        profile.setPreference( "browser.download.manager.showWhenStarting", false );
        profile.setPreference( "pdfjs.disabled", true );

        //Pass FProfile parameter In webdriver to use preferences to download file.
//        FirefoxDriver driver = new FirefoxDriver(profile);

        // Open APP to download application
        driver.get("http://toolsqa.com/automation-practice-form/");

        // Click to download
        driver.findElement(By.linkText("Test File to Download")).click();

        //Halting the execution for 5 secs to donwload the file completely
        Thread.sleep(5000);

        driver.close();


    }

    private void createTempDirectory() {
        //project directory
        String workingDirectory = System.getProperty("user.dir");
        String  dir = workingDirectory + File.separator + DirectoryName;

        System.out.println("Final file directory : " + dir);
        //create a directory in the path
        File file = new File(dir);

        if (!file.exists()) {
            file.mkdir();
            System.out.println("Directory is created!");
        } else {
            System.out.println("Directory is already existed!");
        }
    }

    private void openUploadPage() {
        driver.get(DOWNLOAD_MAIN_PAGE);
    }



}
