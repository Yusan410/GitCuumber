package stepDef;

import Pages.loginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import utilities.BaseClass;
import utilities.TextSetUp;

import java.net.MalformedURLException;


public class loginSteps {
public WebDriver driver;
TextSetUp textSetUp;
public loginPage loginPage;
public loginSteps(TextSetUp textSetUp){
    this.textSetUp=textSetUp;
}
    @Given("User on the GreenCart landing page")
    public void user_on_the_green_cart_landing_page() {
    textSetUp.baseClass.weBdriverManager().get(BaseClass.getProperty("greenCardUrl"));
    }
    // dynamic reusable
    @When("^User search for short name (.+) and extract the actual other$")
    public void user_search_for_short_name_name_and_extract_the_actual_other(String name) throws InterruptedException {
     textSetUp.pageObjectManager.getLoginPage().searchItem(name);


    }
    @Then("User search the same short name <Name> in offers page to verify product exist")
    public void user_search_the_same_short_name_name_in_offers_page_to_verify_product_exist() throws Exception {
    Thread.sleep(2000);
    textSetUp.pageObjectManager.offersPage.topDealClick();

    }
    @Then("Validate landing page productName match with cart page")
    public void validate_landing_page_product_name_match_with_cart_page() {

    }

    @Then("I verify {string} displayed")
    public void iVerifyDisplayed(String name) {
        Assertions.assertEquals(textSetUp.pageObjectManager.loginPage.isDisplayed(),name);
    }
}
