package pagesDice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonSteps;

public class EditProfileDice {

    private final WebDriver driver;
    private By expectedElement;
    private By saveProfile;
    private By switchToggleElement;

    public EditProfileDice(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToggleAndSave() {
        expectedElement = By.xpath("//div[contains(@data-ng-model, 'profile.willingToRelocate')]");
        WebElement webElement = CommonSteps.waitForElement(driver, expectedElement);
        webElement.click();
        saveProfile = By.xpath(("//button[contains(@data-ng-if, 'isOnEditMode')]"));
        WebElement webElement1 = CommonSteps.waitForElement(driver, saveProfile);
        webElement1.click();
    }

    public void clickEditProfile() {
        switchToggleElement = By.xpath("//button[contains(@id, 'editProfile')]");
        WebElement webElement = CommonSteps.waitForElement(driver, switchToggleElement);
        webElement.click();

    }
}
