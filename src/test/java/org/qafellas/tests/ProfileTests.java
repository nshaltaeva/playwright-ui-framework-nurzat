package org.qafellas.tests;

import com.microsoft.playwright.Page;
import org.qafellas.pages.BasePage;
import org.qafellas.pages.LoginPage;
import org.qafellas.pages.ProfilePage;
import org.qafellas.utils.ElementUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProfileTests {
    Page page;
    LoginPage loginPage;
    BasePage basePage;
    Properties properties;
    ProfilePage profilePage;
    ElementUtils elementUtils;



   @BeforeMethod
    public void setUp() throws IOException {
       basePage = new BasePage();
       properties = basePage.initializeProperties();
       page = basePage.initializeBrowser(properties);
       loginPage = new LoginPage(page);
       profilePage = new ProfilePage(page);
       elementUtils = new ElementUtils(page);
    }

    @AfterMethod
    public void tearDown(){
        basePage.quitBrowser();
    }


    @Test
    public void shouldUpdateProfilePicture(){
        loginPage.login("xyz@gmail.com","X1234567@");
        profilePage.profileIcon.click();
        elementUtils.uploadFile("image1.jpg");
        assertThat(profilePage.pictureUploadMessage).containsText("Image successfully uploaded!");
    }


}
