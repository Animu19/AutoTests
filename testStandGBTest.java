package org.example.certificationTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import org.example.Logging.AdditionalLogger;
import org.example.Logging.JunitExtension;
import org.example.certificationAuthorization.testStandGB;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.io.ByteArrayInputStream;

public class testStandGBTest {

    WebDriver driver;


    @RegisterExtension
    JunitExtension testWatcher = new JunitExtension();


    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new EventFiringDecorator(new AdditionalLogger()).decorate(new ChromeDriver());
        driver.get("https://test-stand.gb.ru/login");
        driver.manage().window().fullscreen();
    }

    @AfterEach
    void killbrowser() {
        testWatcher.setScreenshot(new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        driver.quit();
    }


    @Test
    @Feature("Авторизация")
    // @TmsLink("ССылка на тест-кейс")
    // @Issue(" Ссылка на Jira1")
    @DisplayName("Позитивный тест. Проверка удачной авторизации пользователя")
    void checkAuthorizationTest() {
        new testStandGB(driver)
                .login("test1test2", "beff3fcba5")
                .checkAuthorization();
    }

    @Test
    @Feature("Авторизация")
    // @TmsLink("ССылка на тест-кейс")
    // @Issue(" Ссылка на Jira1")
    @DisplayName("Негативный тест. Проверка неудачной авторизации пользователя")
    void checkFailAuthorizationTest() {
        new testStandGB(driver)
                .login("FailLogin", "FailPassword")
                .checkFailAuthoruzation();
    }

    @Test
    @Feature("Авторизация")
    // @TmsLink("ССылка на тест-кейс")
    // @Issue(" Ссылка на Jira1")
    @DisplayName("Позитивный тест. Проверка, что пользователь после успешной авторизации попадает на страницу со своими постами")
    void checkSiteAfterAuthoruzationTest() {
        new testStandGB(driver)
                .login("test1test2", "beff3fcba5")
                .checkSiteAfterAuthoruzation();
    }

    @Test
    @Feature("Страница 'Мои посты'")
    // @TmsLink("ССылка на тест-кейс")
    // @Issue(" Ссылка на Jira1")
    @DisplayName("Проверка, что первый пост имеет картинку")
    void checkImageTest() {
        new testStandGB(driver)
                .login("test1test2", "beff3fcba5")
                .checkImage();
    }

    @Test
    @Feature("Страница 'Мои посты'")
    // @TmsLink("ССылка на тест-кейс")
    // @Issue(" Ссылка на Jira1")
    @DisplayName("Проверка, что пост имеет название")
    void checkTitleTest() {
        new testStandGB(driver)
                .login("test1test2", "beff3fcba5")
                .checkTitle();
    }

    @Test
    @Feature("Страница 'Мои посты'")
    // @TmsLink("ССылка на тест-кейс")
    // @Issue(" Ссылка на Jira1")
    @DisplayName("Проверка, что при нажатии кнопки 'Next Page' открывается сл. страница со своими постами")
    void checkDescription() {
        new testStandGB(driver)
                .login("test1test2", "beff3fcba5")
                .checkButtonNextPage();
    }

    @Test
    @Feature("Страница 'Мои посты'")
    // @TmsLink("ССылка на тест-кейс")
    // @Issue(" Ссылка на Jira1")
    @DisplayName("Проверка, отсутствия постов")
    void checkNoHavePostTest() {
        new testStandGB(driver)
                .login("NoPost", "514b0b5663")
                .checkNoHavePost();
    }

    @Test
    @Feature("Страница 'Мои посты'")
    // @TmsLink("ССылка на тест-кейс")
    // @Issue(" Ссылка на Jira1")
    @DisplayName("Проверка, наличия кнопки 'Next Page'")
    void checkHaveButtonNextPageTest() {
        new testStandGB(driver)
                .login("test1test2", "beff3fcba5")
                .checkHaveButtonNextPage();
    }

    @Test
    @Feature("Страница 'Мои посты'")
    // @TmsLink("ССылка на тест-кейс")
    // @Issue(" Ссылка на Jira1")
    @DisplayName("Проверка, наличия кнопки 'Next Page'")
    void checkHaveButtonPreviousPageTest() {
        new testStandGB(driver)
                .login("test1test2", "beff3fcba5")
                .checkHaveButtonPreviousPage();
    }

    @Test
    @Feature("Страница 'Мои посты'")
    // @TmsLink("ССылка на тест-кейс")
    // @Issue(" Ссылка на Jira1")
    @DisplayName("Проверка, что у первого поста отображается описание")
    void checkDescriptionTest() {
        new testStandGB(driver)
                .login("test1test2", "beff3fcba5")
                .checkDescription();
    }


}
