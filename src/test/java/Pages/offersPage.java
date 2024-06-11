package Pages;

import controlBase.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.TextSetUp;

import java.util.HashMap;
import java.util.Map;

public class offersPage {
    public WebDriver driver;
    public offersPage(WebDriver driver) {
        this.driver = driver;
    }


    By topDeal = By.xpath("//a[normalize-space()='Top Deals']");
    By searchItemm = By.id("search-field");

    public void topDealClick() throws Exception {
    driver.findElement(topDeal).click();

    }
    public void SearcItemo(String name){
        driver.findElement(searchItemm).sendKeys(name);
    }
}
