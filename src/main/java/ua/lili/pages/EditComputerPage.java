package ua.lili.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ua.lili.model.Computer;

public class EditComputerPage {
    public static final String URL = "http://computer-database.gatling.io/computers/new";
    private WebDriver driver;

    @FindBy(id = "name")
    private WebElement computerNameInput;

    @FindBy(id = "introduced")
    private WebElement introducedDateInput;

    @FindBy(id = "discontinued")
    private WebElement discontinuedDateInput;

    @FindBy(id = "company")
    private WebElement companyNameSelect;

    @FindBy(css = "input[type='submit']")
    private WebElement createButton;

    @FindBy(xpath = "//a[text() ='Cancel']")
    private WebElement cancelButton;

    public EditComputerPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void populateComputerData(Computer computer) {
        computerNameInput.sendKeys(computer.getComputerName());
        introducedDateInput.sendKeys(computer.getIntroducedDate());
        discontinuedDateInput.sendKeys(computer.getDiscontinuedDate());
        Select companyNameSelect = new Select(this.companyNameSelect);
        companyNameSelect.selectByVisibleText(computer.getCompanyName());
    }

    public void clickCreateButton() {
        createButton.click();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

}
