import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasketTest extends CommonUtility{


    @Test
    public void addItemToBasket() {

        String expectedTitle = "Jeans";
        searchAnItemByProductOrBrandName("Jeans", expectedTitle);

        List<WebElement> listOfItems = driver.findElements(By.xpath("//div[@id='rhs']/div[5]/article/section/div[1]/h2/a"));

        String itemToChoose = "Mid Blue Slim Fit Authentic Stretch Jeans";

        CommonUtility.waitForElementsToLoad(listOfItems);
        CommonUtility.clickProductFromListOfItem(listOfItems, itemToChoose);

        driver.findElement(By.id("dk_container_Colour-209765")).click();
        List<WebElement> listOfColours = driver.findElements(By.xpath("//div[@id='dk_container_Colour-209765']/div/ul/li"));

        String color = "Black";
        CommonUtility.selectValueFromDivisionTag(listOfColours, color);
        driver.findElement(By.xpath("//div[@id='dk_container_Fit-209765']/a")).click();
        List<WebElement> listOfFit = driver.findElements(By.xpath("//div[@id='dk_container_Fit-209765']/div/ul/li"));
        String fitOption = "Slim Fit";
        CommonUtility.selectValueFromDivisionTag(listOfFit, fitOption);

        CommonUtility.waitForElementsToLoad(listOfFit);
        WebElement element = driver.findElement(By.linkText("Choose Size"));
        CommonUtility.clickJS(element);
        /*
         * List<WebElement> listOfSize = driver.findElements(By.xpath("//div[@id='dk_container_Size-530-205']/div/ul/li"));
         * String sizeOption = "32 S";
         * SelectValueFromDivisionTag(listOfSize, sizeOption);
         */
        driver.findElement(By.linkText("30 S")).click();
        WebElement addToBasketIcon  = driver.findElement(By.xpath("//div[@class='BagHolder padding-top-10']/div/a"));
        addToBasketIcon.click();
        WebElement addedToBagDialog = driver.findElement(By.xpath("//section[@class='BagSummary MiniBagNonSecure']/following-sibling::div"));

        CommonUtility.waitForElementToDisappear(addedToBagDialog);

        WebElement bagIcon = driver.findElement(By.xpath("//section[@class='BagSummary MiniBagNonSecure']/a/div/div/span"));
        CommonUtility.fluentWaitForElement(bagIcon);
        String actualCount = bagIcon.getText();
        String expectedCount = "1";

        int actualNoOfItem = CommonUtility.getNumber(actualCount);
        int expectedNoOfItem = CommonUtility.getNumber(expectedCount);

        Assert.assertEquals(expectedNoOfItem, actualNoOfItem);

    }

}
