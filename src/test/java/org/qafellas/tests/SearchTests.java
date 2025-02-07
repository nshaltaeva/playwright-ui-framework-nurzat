package org.qafellas.tests;

import com.microsoft.playwright.*;
import org.qafellas.pages.BasePage;
import org.qafellas.pages.LoginPage;
import org.qafellas.pages.SearchPage;
import org.testng.annotations.*;

import java.util.Properties;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchTests {
    Page page;
    BasePage basePage;
    LoginPage loginPage;
    SearchPage searchPage;
    Properties properties;


    @BeforeMethod
    public void setUp() {
        basePage = new BasePage();
        properties = basePage.initializeProperties();
        page = basePage.initializeBrowser(properties);
        loginPage = new LoginPage(page);
        searchPage = new SearchPage(page);
    }

    @AfterMethod
    public void tearDown() {
        basePage.quitBrowser();
    }

    @Test
    public void shouldSearchListingWithKeyword(){
        loginPage.login("xyz@gmail.com", "X1234567@");
        searchPage.searchBox.click();
        searchPage.searchBox.fill("unique");
        searchPage.searchSubmitBtn.click();
        assertThat(searchPage.listingResult).containsText("Listing results:");
        assertThat(searchPage.listingResult).containsText("unique");
    }

    @Test
    public void shouldFilterOnlyRentalListings(){
        loginPage.login("xyz@gmail.com", "X1234567@");
        searchPage.searchNavigationBtn.click();
        searchPage.rentCheckBox.check();
        searchPage.advancedSearchBtn.click();
        searchPage.clickAndVerifyRentalListingCards();
    }


}