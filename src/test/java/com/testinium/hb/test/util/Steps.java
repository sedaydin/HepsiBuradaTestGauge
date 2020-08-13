package com.testinium.hb.test.util;

import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Steps extends Methods{

    public static String productName = "";
    public static String detailProductName = "";
    public static String productQuantity = "";
    public static String basketProductQuantity ="";
    public static String totalPrice = "";

    @Step("<name> click element")
    public void clickElement(String name) {
        WebElement element = findElement(name);
        clictToElement(element);
    }

    @Step("<name> random click")
    public void randomClick(String name){
        List<WebElement> elements = findElements(name);
        clictToElement(elements.get(randomInt(name)));
    }

    @Step("<name> value <text>  write")
    public void sendKeysToElementTest(String name, String text) {
        WebElement element = findElement(name);
        element.clear();
        sendKeysToElement(element, text);
    }



    @Step("<name> value <text> same  controll")
    public void getTextControlTest(String key, String text) {
        WebElement element = findElement(key);
        getTextControl(element, text);
    }

    @Step("<name> save text")
    public void detailNameSaveText(String key){
        WebElement element= findElement(key);
        detailProductName = element.getText();
        System.out.println(detailProductName);
    }

    @Step("<name> random text click and save")
    public void SaveTextRandomClick(String name){
        List<WebElement> elements = findElements(name);
        WebElement element = elements.get(randomInt(name)).findElement(By.cssSelector("div[class='product-detail']"));
        productName = element.findElement(By.cssSelector("h3 span")).getText();
        clictToElement(element);
        System.out.println(productName);
    }

    @Step("Sayfadaki div değişikliği kontrol et sonuca göre element seç")
    public void changeControl(){
        if (driver.findElements(By.cssSelector("ul[class='ChildMenuItems-3m2LI']")).size()>0){
            randomClick("ul[class='ChildMenuItems-3m2LI']");
            randomClick("a[class*='ChildMenuItems-1Mjr0 item-']");
        }else {
            randomClick("a[class*='ChildMenuItems-1Mjr0 item-']");
        }
    }

    @Step("Basket Controll ")
    public void basketChangeControl(){
        if (driver.findElements(By.xpath("//a[text()='Sepete git']")).size()>0){
            clickByXpath("//a[text()='Sepete git']");
        }else {
            clickElement("span[id='shoppingCart']");
        }
    }



    @Step("<name> click <number> element")
    public void optionClick(String name,Integer number){
        List<WebElement> elements = findElements(name);
        clictToElement(elements.get(number));
    }

    @Step("<key> come")
    public void mouseHover(String key){
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(key));
        action.moveToElement(element).build().perform();
    }
    @Step("Write Products on cvs ")
    public void productNameWriteCsv() throws IOException {
        FileWriter csvWriter = new FileWriter("ProductName.csv");
        csvWriter.append(productName);
        csvWriter.flush();
        csvWriter.close();
    }

    @Step("<name> price write to cvs")
    public void totalPriceWriteCsv(String name) throws IOException {
        WebElement element= findElement(name);
        totalPrice=element.getText();
        FileWriter csvWriter = new FileWriter("totalPrice.csv");
        csvWriter.append(totalPrice);
        csvWriter.flush();
        csvWriter.close();
    }

    @Step("Ürünün texti ile detay sayfasındaki ürünün textini karşılaştır")
    public void compareName(){
        Assert.assertEquals("Textler uyuşmuyor", productName,detailProductName);
    }

    @Step("<key> li elementin attribute'ünü kaydet")
    public void saveAttribute(String name){
        WebElement element=findElement(name);
        productQuantity=element.getAttribute("value");
    }

    @Step("Detay sayfasındaki <key> li elementin attribute'ünü kaydet")
    public void cartQuantitySaveAttribute(String name){
        WebElement element=findElement(name);
        basketProductQuantity = element.getAttribute("value");
        Assert.assertEquals("Ürün miktarları uyuşmuyor",productQuantity,basketProductQuantity);
    }

    @Step("<xpath> li elemente xpath ile tıkla")
    public void clickByXpath(String xpath){
        WebElement element = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        scrollToElement(element);
        element.click();
    }

}
