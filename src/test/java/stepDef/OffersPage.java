package stepDef;

import io.cucumber.java.en.And;
import utilities.TextSetUp;

import java.net.MalformedURLException;

public class OffersPage {
    TextSetUp textSetUp;

    public OffersPage(TextSetUp textSetUp) {
        this.textSetUp = textSetUp;
    }

    @And("I click on topdeals Button")
    public void iClickOnTopdealsButton() throws Exception {
        textSetUp.pageObjectManager.getOffersPage().topDealClick();


    }

    @And("I search the item")
    public void iSearchTheItem() throws MalformedURLException {

    }

    @And("I search the item on the offer page{}")
    public void iSearchTheItemOnTheOfferPage(String name) throws MalformedURLException {
        textSetUp.commonMethod.switchToChildWindow();
        textSetUp.pageObjectManager.getOffersPage().SearcItemo(name);
    }
}
