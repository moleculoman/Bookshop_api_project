package tests.demoqa.Bookshop.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.demoqa.Bookshop.config.WebConfig;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    public static final String ALL_BOOKS_END_POINT = "/BookStore/v1/Books";
    public static final String SINGLE_BOOK_END_POINT = "/BookStore/v1/Book";
    public static final String LOGIN_END_POINT = "/Account/v1/Login";
    public static final String GENERATE_TOKEN_END_POINT = "/Account/v1/GenerateToken";

    /*
    static String SELENOID_URL = System.getProperty("selenoid.url");
    static String SELENOID_LOGIN = System.getProperty("selenoid.login");
    static String SELENOID_PASSWORD = System.getProperty("selenoid.password");
    */

    private static final WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

    @BeforeAll
    public static void beforeAll() {
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser().toString();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        RestAssured.baseURI = "https://demoqa.com";

        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            //capabilities.setCapability("enableVNC", true);
            //capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;

            if (webConfig.remoteUrl() == null || webConfig.remoteUrl().isEmpty()) {
                throw new IllegalStateException("Remote URL is not configured or is empty");
            }
        }
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    @BeforeEach
    void beforeEach(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}