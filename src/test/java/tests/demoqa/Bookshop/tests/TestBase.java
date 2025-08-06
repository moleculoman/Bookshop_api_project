package tests.demoqa.Bookshop.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.demoqa.Bookshop.config.web.WebConfig;

import java.util.Map;

public class TestBase {
    public static final String ALL_BOOKS_END_POINT = "/BookStore/v1/Books";
    public static final String SINGLE_BOOK_END_POINT = "/BookStore/v1/Book";
    public static final String LOGIN_END_POINT = "/Account/v1/Login";
    public static final String GENERATE_TOKEN_END_POINT = "/Account/v1/GenerateToken";

    private static final WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

    @BeforeAll
    public static void beforeAll() {
        Configuration.timeout = 10000; //доп.настройка, чтоб снизить сетевые проблемы
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser().toString();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = "eager";
        RestAssured.baseURI ="https://demoqa.com";

        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
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
    @AfterEach
    void closeSession(){
        WebDriverRunner.getWebDriver().close();
    }
    @BeforeEach
    void beforeEach(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void startSession(){
        WebDriverRunner.getWebDriver();
    }
}