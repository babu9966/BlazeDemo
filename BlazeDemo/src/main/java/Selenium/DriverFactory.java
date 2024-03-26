package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class DriverFactory {
    private static WebDriver driver = null;
    private static Properties driverProps = null;
    private DriverFactory(){

    }

    /**
     * gets the driver object if it's already initialized. Otherwise creates new driver object and returns.
     * @return - WebDriver object for the given browser
     */
    public static WebDriver getDriver() {
        if(driver != null)
            return driver;
        driver = createDriver();
        driver.get(getDriverProps().getProperty("app_url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    /**
     *
     * @return Properties object of driver.properties file present src/main/java/resources
     */
    public static Properties getDriverProps(){
        if(driverProps != null)
            return driverProps;
        setDriverProperties();
        return driverProps;
    }

    /**
     * Reading the properties file and assign to the 'driverProps'.
     *   So that we can use it anywhere we want.
     */
    public static void setDriverProperties() {
        try {
            Properties properties = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/resources/driver.properties");
            properties.load(ip);
            driverProps = properties;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("file not found");
        }
    }

    /**
     * Create the WebDriver based on 'browser' value in the driver.properties file.
     * @return WebDriver
     */
    private static WebDriver createDriver() {
        String browser = getDriverProps().getProperty("browser");
        switch (browser) {
            case "chrome":
                System.out.println("opening the chrome browser");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            default:
                System.out.println(browser + " is not configured. Please do it.");
                return null;

        }
    }
}
