package ua.lili.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import ua.lili.model.Computer;
import ua.lili.pages.EditComputerPage;
import ua.lili.pages.ListComputerPage;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class CreateComputerTests {
    WebDriver driver;



    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void createComputerPositiveTest() throws InterruptedException {
        driver.get(ListComputerPage.URL);
        ListComputerPage listComputerPage = new ListComputerPage(driver);
        listComputerPage.clickAddComputer();
        Thread.sleep(2000);
        EditComputerPage editComputerPage = new EditComputerPage(driver);
        editComputerPage.populateComputerData(new Computer("Computer12345678", "2010-11-16", "2020-11-16", "Apple Inc."));
        editComputerPage.clickCreateButton();
        Thread.sleep(2000);
        SoftAssert softAssert = new SoftAssert();
        WebElement alertElement = driver.findElement(By.cssSelector("div.alert-message"));
        String actualMsg = alertElement.getText();
        String expectedMsg = "Done ! Computer Computer1 has been created";
        softAssert.assertEquals(actualMsg, expectedMsg, "Wrong message after save");
        listComputerPage.filterByComputerName("Computer12345678");
        try {
            WebElement computerName = driver.findElement(By.xpath("//tr[1]/td[1]/a"));
            WebElement introduced = driver.findElement(By.xpath("//tr[1]/td[2]"));
            WebElement discontinued = driver.findElement(By.xpath("//tr[1]/td[3]"));
            WebElement companyName = driver.findElement(By.xpath("//tr[1]/td[4]"));
            softAssert.assertEquals(computerName.getText(), "Computer12345678");
            softAssert.assertEquals(introduced.getText(), "16 Nov 2010");
            softAssert.assertEquals(discontinued.getText(), "16 Nov 2020");
            softAssert.assertEquals(companyName.getText(), "Apple Inc.");
        } catch (Exception e) {
            softAssert.fail("Computer was not found");
        }
        softAssert.assertAll();

    }

    @Test
    public void cancelTest() throws InterruptedException {
        driver.get(ListComputerPage.URL);
        ListComputerPage listComputerPage = new ListComputerPage(driver);
        listComputerPage.clickAddComputer();
        EditComputerPage editComputerPage = new EditComputerPage(driver);
        editComputerPage.populateComputerData(new Computer("ComputerABC", "2010-11-16", "2020-11-16", "Apple Inc."));
        editComputerPage.clickCancelButton();
        Thread.sleep(2000);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getCurrentUrl(), ListComputerPage.URL, "Wrong URL after cancel");
        listComputerPage.filterByComputerName("ComputerABC");
        WebElement message = driver.findElement(By.xpath("//em"));
        softAssert.assertEquals(message.getText(), "Nothing to display");
        softAssert.assertAll();
    }

    @Test
    public void validateRequiredFieldsTest() throws InterruptedException {
        driver.get(ListComputerPage.URL);
        ListComputerPage listComputerPage = new ListComputerPage(driver);
        listComputerPage.clickAddComputer();
        Thread.sleep(2000);
        EditComputerPage editComputerPage = new EditComputerPage(driver);
        editComputerPage.clickCreateButton();
        Thread.sleep(2000);
        SoftAssert softAssert = new SoftAssert();
        WebElement alertElement = driver.findElement(By.xpath(".//input[@id='name']/following-sibling::span[@class = 'help-inline']"));
        String actualMsg = alertElement.getText();
        String expectedMsg = "Failed to refine type : Predicate isEmpty() did not fail.";
        softAssert.assertEquals(actualMsg, expectedMsg, "Wrong validation message");
        softAssert.assertAll();
    }

    @Test
    public void validateIntroducedDateTest() throws InterruptedException {
        driver.get(ListComputerPage.URL);
        ListComputerPage listComputerPage = new ListComputerPage(driver);
        listComputerPage.clickAddComputer();
        Thread.sleep(2000);
        LocalDate today = LocalDate.now();
        EditComputerPage editComputerPage = new EditComputerPage(driver);
        editComputerPage.populateComputerData(new Computer("Computer1", "test", today.toString(), "Apple Inc."));
        editComputerPage.clickCreateButton();
        Thread.sleep(2000);
        SoftAssert softAssert = new SoftAssert();
        WebElement alertElement = driver.findElement(By.xpath(".//input[@id='introduced']/following-sibling::span[contains(@class, 'help-inline')]"));
        String actualMsg = alertElement.getText();
        String expectedMsg = "Failed to decode date : java.time.format.DateTimeParseException: Text 'test' could not be parsed at index 0";
        softAssert.assertEquals(actualMsg, expectedMsg, "Wrong input data");
        softAssert.assertAll();
    }

    @Test
    public void validateDiscontinuedDateTest() throws InterruptedException {
        driver.get(ListComputerPage.URL);
        ListComputerPage listComputerPage = new ListComputerPage(driver);
        listComputerPage.clickAddComputer();
        Thread.sleep(2000);
        LocalDate today = LocalDate.now();
        EditComputerPage editComputerPage = new EditComputerPage(driver);
        editComputerPage.populateComputerData(new Computer("Computer1", today.minusYears(10).toString(), "test", "Apple Inc."));
        editComputerPage.clickCreateButton();
        Thread.sleep(2000);
        SoftAssert softAssert = new SoftAssert();
        WebElement alertElement = driver.findElement(By.xpath(".//input[@id='discontinued']/following-sibling::span[contains(@class, 'help-inline')]"));
        String actualMsg = alertElement.getText();
        String expectedMsg = "Failed to decode date : java.time.format.DateTimeParseException: Text 'test' could not be parsed at index 0";
        softAssert.assertEquals(actualMsg, expectedMsg, "Wrong input data");
        softAssert.assertAll();
    }

    @Test
    public void validateIntroducedBeforeDiscontinuedDateTest() throws InterruptedException {
        driver.get(ListComputerPage.URL);
        ListComputerPage listComputerPage = new ListComputerPage(driver);
        listComputerPage.clickAddComputer();
        Thread.sleep(2000);
        LocalDate today = LocalDate.now();
        EditComputerPage editComputerPage = new EditComputerPage(driver);
        editComputerPage.populateComputerData(new Computer("Computer1", today.toString(), today.minusYears(10).toString(), "Apple Inc."));
        editComputerPage.clickCreateButton();
        Thread.sleep(2000);
        SoftAssert softAssert = new SoftAssert();
        WebElement alertElement = driver.findElement(By.xpath(".//input[@id='discontinued']/following-sibling::span[contains(@class, 'help-inline')]"));
        String actualMsg = alertElement.getText();
        String expectedMsg = "Discontinued date is before introduction date";
        softAssert.assertEquals(actualMsg, expectedMsg, "Wrong input data");
        softAssert.assertAll();

    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
