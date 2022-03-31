import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAccountRegistrationTest extends CommonUtility{

    @Test
    public void registerNewAccount() throws InterruptedException {

        driver.findElement(By.xpath("//a[@title='Sign in to view account details']")).click();
        driver.findElement(By.xpath("//span[text()='Register Now']")).click();

        String expectedRegPageTitle = "Quick Secure Sign Up";
        String actualRegPageTitle = CommonUtility.driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/h2")).getText();

        Assert.assertEquals(expectedRegPageTitle, actualRegPageTitle);

        CommonUtility.selectByValue("Mr");

         /*
        Please replace all the data input based on your test data
        */
        driver.findElement(By.id("FirstName")).sendKeys("SomeNamesss");
        driver.findElement(By.id("LastName")).sendKeys("SomeNamezzzz");
        driver.findElement(By.id("Email")).sendKeys("somenamebanaala@yahoo.com");
        driver.findElement(By.id("Password")).sendKeys("PasswonNrd@1");
        driver.findElement(By.id("DobDate")).sendKeys("05 07 84");
        driver.findElement(By.id("PhoneNumber")).sendKeys("0123456788");
        driver.findElement(By.id("HouseNumberOrName")).sendKeys("86");
        driver.findElement(By.id("Postcode")).sendKeys("E6 2BU");
        driver.findElement(By.id("SearchPostcode")).click();

        driver.findElement(By.id("SignupButton")).click();

        Thread.sleep(10000);

        String actRegConfirmation = "Thank you for registering.";
        String expRegConfirmation = driver.findElement(By.xpath("//div[@id=\"welcome-wrapper\"]/div/div[2]/div/p[1]")).getText();

        Assert.assertEquals(expRegConfirmation, actRegConfirmation);
    }

}
