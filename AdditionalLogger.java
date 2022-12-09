package org.example.Logging;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class AdditionalLogger implements WebDriverListener {


    public void beforeFindElement(WebDriver driver, By locator) {
        Allure.step("Ищем элемент по локатору" + locator);
    }

}