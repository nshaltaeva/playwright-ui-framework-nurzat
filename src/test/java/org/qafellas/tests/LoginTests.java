package org.qafellas.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.qafellas.pages.BasePage;
import org.qafellas.pages.LoginPage;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTests {
    Page page;
    LoginPage loginPage;
    BasePage basePage;
    Properties properties;



   @BeforeMethod
    public void setUp() throws IOException {
       basePage = new BasePage();
       properties = basePage.initializeProperties();
       page = basePage.initializeBrowser(properties);
       loginPage = new LoginPage(page);
    }

    @AfterMethod
    public void tearDown(){
        basePage.quitBrowser();
    }


    @Test
    public void shouldLoginSuccessfully() {
            loginPage.login("xyz@gmail.com", "X1234567@");
            assertThat(loginPage.userProfileBtn).isVisible();
        }

    @Test
    public void shouldGetInvalidPasswordNotification() {
            loginPage.login("xyz@gmail.com","ddddddddvv");
            assertThat(loginPage.passwordErrorNotice).containsText("Password must be at least 8 characters long, contain an uppercase letter, a number, and a special character (including a dot).");
        }

    @Test
    public void shouldGetWarningWhenEmptyValuesEnteredForEmailAndPassword(){
            loginPage.signInBtn.click();
            loginPage.submitBtn.click();
            assertThat(loginPage.emailErrorNotice).containsText("Email is required");
            assertThat(loginPage.passwordErrorNotice).containsText("Password is required");
        }


    @Test
    public void shouldGetWarningOnNonExistEmail(){
            loginPage.login("jhgghjj@gmail.com","X1234567@");
            assertThat(loginPage.generalError).containsText("User not found!");
        }


}
