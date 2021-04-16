package com.sample.test.demo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public void waitForElementToBeDisplayed(WebDriver driver,WebElement element, int n)
    {
        //explicit wait for an element to be displayed for n seconds
        WebDriverWait wait= new WebDriverWait(driver, n);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void selectFromDropdownMenu(String text, WebElement element){
        //selecting an option from a dropdown menu using value
        Select menu= new Select(element);
        menu.selectByValue(text);

    }
    public String calculateTotalCost(String numberOfOrders, String eachPizzaCost)
    {
        //to calculate the total cost of an order
        double totalCost=Integer.valueOf(numberOfOrders)*Double.valueOf(eachPizzaCost);
        return String.valueOf(totalCost);
    }
    public String defaultDropdownMenu(WebElement element){
        //selecting the default option from a dropdown menu using value
        Select menu= new Select(element);
        return menu.getFirstSelectedOption().getText();

    }
    public void waitForInputToBeEntered(WebDriver driver,WebElement element, String attribute, String value,int n)
    {
        //explicit wait for the attribute of an element to be  certain value for n seconds
        WebDriverWait wait= new WebDriverWait(driver, n);
        wait.until(ExpectedConditions.attributeToBe(element, attribute,value));
    }
    public boolean checkName(String name){
        //checks the name for special characters
        Pattern letter=Pattern.compile("[a-zA-z]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher checkLetter=letter.matcher(name);
        Matcher checkSpecial=special.matcher(name);
        return checkLetter.find() && !checkSpecial.find();
    }
    public boolean checkPhoneNumber(String phone){
        //checks the phone number for special characters  and letters
        //checks the number of phone number's digit to be 10
        if(phone.length()==10) {
            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            Matcher checkLetter = letter.matcher(phone);
            Matcher checkdigit = digit.matcher(phone);
            Matcher checkSpecial = special.matcher(phone);
            return !checkLetter.find() && !checkSpecial.find() && checkdigit.find();
        }
        else
            return false;
    }
}
