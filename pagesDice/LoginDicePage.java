package pagesDice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginDicePage {

    private static final String DICE_LOGIN_PAGE_URL = "https://www.dice.com/dashboard";
    private By loginInputLocator = By.xpath("//input[contains(@id, 'email')]");
    private By passwordInputLocator = By.xpath("//input[contains(@id, 'password')]");
    private By signInButton = By.xpath("//button[contains(@type, 'submit')]");

    private WebDriver driver;

    public LoginDicePage(WebDriver driver){
        this.driver = driver;
    }

    public void open(){
        driver.get(DICE_LOGIN_PAGE_URL);
    }

    public void loginIntoAccount(String Email, String Password) {
        WebElement emailInput = driver.findElement(loginInputLocator);
        emailInput.sendKeys(Email);
        WebElement passwordInput = driver.findElement(passwordInputLocator);
        passwordInput.sendKeys(Password);
        WebElement clickSignIn = driver.findElement(signInButton);
        clickSignIn.click();
    }


}
