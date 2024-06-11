package utilities;

import Pages.pageObjectManager;
import controlBase.Button;
import controlBase.controls;
import org.bouncycastle.asn1.crmf.Controls;
import org.openqa.selenium.WebDriver;

public class TextSetUp {

    public WebDriver driver;
    public pageObjectManager pageObjectManager;
    public BaseClass baseClass;
    public commonMethod commonMethod;
    public controls controls;

    public TextSetUp(){
        baseClass = new BaseClass();
        pageObjectManager = new pageObjectManager(baseClass.weBdriverManager());
        commonMethod = new commonMethod(baseClass.weBdriverManager());



    }
}
