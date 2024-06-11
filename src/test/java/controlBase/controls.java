package controlBase;


import org.openqa.selenium.WebDriver;
import utilities.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TextSetUp;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Level;


public class controls {
    protected By locator;
    protected WebElement control;
    public TextSetUp textSetUp;


    public controls(By locator) {
        this.locator = locator;

    }
    protected void findControl() throws Exception {
        try {
            this.control = textSetUp.baseClass.weBdriverManager().findElement(this.locator);
            WebDriverWait explicitWait = new WebDriverWait(textSetUp.baseClass.weBdriverManager(), Duration.ofSeconds(30));
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(this.locator));
            Logger.log(Level.INFO, this.getClass().getName() + " > Session Thread to Use: [" + ((RemoteWebDriver) textSetUp.baseClass.weBdriverManager()).getSessionId() + "]");
            Logger.log(Level.INFO, this.getClass().getName() + "> locator [" + locator + "] amount found element: [" + textSetUp.baseClass.weBdriverManager().findElements(this.locator).size() + "]");
            this.smartScroll();
        } catch (NoSuchElementException exception) {
            Logger.log(Level.ALL, this.getClass().getName() + " > Session Thread to Use: [" + ((RemoteWebDriver) textSetUp.baseClass.weBdriverManager()).getSessionId() + "]");
            Logger.log(Level.ALL, this.getClass().getName() + "> *** the locator : [" + this.locator + "] was not found, Error message detail: \n" + exception.getMessage());
            throw new Exception(Level.ALL + " " + this.getClass().getName() + "> the locator : [" + this.locator + "] is not displayed, Error message detail:\n " + exception.getMessage() + "Session: " +
                    ((RemoteWebDriver) textSetUp.baseClass.weBdriverManager()).getSessionId() + "\nThread: " + Thread.currentThread().getName());
        } catch (Exception e) {
            Logger.log(Level.ALL, this.getClass().getName() + " > Session Thread to Use: [" + ((RemoteWebDriver) textSetUp.baseClass.weBdriverManager()).getSessionId() + "]");
            Logger.log(Level.ALL, this.getClass().getName() + "> the locator : [" + this.locator + "] can not be found, Error message detail:\n " + e.getMessage());
            throw new Exception(Level.ALL + " " + this.getClass().getName() + "> the locator : [" + this.locator + "] can not be found, Error message detail:\n " + e.getMessage() + "Session: " +
                    ((RemoteWebDriver) textSetUp.baseClass.weBdriverManager()).getSessionId() + " Thread: " + Thread.currentThread().getName());
        }
    }

    protected synchronized void smartScroll() {
        try {
            ((JavascriptExecutor) textSetUp.baseClass.weBdriverManager()).executeScript("arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });", this.control);
            Logger.log(Level.INFO, this.getClass().getName() + " > Scroll SUCCESSFULLY] - Thread: " + Thread.currentThread().getName());
        } catch (Exception e) {
            Logger.log(Level.ALL, this.getClass().getName() + " > Scroll FAILED " + e.getMessage() + " Thread: " + Thread.currentThread().getName());
        }
    }

    public void click() throws Exception {
        //  ScreenShot.addScreen(this.getClass().getName() + "> Click on [" + this.locator + "] " + this.getClass().getSimpleName());
        this.findControl();
        Logger.log(Level.INFO, this.getClass().getName() + "> Click on [" + this.locator + "] " + this.getClass().getSimpleName());
        this.control.click();
        // ScreenShot.addScreen(this.getClass().getName() + "> Click on [" + this.locator + "] " + this.getClass().getSimpleName());
    }

    public boolean controlIsDisplayed() {
        try {
            // ScreenShot.addScreen(this.getClass().getName() + "> Is control [" + this.locator + "] " + this.getClass().getSimpleName() + " displayed?");
            this.findControl();
            Logger.log(Level.INFO, this.getClass().getName() + "> Is control [" + this.locator + "] " + this.getClass().getSimpleName() + " displayed?: " + this.control.isDisplayed());
            return this.control.isDisplayed();
        } catch (Exception e) {
            //  ScreenShot.addScreen(this.getClass().getName() + "> Is control [" + this.locator + "] " + this.getClass().getSimpleName() + " displayed?");
            return false;
        } finally {
            //   ScreenShot.addScreen(this.getClass().getName() + "> Is control [" + this.locator + "] " + this.getClass().getSimpleName() + " displayed?");
        }
    }


    public void controlIsClickable() throws Exception {
        this.findControl();
        WebDriverWait explicitWait = new WebDriverWait(textSetUp.baseClass.weBdriverManager(), Duration.ofSeconds(15));
        explicitWait.until(ExpectedConditions.elementToBeClickable(this.locator));
        Logger.log(Level.INFO, this.getClass().getName() + "> Wait control [" + this.locator + "] " + this.getClass().getSimpleName() + " is clickable");
    }


    public boolean controlIsDisplayed(int timeoutSeconds) throws MalformedURLException {
        try {

            textSetUp.baseClass.weBdriverManager().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutSeconds));
            this.findControl();
            Logger.log(Level.INFO, this.getClass().getName() + "> control [" + this.locator + "] " + this.getClass().getSimpleName() + " is displayed: " + true);
            return true;
        } catch (Exception e) {
            Logger.log(Level.INFO, this.getClass().getName() + "> control [" + this.locator + "] " + this.getClass().getSimpleName() + " is displayed: false");
            textSetUp.baseClass.weBdriverManager().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            return false;
        } finally {
            textSetUp.baseClass.weBdriverManager().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            //ScreenShot.addScreen(this.getClass().getName() + "> Is control [" + this.locator + "] " + this.getClass().getSimpleName() + " displayed?");
        }
    }

    public String getText() throws Exception {
        // ScreenShot.addScreen(this.getClass().getName() + "> Get text from [" + this.locator + "] get text");
        Thread.sleep(1000);
        this.findControl();
        Logger.log(Level.INFO, this.getClass().getName() + "> Get text from [" + this.locator + "] " + this.getClass().getSimpleName() + ", value: [" + this.control.getText() + "]");
        // ScreenShot.addScreen(this.getClass().getName() + "> Get text from [" + this.locator + "] get text");
        return this.control.getText();
    }

    public String getTextAttribute(String attribute) throws Exception {
        // ScreenShot.addScreen(this.getClass().getName() + "> Get text from [" + this.locator + "] get text");
        this.findControl();
        Logger.log(Level.INFO, this.getClass().getName() + "> Get text from [" + this.locator + "] " + this.getClass().getSimpleName() + ", value: [" + this.control.getAttribute(attribute) + "]");
        //  ScreenShot.addScreen(this.getClass().getName() + "> Get text from [" + this.locator + "] get text");
        return this.control.getAttribute(attribute);
    }

    public String getCssAttribute(String attribute) throws Exception {
        //  ScreenShot.addScreen(this.getClass().getName() + "> Get css from [" + this.locator + "] get text");
        this.findControl();
        Logger.log(Level.INFO, this.getClass().getName() + "> Get css from [" + this.locator + "] " + this.getClass().getSimpleName() + ", value: [" + this.control.getCssValue(attribute) + "]");
        // ScreenShot.addScreen(this.getClass().getName() + "> Get css from [" + this.locator + "] get text");
        return this.control.getCssValue(attribute);
    }

    public String getLocatorXpathString() {
        Logger.log(Level.INFO, this.getClass().getName() + "> Get xpath locator [" + this.locator + "] " + this.getClass().getSimpleName() + ", value: [" + this.locator.toString().replace("By.xpath: ", "") + "]");
        return this.locator.toString().replace("By.xpath: ", "");
    }

    public boolean controlIsNotDisplayed(int timeoutSecond) throws MalformedURLException {
        try {
            //  ScreenShot.addScreen(this.getClass().getName() + "> Is control [" + this.locator + "] " + this.getClass().getSimpleName() + " not displayed?");
            textSetUp.baseClass.weBdriverManager().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutSecond));
            WebDriverWait explicitWait = new WebDriverWait(textSetUp.baseClass.weBdriverManager(), Duration.ofSeconds(15));
            explicitWait.until(ExpectedConditions.numberOfElementsToBe(this.locator, 0));
            this.findControl();
            Logger.log(Level.INFO, this.getClass().getName() + "> control [" + this.locator + "] " + this.getClass().getSimpleName() + " is not displayed: " + true);
            return true;
        } catch (Exception e) {
            Logger.log(Level.INFO, this.getClass().getName() + "> control [" + this.locator + "] " + this.getClass().getSimpleName() + " is not displayed: false");
            textSetUp.baseClass.weBdriverManager().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            return false;
        } finally {
            textSetUp.baseClass.weBdriverManager().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            //  ScreenShot.addScreen(this.getClass().getName() + "> Is control [" + this.locator + "] " + this.getClass().getSimpleName() + " not displayed?");
        }
    }

    public boolean controlIsTextboxEmpty(String attribute) throws Exception {
        // ScreenShot.addScreen(this.getClass().getName() + "> Get text from [" + this.locator + "] get text");
        this.findControl();
        Logger.log(Level.INFO, this.getClass().getName() + "> Get text from [" + this.locator + "] " + this.getClass().getSimpleName() + ", value: [" + this.control.getAttribute(attribute) + "]");
        //  ScreenShot.addScreen(this.getClass().getName() + "> Get text from [" + this.locator + "] get text");
        return this.control.getAttribute(attribute).isEmpty();
    }

    public boolean controlIsSelectDropdownEmpty(String attribute, String value) throws Exception {
        // ScreenShot.addScreen(this.getClass().getName() + "> Get text from [" + this.locator + "] get text");
        this.findControl();
        Logger.log(Level.INFO, this.getClass().getName() + "> Get text from [" + this.locator + "] " + this.getClass().getSimpleName() + ", value: [" + this.control.getAttribute(attribute) + "]");
        // ScreenShot.addScreen(this.getClass().getName() + "> Get text from [" + this.locator + "] get text");
        return this.control.getAttribute(attribute).isEmpty() || this.control.getAttribute(attribute).contains(value);
    }

    public void waitUntilControlIsDisplayed() throws Exception {
        this.findControl();
        WebDriverWait explicitWait = new WebDriverWait(textSetUp.baseClass.weBdriverManager(), Duration.ofSeconds(15));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(this.locator));
        Logger.log(Level.INFO, this.getClass().getName() + "> Wait control [" + this.locator + "] " + this.getClass().getSimpleName() + " is clickable");
    }

    public void waitWhileControlDisappears(int timeOutSeconds) throws MalformedURLException {
        try {
            textSetUp.baseClass.weBdriverManager().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            int time = 0;
            int amountOfElementsDisplayed = textSetUp.baseClass.weBdriverManager().findElements(this.locator).size();
            Logger.log(Level.INFO, this.getClass().getName() + "> control [" + this.locator + "] " + this.getClass().getSimpleName() + " has found : [" + amountOfElementsDisplayed + "] elements");
            while (amountOfElementsDisplayed > 0 && time < timeOutSeconds) {
                amountOfElementsDisplayed = textSetUp.baseClass.weBdriverManager().findElements(this.locator).size();
                Logger.log(Level.INFO, this.getClass().getName() + "> waiting disappears the control [" + this.locator + "] " + this.getClass().getSimpleName() + " has found : [" + amountOfElementsDisplayed + "] elements");
                Thread.sleep(1000);
                time++;
            }
            Logger.log(Level.INFO, this.getClass().getName() + "> finishing disappears the control [" + this.locator + "] " + this.getClass().getSimpleName() + " has found : [" + amountOfElementsDisplayed + "] elements");
            Thread.sleep(1000);
        } catch (Exception e) {
        } finally {
            textSetUp.baseClass.weBdriverManager().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }
    }

    public String getAmountElements() throws Exception {
        // ScreenShot.addScreen(this.getClass().getName() + "> Get text from [" + this.locator + "] get text");
        this.findControl();
        Logger.log(Level.INFO, this.getClass().getName() + "> Get text from [" + this.locator + "] " + this.getClass().getSimpleName() + ", value: [" + this.control.getText() + "]");
        // ScreenShot.addScreen(this.getClass().getName() + "> Get text from [" + this.locator + "] get text");
        String amountOfElementsDisplayed = Integer.toString(textSetUp.baseClass.weBdriverManager().findElements(this.locator).size());
        return amountOfElementsDisplayed;
    }

    public void hoverMouse() throws Exception {
        Actions actions = new Actions(textSetUp.baseClass.weBdriverManager());
        this.findControl();
        actions.moveToElement(this.control).pause(Duration.ofSeconds(5)).perform();
    }

    public WebElement getWebElement() throws MalformedURLException {
        return textSetUp.baseClass.weBdriverManager().findElement((this.locator));
    }

    public boolean isEnabled() throws Exception {
        this.findControl();
        return this.control.isEnabled();
    }

}
