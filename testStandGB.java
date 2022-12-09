package org.example.certificationAuthorization;


import io.qameta.allure.Step;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



import java.util.List;


public class testStandGB extends basePage {

    public testStandGB(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[contains(.,'Username')]")
    private WebElement userNameField;

    @FindBy(xpath = "//label[contains(.,'Password')]")
    private WebElement passwordField;

    @FindBy(xpath = "//span[@class='mdc-button__label']")
    private WebElement buttonLogin;

    @FindBy(xpath = "//div[@class='posts svelte-127jg4t']")
    private List<WebElement> postList;

    @FindBy(xpath = "//a[contains(.,'Next Page')]")
    private WebElement buttonNextPage;
    @FindBy(xpath = "//a[@class='svelte-d01pfs disabled'][contains(.,'Previous Page')]")
    private WebElement buttonPreviousPage;

    @Step("Авторизация")
    public testStandGB login(String login, String password) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='mdc-button__label']")));
        userNameField.sendKeys(login);
        passwordField.sendKeys(password);
        buttonLogin.click();
        return new testStandGB(driver);
    }

    @Step("Проверка успешной Авторизации")
    public void checkAuthorization() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='svelte-1e9zcmy'][contains(.,'Blog')]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//a[contains(.,'Hello, test1test2')]")).isDisplayed());
    }

    @Step("Проверка неудачной Авторизации")
    public void checkFailAuthoruzation() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(.,'Invalid credentials.')]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//p[contains(.,'Invalid credentials.')]")).isDisplayed());
    }

    @Step("Проверка, что пользователь после успешной авторизации попадает на страницу со своими постами")
    public void checkSiteAfterAuthoruzation() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='svelte-1e9zcmy'][contains(.,'Blog')]")));
        Assertions.assertTrue(driver.getCurrentUrl().equals("https://test-stand.gb.ru/"));
    }

    //Я хотел что бы код искал из списка элементы с тэгом img и в случии их наличия тест бы проходил успешно, говоря тем самым, что картинка(превью) присутствует и также с описанием и названием поста заменяя просто тэги, но при отсуствии постов тесты всеравно проходят успешно и я не знаю как тут можна изменить или в самом списке я не правильно достаю элементы или еще в чем проблема
    /*@Step("Проверка, что у постов есть картинка, а случии ее отсутствие присутствует заглушка")
    public void checkImage() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='svelte-1e9zcmy'][contains(.,'Blog')]")));
        for (WebElement image : postList) {
            Assertions.assertTrue(image.findElement(By.tagName("img")).isDisplayed());
        }
    }*/
    @Step("Проверка, что у  первого поста отображается картинка")
    public void checkImage() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='svelte-1e9zcmy'][contains(.,'Blog')]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//img[@src='http://test-stand.gb.ru/files/public/image/d31093cf15d914757a472055c460bb9f.png']")).isDisplayed());
        }

    @Step("Проверка, что у первого поста отображается название  поста")
    public void checkTitle() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='svelte-1e9zcmy'][contains(.,'Blog')]")));
      Assertions.assertTrue(driver.findElement(By.xpath("//h2[contains(.,'Title')]")).isDisplayed());
    }
    @Step("Проверка, что у первого поста отображается описание")
    public void checkDescription() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='svelte-1e9zcmy'][contains(.,'Blog')]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='description svelte-127jg4t'][contains(.,'Description')]")).isDisplayed());
    }
    @Step("Проверка, что при нажатии кнопки 'Next Page' открывается сл. страница со своими постами")
    public void checkButtonNextPage() {

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Blog')]")));
        buttonNextPage.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//img[@src='/placeholder/800x600.gif'])[1]")));
        Assertions.assertTrue(driver.getCurrentUrl().equals("https://test-stand.gb.ru/?page=2"));
    }
    @Step("Проверка отсутствия постов")
    public void checkNoHavePost(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='svelte-1e9zcmy'][contains(.,'Blog')]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//p[contains(.,'No items for your filter')]")).isDisplayed());

    }

    @Step("Проверка, наличии кнопки 'Next Page'")
    public void checkHaveButtonNextPage() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Home')]")));
        Assertions.assertTrue(buttonNextPage.isDisplayed());
    }

    @Step("Проверка, наличии кнопки 'Previous Page'")
    public void checkHaveButtonPreviousPage() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Home')]")));
        Assertions.assertTrue(buttonPreviousPage.isDisplayed());
    }


}
