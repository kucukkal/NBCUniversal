package com.sample.test.demo.pages;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.sample.test.demo.base.TestBasePage;
import com.sample.test.demo.base.LocalDriver;
import com.sample.test.demo.constants.CustomWarnings;
import com.sample.test.demo.utils.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HomePage<quantity> extends TestBasePage{
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver=LocalDriver.getInstance().getDriver();
        PageFactory.initElements(LocalDriver.getInstance().getDriver(),this);
    }
    Helper helper=new Helper();
    CustomWarnings warnings=new CustomWarnings();

    @FindBy(how = How.ID, using = "pizza1Pizza")
    public WebElement pizzaChoice;
    @FindBy(how = How.XPATH, using = "//div[@id='pizza1']//select[@class='toppings1']")
    public WebElement topping1;
    @FindBy(how = How.XPATH, using = "//div[@id='pizza1']//select[@class='toppings2']")
    public WebElement topping2;
    @FindBy(how = How.ID, using = "pizza1Qty")
    public WebElement quantity;
    @FindBy(how = How.ID, using = "pizza1Cost")
    public WebElement cost;
    @FindBy(how = How.ID, using = "ccpayment")
    public WebElement ccard;
    @FindBy(how = How.ID, using = "cashpayment")
    public WebElement cash;
    @FindBy(how = How.ID, using = "email")
    public WebElement email;
    @FindBy(how = How.ID, using = "name")
    public WebElement name;
    @FindBy(how = How.ID, using = "phone")
    public WebElement phone;
    @FindBy(how = How.ID, using = "placeOrder")
    public WebElement placeOrder;
    @FindBy(how = How.ID, using = "reset")
    public WebElement reset;
    @FindBy(how = How.ID, using = "dialog")
    public WebElement dialog;
    @FindBy(how = How.XPATH, using = "//div[@id='dialog']/p")
    public WebElement dialogText;
    @FindBy(how = How.XPATH, using = "//button[@type='button' and @title='Close']")
    public WebElement closeDialogBox;

    public void choosePizza(String text)
    {
       helper.selectFromDropdownMenu(text,pizzaChoice);
    }
    public void chooseTopping1(String text)
    {
        helper.selectFromDropdownMenu(text,topping1);
    }
    public void chooseTopping2(String text)
    {
        helper.selectFromDropdownMenu(text,topping2);
    }
    //Three methods on the top are used to choose pizza and toppings
    public void enterValueToTextBox(String textBoxName,String text)
    {
        textBox(textBoxName).clear();
        textBox(textBoxName).sendKeys(text);
    }
    public WebElement textBox(String option)
    {
        //Setting the WebElement based on the option to be used for text entry
        WebElement element=null;
        switch(option.toLowerCase()){
            case "quantity":
                element=quantity;
                break;
            case "name":
                element=name;
            break;
            case "email":
                element=email;
            break;
            case "phone":
                element=phone;
            break;
        }
        return element;
    }
    public void choosePaymentType(String option1, String option2) {
        //Clicking the payment option based on the choice
        Map<String, WebElement> elementMap=new Hashtable<>();
        elementMap.put("cash",cash);
        elementMap.put("credit",ccard);
        if(option1!=null) {
            elementMap.get(option1.toLowerCase()).click();
        }
        if(option2!=null) {
            elementMap.get(option2.toLowerCase()).click();
        }
        Assert.assertFalse(option1.equalsIgnoreCase(option2),"Payment options can't be the same");
        if((option1==null && option2==null) || (option1!=null && option2!=null)) {
            try {
                if(option1==null && option2==null)
                    test.get().log(Status.INFO, "Missing payment information");
                else
                  test.get().log(Status.INFO, "Both payment options are selected");
            }catch (Exception e){}
        }
    }
    public void buttonClick(String option, String elementValue) throws IOException {
        //Clicking the "Place Order" or "Reset" Buttons
        //Wait is needed since "Place Order" is clicked before inserting value to textboxes
        if(elementValue!=null)
            helper.waitForInputToBeEntered(driver,phone,"value",elementValue,3);
        test.get().log(Status.INFO,option+" button will be clicked",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
        WebElement element=option.equalsIgnoreCase("Order") ? placeOrder : reset;
        element.click();
    }
    public List<String> getDialogueText()
    {
        //Get the content of dialogue box after a successful order or an order missing name and/or phone numbers (Error messages)
        String dialogBoxText=dialogText.getText();
        List<String> list= Arrays.stream(dialogBoxText.split(":")).map(x->x.trim()).collect(Collectors.toList());
        return list;
    }
    public String getPizzaCost() {
        //return the total cost of pizza ordered from Cost text box
        return cost.getAttribute("value");
    }
    public String checkPizzaCost(String pizzaOrdered,String pizzaCost)
    {
        //compares the expected cost of the order with the value shown on Cost text box
        helper.waitForInputToBeEntered(driver,quantity,"value",pizzaOrdered,3);
        String numberofPizzaOrdered=quantity.getAttribute("value");
        helper.calculateTotalCost(numberofPizzaOrdered, pizzaCost);
        String totalCost=cost.getAttribute("value");
        if(pizzaOrdered !="0")
            Assert.assertEquals(totalCost,helper.calculateTotalCost(numberofPizzaOrdered, pizzaCost));
        else
            Assert.assertEquals(totalCost,"0");
        return totalCost;
    }
    public List<String> enterCustomerDetails(Map<String, String> entryData) {
        //Enters the customer details and the quantity of the pizza in the order to the necessary text boxes
        for (Map.Entry<String, String> entry : entryData.entrySet()) {
            if(entry.getValue()!=null)
                enterValueToTextBox(entry.getKey(), entry.getValue());
            test.get().log(Status.INFO, entry.getKey() + " has been entered");
        }
        List<String> valuesToBeChecked=new ArrayList<>(){{add(entryData.get("name"));add(entryData.get("phone"));}};
        return valuesToBeChecked;
    }
    public void enterPizzaOrderDetails(Map<String,String> pizzaDetails)
    {
        //choose the pizza details in the necessary text boxes
        choosePizza(pizzaDetails.get("pizzaChoice"));
        test.get().log(Status.INFO,pizzaDetails.get("pizzaChoice")+" has been selected");
        if(pizzaDetails.get("topping1")!=null) {
            chooseTopping1(pizzaDetails.get("topping1"));
            test.get().log(Status.INFO,pizzaDetails.get("topping1")+" has been added");
        }
        else
            test.get().log(Status.INFO,"No topping as the first topping has been selected");

        if(pizzaDetails.get("topping2")!=null) {
            chooseTopping2(pizzaDetails.get("topping2"));
            test.get().log(Status.INFO,pizzaDetails.get("topping2")+" has been added");
        }else
            test.get().log(Status.INFO,"No topping as the second topping has been selected");

    }
    public void compareOrderDetails(String pizzaChoice,String totalCost, List<String>valuesToBeChecked) {
        //compares the order details with details in the dialogue box displayed for a successful order
        Assert.assertTrue(dialogBoxAppear());
        test.get().log(Status.INFO,"Dialog box appeared");
        List<String> enteredValues = new ArrayList<String>() {{
                add(warnings.DIALOGUE_BOX_GREETING);
                add(totalCost+" "+pizzaChoice);
        }};
        Assert.assertEquals(getDialogueText(), enteredValues);
        checkValueForIntegrity(valuesToBeChecked);
        test.get().log(Status.INFO,"One successful order has been placed, one happy face");
    }

    public boolean dialogBoxAppear()
    {
        //checks if dialogue popup is displayed
        helper.waitForElementToBeDisplayed(driver,dialog,3);
        return dialog.isDisplayed();
    }
    public void checkTheDialogueBoxForDefect()
    {
        //checks if the dialogue popup contains the message of a successful order
        dialogBoxAppear();
        List<String> dialogueText=getDialogueText();
        System.out.println("Text= "+dialogueText.get(0));
        Assert.assertFalse(dialogueText.contains(warnings.DIALOGUE_BOX_GREETING),"There should not be an order confirmation");
        test.get().log(Status.INFO,"It should not complete these order, happy face");
    }
    public List<String> checkForTheDefaultValues()
    {
        //get the values of the WebElements after reseting as a list to be used for validation
        List<String> defaultValues=new ArrayList<>();
        defaultValues.add(helper.defaultDropdownMenu(pizzaChoice));
        defaultValues.add(helper.defaultDropdownMenu(topping1));
        defaultValues.add(helper.defaultDropdownMenu(topping2));
        defaultValues.add(String.valueOf(quantity.getAttribute("value")));
        defaultValues.add(String.valueOf(cost.getAttribute("value")));
        defaultValues.add(name.getAttribute("value"));
        defaultValues.add(email.getAttribute("value"));
        defaultValues.add(String.valueOf(phone.getAttribute("value")));
        return defaultValues;
    }
    public List<String> originalDefaultValues()
    {
        //gives the default values of the WebElements as a list to be used for validation
        List<String> defaultValues=new ArrayList<>();
        defaultValues.add("Choose Pizza");
        defaultValues.add("Choose a Toppings 1");
        defaultValues.add("Choose a Toppings 2");
        defaultValues.add("0");
        defaultValues.add("0.00");
        defaultValues.add(null);
        defaultValues.add(null);
        defaultValues.add(null);
        return defaultValues;
    }
    public void compareDefaultValuesAfterReset()
    {
        //checks if all the values of the WebElements has changed to default values after clicking reset button
        List<String> afterReset=checkForTheDefaultValues();
        List<String> expectedValues=originalDefaultValues();
        Assert.assertEquals(afterReset.size(),expectedValues.size());
        test.get().log(Status.INFO,"Number of elements are equal");
        Assert.assertFalse(ccard.isSelected());
        test.get().log(Status.INFO,"Credit card has been resetted");
        Assert.assertFalse(cash.isSelected());
        test.get().log(Status.INFO,"Cash has been resetted");
        for(int i=0; i<afterReset.size();i++)
        {
            Assert.assertEquals(afterReset.get(i),expectedValues.get(i));
            test.get().log(Status.INFO,"Default value of the element that is number "+i+" is OK");
        }

    }
    public void checkErrorMessages(List<String>missingElements) {
        //checking the error messages is displayed
        dialogBoxAppear();
        List<String> errorlist=Arrays.stream(dialogText.getText().split("\n")).map(x->x.trim()).collect(Collectors.toList());
        List<String> expectedlist=missingElements.stream().map(x->"Missing"+" "+x).collect(Collectors.toList());
        Assert.assertEquals(expectedlist,errorlist,"Error messages match");
        test.get().log(Status.INFO,missingElements.toString()+"error messages has been verified");
        closeDialogBox.click();
    }
    public void checkValueForIntegrity(List<String> valuesToBeChecked)
    {
        //checking the name and phone number for integrity
        //name should no contain any special characters
        //phone number should be 10 digits and should not contain any letters and special characters
        Assert.assertTrue(helper.checkName(valuesToBeChecked.get(0)),valuesToBeChecked.get(0)+" is the name of the customer");
        test.get().log(Status.INFO,"Name has been checked");
        Assert.assertTrue(helper.checkPhoneNumber(valuesToBeChecked.get(1)),valuesToBeChecked.get(1)+" is the phone number of the customer");
        test.get().log(Status.INFO,"Phone number has been checked");
    }








}
