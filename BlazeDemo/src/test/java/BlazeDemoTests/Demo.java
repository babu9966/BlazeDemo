package BlazeDemoTests;

import Selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

import static Selenium.DriverFactory.*;

public class Demo{
    @BeforeSuite
    public void setup() {
        System.out.println("set up");
        getDriver();
    }

    @Test
    public void VerifyHomePageTitleAndDestinationWeekPageURL() {
        HomePage homePage = new HomePage();
        String actualPageTitle = homePage.getHomePageTitle();
        System.out.println("validating page title");
        Assert.assertEquals(actualPageTitle,
                getDriverProps().getProperty("home_page_title"));

        String redirectUrl = homePage.clickOnDestinationWeekAndGetPageURL();
        System.out.println("redirected URL for Destination week page is: " + redirectUrl);
        Assert.assertTrue(redirectUrl.contains("vacation"),
                "vacation is not present in the URL. please check. URL:: "+redirectUrl);
    }

    @Test
    @Parameters({"deptCity","destCity"})
    public void ValidatePurchaseTicket(String deptCity, String destCity) {
        System.out.println("dept city: " + deptCity);
        System.out.println("dest city: " + destCity);
        HomePage homePage = new HomePage();
//        selecting the deptartute and destination cities
        homePage.selectDeptCity(deptCity);
        homePage.selectDestnCity(destCity);
        boolean isPurchasePageDispalyed = homePage.clickFlightsBtn();
        Assert.assertTrue(isPurchasePageDispalyed, "Purchase page is not displayed. please check.");

        PurchaseFlight purchaseFlight = new PurchasePage().selectLowestPriceFlight();
        boolean isFormatExpected = purchaseFlight.verifyTotalCostFormat();
        ConfirmationPage confirmationPage = purchaseFlight.clickOnPurchaseBtnFlight();
        String confirmationId = confirmationPage.getConfirmationId();
        System.out.println("Confirmation ID: " + confirmationId);
        Assert.assertTrue(isFormatExpected, "Total cost format is not as expected XXX.XX");
    }

    @AfterSuite
    public void exitProcess() {
        getDriver().quit();
    }
}
