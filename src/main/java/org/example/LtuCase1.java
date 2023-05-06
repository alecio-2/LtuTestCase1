package org.example;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.title;

public class LtuCase1 {

    //Adding logger functionality
    private static final Logger LOGGER = LoggerFactory.getLogger(LtuCase1.class);

    public static void main(String[] args) throws InterruptedException {

        // One test case to find the final examination information and verify that the web page has the correct
        // information
        // Open the LTU website
        LOGGER.info("Starting program");
        try {
            Configuration.browser = "chrome";
            open("https://www.ltu.se");
            if (title().isEmpty()) {
                LOGGER.error("Failed to open the web page: empty title");
            } else {
                LOGGER.info("Successfully opened the web page: " + title());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to open the web page: " + e.getMessage());
        }

        // Click the "Accept" button on the cookies notification
        try {
            if ($(Selectors.byId("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).exists()) {
                $(Selectors.byId("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
                LOGGER.info("The cookies notification is closed");
            } else {
                LOGGER.info("The cookies notification is not displayed");
            }
            sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The cookies button not found");
        }

        //Click on the Student link
        try {
            $(Selectors.byXpath("/html/body/header/div[2]/div[1]/div[1]/div[3]/div/a[1]")).shouldBe(Condition.visible)
                    .click();
            LOGGER.debug("The STUDENT link is clicked");
            Selenide.sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The STUDENT link is not displayed");
        }

        //Click on the Mitt LTU link
        try {
            $(Selectors.byXpath("/html/body/header/div[2]/div[1]/div[1]/div[3]/div/a[1]")).shouldBe(Condition.visible)
                    .click();
            LOGGER.debug("The Mitt LTU link is clicked");
            Selenide.sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The Mitt LTU link is not displayed");
        }

        //Get credentials from Json file and enter them in the login form
        //Json file with username and password to login
        File jsonFile = new File("C:\\temp\\ltu.json");

        String username = null;
        String password = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            username = jsonNode.get("ltuCredentials").get("username").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Enter the username
        try {
            $(byId("username")).sendKeys(username);
            LOGGER.debug("The username is entered");
            Selenide.sleep(1000);
        } catch (Exception e) {
            LOGGER.error("The username  is not entered");
        }

        //Enter the password
        try {
            $(byId("password")).sendKeys(password);
            LOGGER.debug("The password is entered");
            Selenide.sleep(1000);
        } catch (Exception e) {
            LOGGER.error("The password is not entered");
        }

        //Click on submit button
        try {
            $(Selectors.byName("submit")).shouldBe(Condition.visible).click();
            LOGGER.debug("The login button is clicked");
            Selenide.sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The login button is not found");
        }

        //Click on the Tentamen header from left list
        try {
            $(Selectors.byText("Tentamen")).shouldBe(Condition.visible).click();
            LOGGER.debug("The Tentamen header from left list is clicked");
            Selenide.sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The Tentamen text is not found");
        }

        //Click on the Tentamensanmälan from left list
        try {
            $(Selectors.byText("Tentamensanmälan")).shouldBe(Condition.visible).click();
            LOGGER.debug("The Tentamensanmälan from left list is clicked");
            Selenide.sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The Tentamensanmälan text is not found");
        }

        //Click on the Tentamensanmälan from the page
        try {
            $(Selectors.byXpath("/html/body/div/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/div/div/div/div/div[1]/p[5]/a"))
                    .shouldBe(Condition.visible).click();
            LOGGER.debug("The Tentamensanmälan from the page is clicked");
            Selenide.sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The Tentamensanmälan from the page is not found");
        }

        //Click on the login button
        try {
            $(Selectors.byXpath("//span[contains(text(),'Logga in')]")).shouldBe(Condition.visible).click();
            LOGGER.debug("The Logga in button from Kronox is clicked");
            Selenide.sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The Logga in button from Kronox is not found");
        }

        //Get credentials from Json file and enter them in the login form
        //Json file with username and password to login

        jsonFile = new File("C:\\temp\\ltu.json");

        username = null;
        password = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            username = jsonNode.get("ltuCredentials").get("username").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Enter the username
        try {
            $(By.cssSelector("#login_username")).setValue(username);
            LOGGER.debug("The username is entered");
            Selenide.sleep(1000);
        } catch (Exception e) {
            LOGGER.error("The username  is not entered");
        }

        //Enter the password
        try {
            $(By.cssSelector("#login_password")).setValue(password);
            LOGGER.debug("The password is entered");
            Selenide.sleep(1000);
        } catch (Exception e) {
            LOGGER.error("The password is not entered");
        }

        //Simulate pressing the Enter key to perform login
        try {
            $(By.cssSelector("#login_password")).pressEnter();
            LOGGER.debug("The Enter key is pressed");
            Selenide.sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The Enter key is not pressed");
        }

        //Click on the tab with Aktivitetsanmälan
        try {
            $(By.xpath("//b[text()='Aktivitetsanmälan']")).shouldBe(Condition.visible).click();
            LOGGER.debug("The Aktivitetsanmälan tab is clicked");
            LOGGER.info("Showing the examination information");

            //Take a screenshot and save it to the target/Files folder
            File screenshot = Screenshots.takeScreenShotAsFile();
            String path = System.getProperty("user.dir") + "\\target\\Files\\screenshot.png";
            Files.move(screenshot.toPath(), new File(path).toPath());
            LOGGER.info("Screenshot saved to: " + path);

            Selenide.sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The Aktivitetsanmälan tab is not found");
        } catch (IOException e) {
            LOGGER.error("The screenshot is not saved");
        }

        LOGGER.info("The test is finished");
        LOGGER.info("-");
        Selenide.sleep(2000);
    }
}