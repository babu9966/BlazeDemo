package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Selenium.DriverFactory.getDriver;

public class PurchasePage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr/td[6]")
    List<WebElement> pricesEles;

    String chooseLowestPriceFlightXpath =
            "//table[@class='table']/tbody/tr/td[6][contains(text() , '%s')]/preceding-sibling::td/input[@value =\"Choose This Flight\"]";
    public PurchasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public PurchaseFlight selectLowestPriceFlight(){
        List<String> prices=new ArrayList<>();
        for(WebElement priceEle : pricesEles){
            String price = priceEle.getText();
            prices.add(price.replaceAll("\\$", ""));
        }
        String minPrice = Collections.min(prices);
        System.out.println("Min Price is: "+minPrice);
        driver.findElement(By.xpath(String.format(chooseLowestPriceFlightXpath, minPrice))).click();

        return new PurchaseFlight();
    }
}
