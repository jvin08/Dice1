package uitests;

import org.testng.annotations.Test;
import utils.TestBaseDice;

public class DiceUpdateTest extends TestBaseDice {

    @Test
    public void testUpdate(){
        String Email = "ivan8qa@gmail.com";
        String Password = "jD3$_wL0o";

        dice.loginDicePage.open();
        dice.loginDicePage.loginIntoAccount(Email, Password);
        dice.editProfileDice.clickEditProfile();
        dice.editProfileDice.switchToggleAndSave();
        dice.editProfileDice.clickEditProfile();
        dice.editProfileDice.switchToggleAndSave();
    }

}
