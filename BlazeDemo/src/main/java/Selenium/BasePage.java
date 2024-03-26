package Selenium;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.pqc.jcajce.provider.NTRU;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static Selenium.DriverFactory.getDriver;

//import static Selenium.DriverFactory.driver;

public class BasePage {
    public WebDriver driver = getDriver();

    /**
     * select the dropdown value
     * @param sel
     * @param val
     */
    public void selectFromDropdownByValue(WebElement sel, String val){
        WebDriverWait wait = new WebDriverWait(driver,40);
        wait.until(ExpectedConditions.visibilityOf(sel));
        Select selEle = new Select(sel);
        selEle.selectByValue(val);
    }

    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(getDriver(), seconds);
    }

    /**
     * Wait until element is present
     * @param by
     * @param seconds
     * @return
     */
    public boolean waitForElementToBePresent(By by, int seconds) {
        try {
            getWait(seconds).until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch(TimeoutException te) {
            return false;
        }
    }

    /**
     * Take screenshot and store it in the //Target/screenshots folder.
     * @param fileName - starting name of screenshot
     */
    public void getScreenshot(String fileName) {
        TakesScreenshot srcShot = (TakesScreenshot) driver;
        File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
        try {
            String destFileLoc = System.getProperty("user.dir") + "\\target\\screenshots\\" + fileName + "_" + System.currentTimeMillis()+".png";
            FileUtils.copyFile(srcFile, new File(destFileLoc));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
