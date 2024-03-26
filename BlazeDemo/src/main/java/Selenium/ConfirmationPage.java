package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends BasePage {
    String tileXpath = "//div/h1[contains(text(), 'Thank you for your purchase today')]";
    @FindBy(xpath = "//table[@class = 'table']/tbody/tr/td[text() = 'Id']/following-sibling::td")
    public WebElement confirmationIdEle;
    public ConfirmationPage()  {
        PageFactory.initElements(driver, this);
        this.waitForElementToBePresent(By.xpath(tileXpath), 30);
    }

    /**
     * Gets the confirmation id from the confirmation page.
     * @return
     */
    public String getConfirmationId() {
        getScreenshot("test_");
        return confirmationIdEle.getText();
    }
}
