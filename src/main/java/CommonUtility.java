import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class CommonUtility {


    public static WebDriver driver;
    public static WebDriverWait webDriverWait;

    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.next.co.uk/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement cookieButton = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookieButton.click();
        waitForElementToDisappear(cookieButton);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    public void searchAnItemByProductOrBrandName(String keyword, String pageTitle) {

        driver.findElement(By.id("sli_search_1")).sendKeys(keyword);
        driver.findElement(By.xpath("//form[@id=\"newsearch\"]/input[4]")).click();

        String expectedTitle = pageTitle;
        String actualTitle = driver.findElement(By.xpath("//div[@id='ResultHeader']/div/h1")).getText();

        Assert.assertEquals(expectedTitle, actualTitle);
        System.out.println("Assertion passed, both values are equal");
    }

    public static void clickProductFromListOfItem(List<WebElement> listOfItems, String itemToChoose) {

        for (WebElement item : listOfItems) {
            String productName = item.getAttribute("title");
            if (productName.contains(itemToChoose)) {
                item.click();
                break;
            }
        }
    }

    public static void selectValueFromDivisionTag(List<WebElement> listOfElements, String option) {

        for (WebElement eachElement : listOfElements) {
            if (eachElement.getText().equals(option)) {
                eachElement.click();
                break;
            }
        }
    }

    public static void clickJS(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String js = "arguments[0].click();";
        javascriptExecutor.executeScript(js, element);
    }

    public static int getNumber(String value) {
        return Integer.parseInt(value);
    }

    public static void selectByValue(String titleValue) {
        /*
         *I created Select Object & passed the element ID to locate then
         * i select by value display in the dropdown
         */
        Select select = new Select(driver.findElement(By.id("Title")));
        select.selectByValue(titleValue);
    }

    //Explicit wait for specific element to load
    public static void waitForElementToLoad(WebElement element) {
        try {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException nse) {
            System.out.println("No such element exception : " + nse.getMessage());
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public static void waitForElementsToLoad(List<WebElement> elements) {
        try {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (NoSuchElementException nse) {
            System.out.println("No such element exception : " + nse.getMessage());
        } catch (Exception e) {
        System.out.println("Failed to locate element : " + e.getMessage());
        }
    }

    //Explicit wait for specific element to disappear
    public static void waitForElementToDisappear(WebElement element) {
        try {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            webDriverWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException te) {
            System.out.println("Cannot see invisibility of element within 5 seconds : " + te.getMessage());
        } catch (Exception e) {
            System.out.println("Failed with exception : " + e.getMessage());
        }
    }

    public static void fluentWaitForElement(WebElement element) {

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
