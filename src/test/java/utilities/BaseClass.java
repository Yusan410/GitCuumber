package utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;

    public WebDriver weBdriverManager() {
        if (driver == null) {

            switch (BaseClass.getProperty("browser")) {

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                  // PageInitilaizer.initilize();

                    break;
                case "chrome":
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    driver = new ChromeDriver();
                   // PageInitilaizer.initilize();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                  //  PageInitilaizer.initilize();
                    break;
            }
            //  PageInitilaizer.initilize();

        }

        driver.manage().window().maximize();

        return driver;
    }

    private static Properties configFile;

    static {

        try {
            String filePath = Constant.configuration_filepath;

            FileInputStream input = new FileInputStream(filePath);

            configFile = new Properties();
            configFile.load(input);

            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getProperty(String keyName) {
        return configFile.getProperty(keyName);
    }
    public void TreeDown(){
        driver.close();
        driver.quit();
    }
}
