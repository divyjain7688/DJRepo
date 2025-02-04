/**
 * @author Bhavani
 * <p>
 * FMQAECOMM-2 [1]Validate Compare Products persists when view and sort are changed
 * <p>
 * ALM Test Instance ID: 649415
 */
package com.selenium.ebiz.compareProducts.compareProducts_grid;

import com.selenium.ebiz.baseclass.BaseClassV8_ParallelGrid;
import com.selenium.ebiz.beans.UsersListBean;
import com.selenium.ebiz.tools.PageManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ValidateCompareProductsPersistsWhenViewAndSortAreChanged_Grid extends BaseClassV8_ParallelGrid {

    String searchPLPHeading, subCategory, plpHeading, searchPLPHeadingAfterBackClick;
    List<String> cpPartNumbers, cpPartNumbersAfterSort, cpPartNumbersAfterListView;
    private ArrayList<UsersListBean> usersList;

    @Test(dataProvider = "browsers")
    public void compProd__ValidateCompareProductsPersistsWhenViewAndSortAreChanged(String browser, String version, String os, Method method) throws Exception {
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        PageManager pageManager = new PageManager(driver);

        usersList = pageManager.dBCon().loadDataFromExcel("searchByProdDesc", "Search");
        pageManager.homePage().searchByKeywordOrPartNumber(usersList.get(0).getSearchProductKeyword());
        Log.info("Search by product desc");

        pageManager.productListingPage().isGridViewDisplayed();
        Log.info("Navigate to PLP");
        searchPLPHeading = pageManager.productListingPage().getPLPHeadingText();
        pageManager.productListingPage().clickCompareCheckBoxes(4);
        pageManager.productListingPage().clickCompareTheseButton();

        pageManager.compareTheseItems().isDisplayedCompareProductsTitle();
        Log.info("Navigate to Compare products page");
        cpPartNumbers = pageManager.compareTheseItems().getAllPartNumbers();
        Log.info("Get all the part numbers from compare products page");
        pageManager.compareTheseItems().clickBackToSearchLink();
        Log.info("Navigates back to PLP");

        searchPLPHeadingAfterBackClick = pageManager.productListingPage().getPLPHeadingText();
        Log.info("searchPLPHeadingAfterBackClick: " + searchPLPHeadingAfterBackClick + "searchPLPHeading: " + searchPLPHeading);
        Assert.assertEquals(searchPLPHeadingAfterBackClick, searchPLPHeading);

        pageManager.productListingPage().selectBrandFromSortByDropdown();
        pageManager.productListingPage().clickCompareTheseButton();
        cpPartNumbersAfterSort = pageManager.compareTheseItems().getAllPartNumbers();
        Assert.assertTrue(pageManager.compareTheseItems().compareParts(cpPartNumbersAfterSort, cpPartNumbers));
        pageManager.compareTheseItems().clickBackToSearchLink();
        Log.info("Navigates back to PLP");

        pageManager.productListingPage().clickOnListView();
        Log.info("Click on List View button");
        pageManager.productListingPage().isDisplayedListView();
        Log.info("Verify PLP is displayed in List view");
        pageManager.productListingPage().clickCompareTheseButton();

        cpPartNumbersAfterListView = pageManager.compareTheseItems().getAllPartNumbers();
        pageManager.compareTheseItems().compareParts(cpPartNumbersAfterListView, cpPartNumbersAfterSort);
        pageManager.compareTheseItems().clickBackToSearchLink();
        Log.info("Navigates back to PLP");

        Assert.assertTrue(pageManager.productListingPage().isDisplayedListView());

    }
}
