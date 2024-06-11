package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.bs.A;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.TextSetUp;

import java.io.File;
import java.io.IOException;

public class Hooks {
    TextSetUp textSetUp;

    public Hooks(TextSetUp textSetUp) {

        this.textSetUp = textSetUp;
    }

    @After
    public void TreeDown() {
        textSetUp.baseClass.weBdriverManager().quit();
    }

    @AfterStep
    public void ScreenShot(Scenario scenario) throws IOException {
        WebDriver driver = textSetUp.baseClass.weBdriverManager();
        if (scenario.isFailed()) {
            // Take screen shot
            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContext = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContext, "image/png", "image");
        }

    }
}
