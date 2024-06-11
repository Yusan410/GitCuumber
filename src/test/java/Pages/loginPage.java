package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {
    public WebDriver driver;
    public loginPage(WebDriver driver){
        this.driver=driver;
    }

    By search = By.xpath("//input[@type='search']");
    By tomaToValidation = By.xpath("//h4[normalize-space()='Tomato - 1 Kg']");
    public void searchItem(String name){
        driver.findElement(search).sendKeys(name);
    }
    public String  isDisplayed(){
        return driver.findElement(tomaToValidation).getText();

    }
}
