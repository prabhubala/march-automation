import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchItemTest extends CommonUtility{


    /*
     * Launch the browser
     * Make sure the browser launch in full window
     * Get the site URL
     * Implicit wait for entire page to load.
     * Handle cookie dialog
     * Locate the search text field - selenium brings focus to the located filed
     * Input/Enter the keyword.
     * Click search Icon
     * Assert (JUnit) user is on right page.
     * */


    @Test
    public void searchAnItemUsingAProductName() {

        String expectedPageTitle = "Jeans";
        searchAnItemByProductOrBrandName("Jeans", expectedPageTitle);

    }

    @Test
    public void searchAnItemUsingBrandName() {

        String expectedPageTitle = "Nike";
        searchAnItemByProductOrBrandName("Nike", expectedPageTitle );

    }
}
