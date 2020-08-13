package com.testinium.hb.test.util;

import com.testinium.hb.test.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class Methods extends BaseTest {

    public static String text = "";

    public WebElement findElement(String key) {
        try {
            WebElement element = (new WebDriverWait(driver, 5, 100))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector(key)));
            return element;

        } catch (Exception ex) {
            return null;
        }
    }

    public List<WebElement> findElements(String key) {
        try {
            WebDriverWait waitForFormLabel = new WebDriverWait(driver, 50);
            List<WebElement> elements = driver.findElements(By.cssSelector(key));
            waitForFormLabel.until(ExpectedConditions.visibilityOfAllElements(elements));
            return elements;

        } catch (Exception ex) {
            return null;
        }
    }

    public void clictToElement(WebElement element) {

        scrollToElement(element);
        waitByMilliSeconds(100);
        element.click();
    }

    public void sendKeysToElement(WebElement element, String text) {
        scrollToElement(element);
        waitByMilliSeconds(100);
        element.sendKeys(text);
    }

    public String getTextElement(WebElement element) {
        scrollToElement(element);
        waitByMilliSeconds(100);
        String elemaninTexti = "";
        elemaninTexti = element.getText();
        System.out.println("Text değeri: " + elemaninTexti);
        return elemaninTexti;
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", element);
    }

    public void waitByMilliSeconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getTextControl(WebElement element, String key) {
        Assert.assertEquals("Yok", getTextElement(element), key);
        System.out.println("Değerler uyuşmuyor");
    }

    public int randomInt(String key) {
        List <WebElement> element= findElements(key);

        Random r = new Random();
        int low = 0;
        int high = element.size()-1;
        int result = r.nextInt(high - low) + low;
        System.out.println(result + ". Elemente tıklandı..");
        return result;
    }
}
