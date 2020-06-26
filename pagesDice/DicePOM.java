package pagesDice;

import org.openqa.selenium.WebDriver;

public class DicePOM {

        public EditProfileDice editProfileDice;
        public LoginDicePage loginDicePage;

        public DicePOM(WebDriver driver) {
            this.editProfileDice = new EditProfileDice(driver);
            this.loginDicePage = new LoginDicePage(driver);
        }
}
