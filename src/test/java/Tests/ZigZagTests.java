package Tests;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static Utilities.AssertionMessages.*;
import static Utilities.Parameters.headphoneSearch;

public class ZigZagTests extends TestBase {


    @Test
    public void AddingProductAppearsInCartPageAfterSearch() {
        driver.get("https://www.zigzag.am/");

        HomePage homePage = new HomePage(driver);

        homePage.search(headphoneSearch);

        ResultsPage resultsPage = new ResultsPage(driver);

        resultsPage.hoverOverPrice();

        resultsPage.addToCartFromProductInfo();

        resultsPage.openCart();

        resultsPage.goToCartPagefromCartBar();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isCartFilled(), PRODUCT_ADDED_TO_CART_MESSAGE);
    }

    @Test
    public void CannotFavoriteWithoutAccountAndIsSentToLogin(){
        driver.get("https://www.zigzag.am/");
        HomePage homePage = new HomePage(driver);
        Assert.assertFalse(homePage.isLoggedIN());
        homePage.openFavorites();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isInLoginPage(), CANNOT_FAVORITE_WITHOUT_ACCOUNT_MESSAGE);
        }

    @Test
    public void cannotCompareSameItem(){
        driver.get("https://www.zigzag.am/");
        HomePage homePage = new HomePage(driver);

        homePage.hoverOverPrice();
        homePage.addToCompareFromProductInfo();

        homePage.hoverOverPrice();
        homePage.addToCompareFromProductInfo();

        homePage.goToComparePage();
        ComparePage comparePage = new ComparePage(driver);

        Assert.assertEquals(comparePage.numberOfProducts(), 1, CANNOT_COMPARE_SAME_ITEM_MESSAGE);

    }

    @Test
    public void invalidLoginFromHome(){
        driver.get("https://www.zigzag.am/");

        HomePage homePage = new HomePage(driver);
        homePage.invalidLoginFromHeader();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.invalidErrorAppeared(), INVALID_LOGIN_FROM_HOME_MESSAGE);
    }

/*
    @Test
    public void searchResultsContainKeyword(){
        driver.get("https://www.zigzag.am/");
        HomePage homePage = new HomePage(driver);

        homePage.search(headphoneSearch);
        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.checkForAtLeastFiveHeadphoneResults());
    }
*/
}
