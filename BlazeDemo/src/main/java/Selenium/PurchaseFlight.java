package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.regex.Pattern;

public class PurchaseFlight extends BasePage{
    @FindBy(xpath = "//p[contains(text(), 'Total Cost')]/em")
    WebElement totalCostEle;

    @FindBy(xpath = "//input[@value = 'Purchase Flight']")
    WebElement purchaseFlightBtn;

    String waitForThisPageXpath = "//h2[contains(text(), 'Your flight from')]";
    public PurchaseFlight(){
        PageFactory.initElements(driver, this);
        this.waitForElementToBePresent(By.xpath(waitForThisPageXpath), 30);
    }

    public boolean verifyTotalCostFormat() {
        String totalCost = totalCostEle.getText().trim();
        System.out.println("Total cost is: " + totalCost);
        boolean isPatternMatched = Pattern.compile("[0-9]{3}\\.[0-9]{2}").matcher(totalCost).matches();
        System.out.println("Is total cost in the expected format ***.** : " + isPatternMatched);
        return isPatternMatched;
    }

    public ConfirmationPage clickOnPurchaseBtnFlight() {
        purchaseFlightBtn.click();
        return new ConfirmationPage();
    }
}
