package Pages;

import controlBase.Button;
import org.openqa.selenium.WebDriver;

public class pageObjectManager {

    public WebDriver driver;
    public loginPage loginPage;
    public offersPage offersPage;
    public pageObjectManager(WebDriver driver){
        this.driver= driver;
    }
    public loginPage getLoginPage(){

        loginPage = new loginPage(driver);
        return loginPage;
    }
    public offersPage getOffersPage(){
        offersPage = new offersPage(driver);
        return offersPage;
    }
}
