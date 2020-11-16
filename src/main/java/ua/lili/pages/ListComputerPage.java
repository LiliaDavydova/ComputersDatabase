package ua.lili.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ListComputerPage {
    public static final String URL = "http://computer-database.gatling.io/computers";
    private WebDriver driver;

    @FindBy(id = "add")
    private WebElement addComputerButton;

    @FindBy(id = "searchbox")
    private WebElement searchBoxInput;

    @FindBy(id = "searchsubmit")
    private WebElement searchSubmitButton;


    public ListComputerPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickAddComputer() {
        addComputerButton.click();
    }

    public void filterByComputerName(String name) {
        searchBoxInput.sendKeys(name);
        searchSubmitButton.click();
    }

}

