package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Selenium.DriverFactory.getDriver;

public class HomePage extends BasePage {
    @FindBy(xpath = "//div[@class = 'container']/h1")
    public WebElement homePageTitle;

    @FindBy(xpath = "//a[@href='vacation.html']")
    WebElement destWeekLink;

    String destinationWeekXpath = "//div[@class = 'container'][contains(text() , 'Destination of the week')]";
    String fromXpath = "//div[@class='container']//select[@name='fromPort']";

    @FindBy(xpath="//div[@class='container']//select[@name='fromPort']")
    WebElement deptCity;

    @FindBy(xpath="//div[@class='container']//select[@name='toPort']")
    WebElement destnCity;

    @FindBy(xpath = "//div[@class='container']//input[@class='btn btn-primary']")
    WebElement findFlightsBtn;

    @FindBy(xpath="//div[@class='container']//table[@class='table']//td[6]")
    WebElement costChoosingTable;

    public HomePage(){

        PageFactory.initElements(getDriver(), this);
    }

    public String getHomePageTitle() {
        return homePageTitle.getText();
    }

    public String clickOnDestinationWeekAndGetPageURL() {
        destWeekLink.click();
        waitForElementToBePresent(By.xpath(destinationWeekXpath), 20);
        String url = getDriver().getCurrentUrl();
        getDriver().navigate().back();
        waitForElementToBePresent(By.xpath(fromXpath), 20);
        return url;
    }

    public void selectDeptCity(String deptCityName){
        getWait(20).until(ExpectedConditions.visibilityOf(deptCity));
        deptCity.click();
        selectFromDropdownByValue(deptCity,deptCityName);
    }

    public void selectDestnCity(String destCityName){
        getWait(20).until(ExpectedConditions.visibilityOf(destnCity));
        destnCity.click();
        selectFromDropdownByValue(destnCity,destCityName);
    }

    public boolean clickFlightsBtn(){
        if(findFlightsBtn.isDisplayed()){
            findFlightsBtn.click();
            return waitForElementToBePresent(By.xpath("//table/tbody/tr"), 20);
        } else {
            System.out.println("Find Flights Btn not Displayed");
            return false;
        }
    }


}