package com.sample.test.demo.tests;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.base.LocalDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sample.test.demo.base.TestBasePage;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.pages.HomePage;
import com.sample.test.demo.utils.Helper;
import java.io.IOException;
import java.util.*;

public class DemoTest extends TestBasePage {

    Helper helper=new Helper();
    HomePage homepage;
    @Test(groups ={"Smoke"})
    public void SuccessfulNoTopingOrder() {
        //This test is for giving a successful pizza order with no topping
        //Name and phone numbers are checked for integrity
        //Total cost is checked with the expected value
        try {
            Map<String, String> entryData = new HashMap<>();
            entryData.put("quantity", "1");
            entryData.put("name", "Mustafa");
            entryData.put("email", "kucukkal@gmail.com");
            entryData.put("phone", "7036891476");
            Map<String, String> pizzaDetails = new HashMap<>();
            pizzaDetails.put("pizzaChoice", PizzaTypes.SMALL_NOTOPPINGS.getDisplayName());
            pizzaDetails.put("pizzaCost", String.valueOf(PizzaTypes.SMALL_NOTOPPINGS.getCost()));
            pizzaDetails.put("topping1", null);
            pizzaDetails.put("topping2", null);
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData);
            String totalCost=homepage.checkPizzaCost(entryData.get("quantity"),pizzaDetails.get("pizzaCost"));
            homepage.choosePaymentType("Credit",null);
            homepage.buttonClick("Order", entryData.get("phone"));
            homepage.compareOrderDetails(pizzaDetails.get("pizzaChoice"),totalCost,valuesToBeChecked);
        } catch (Exception e) {
            Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Smoke"})
    public void SuccessfulOneToppingOrder()   {
            //This test is for giving a successful pizza order with one topping
            //Name and phone numbers are checked for integrity
            //Total cost is checked with the expected value
        try {
            Map<String, String> entryData1 = new HashMap<>();
            entryData1.put("quantity", "1");
            entryData1.put("name", "Mustafa");
            entryData1.put("email", "kucukkal@gmail.com");
            entryData1.put("phone", "7036891476");
            Map<String, String> pizzaDetails1 = new HashMap<>();
            pizzaDetails1.put("pizzaChoice", PizzaTypes.SMALL_ONETOPPINGS.getDisplayName());
            pizzaDetails1.put("pizzaCost", String.valueOf(PizzaTypes.SMALL_ONETOPPINGS.getCost()));
            pizzaDetails1.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails1.put("topping2", null);
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails1);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData1);
            String totalCost=homepage.checkPizzaCost(entryData1.get("quantity"),pizzaDetails1.get("pizzaCost"));
            homepage.choosePaymentType("Cash",null);
            homepage.buttonClick("Order", entryData1.get("phone"));
            homepage.compareOrderDetails(pizzaDetails1.get("pizzaChoice"),totalCost,valuesToBeChecked);

        } catch (Exception e) {
                Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Smoke"})
    public void SuccessfulTwoTopingOrder()   {
            //This test is for giving a successful pizza order with two toppings
            // Name and phone numbers are checked for integrity
            //Total cost is checked with the expected value
        try{
            Map<String,String> entryData2=new HashMap<>();
            entryData2.put("quantity","1");
            entryData2.put("name","Mustafa");
            entryData2.put("email","kucukkal@gmail.com");
            entryData2.put("phone","7036891476");
            Map<String,String> pizzaDetails2=new HashMap<>();
            pizzaDetails2.put("pizzaChoice",PizzaTypes.LARGE_TWOTOPPINGS.getDisplayName());
            pizzaDetails2.put("pizzaCost",String.valueOf(PizzaTypes.LARGE_TWOTOPPINGS.getCost()));
            pizzaDetails2.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails2.put("topping2",PizzaToppings.ONIONS.getDisplayName());
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails2);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData2);
            String totalCost=homepage.checkPizzaCost(entryData2.get("quantity"),pizzaDetails2.get("pizzaCost"));
            homepage.choosePaymentType("Credit",null);
            homepage.buttonClick("Order",entryData2.get("phone"));
            homepage.compareOrderDetails(pizzaDetails2.get("pizzaChoice"),totalCost,valuesToBeChecked);
        } catch (Exception e) {
                    Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Smoke"})
    public void SuccessfulMultiplePizzaOrder()   {
        //This test is for giving a successful order for three pizza with the same two toppings
        //Name and phone numbers are checked for integrity
        //Total cost is checked with the expected value
        try {
            Map<String, String> entryData3 = new HashMap<>();
            entryData3.put("quantity", "3");
            entryData3.put("name", "Mustafa");
            entryData3.put("email", "kucukkal@gmail.com");
            entryData3.put("phone", "7036891476");
            Map<String, String> pizzaDetails3 = new HashMap<>();
            pizzaDetails3.put("pizzaChoice", PizzaTypes.LARGE_TWOTOPPINGS.getDisplayName());
            pizzaDetails3.put("pizzaCost", String.valueOf(PizzaTypes.LARGE_TWOTOPPINGS.getCost()));
            pizzaDetails3.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails3.put("topping2", PizzaToppings.ONIONS.getDisplayName());
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails3);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData3);
            String totalCost=homepage.checkPizzaCost(entryData3.get("quantity"),pizzaDetails3.get("pizzaCost"));
            homepage.choosePaymentType("Cash",null);
            homepage.buttonClick("Order",entryData3.get("phone"));
            homepage.compareOrderDetails(pizzaDetails3.get("pizzaChoice"),totalCost,valuesToBeChecked);
        } catch (Exception e)
        {
            Assert.fail("Exception caught"+e);
        }


    }
    @Test(groups ={"Defect"})
    public void ChoosingBothPaymentOptions()   {
        //This test is for checking if we can choose multiple payment options for pizza order with two toppings
        //Name and phone numbers are checked for integrity
        //Total cost is checked with the expected value, although this test failing it should pass.
        //The defect priority: High
        try {
            Map<String, String> entryData4 = new HashMap<>();
            entryData4.put("quantity", "1");
            entryData4.put("name", "Mustafa");
            entryData4.put("email", "kucukkal@gmail.com");
            entryData4.put("phone", "7036891476");
            Map<String, String> pizzaDetails4 = new HashMap<>();
            pizzaDetails4.put("pizzaChoice", PizzaTypes.LARGE_TWOTOPPINGS.getDisplayName());
            pizzaDetails4.put("pizzaCost", String.valueOf(PizzaTypes.LARGE_TWOTOPPINGS.getCost()));
            pizzaDetails4.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails4.put("topping2", PizzaToppings.ONIONS.getDisplayName());
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails4);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData4);
            String totalCost=homepage.checkPizzaCost(entryData4.get("quantity"),pizzaDetails4.get("pizzaCost"));
            homepage.choosePaymentType("Credit","Cash");
            homepage.buttonClick("Order",entryData4.get("phone"));
            homepage.checkTheDialogueBoxForDefect();
        } catch (Exception e) {
            //test.get().log(Status.FAIL,e,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
            Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Defect"})
    public void MissingPaymentOptions()   {
        //This test is for checking if we can order pizza with two toppings without any payment information
        //Name and phone numbers are checked for integrity
        //Total cost is checked with the expected value, although this test failing it should pass.
        //The defect priority: High
        try {
            Map<String, String> entryData4 = new HashMap<>();
            entryData4.put("quantity", "1");
            entryData4.put("name", "Mustafa");
            entryData4.put("email", "kucukkal@gmail.com");
            entryData4.put("phone", "7036891476");
            Map<String, String> pizzaDetails4 = new HashMap<>();
            pizzaDetails4.put("pizzaChoice", PizzaTypes.LARGE_TWOTOPPINGS.getDisplayName());
            pizzaDetails4.put("pizzaCost", String.valueOf(PizzaTypes.LARGE_TWOTOPPINGS.getCost()));
            pizzaDetails4.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails4.put("topping2", PizzaToppings.ONIONS.getDisplayName());
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails4);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData4);
            String totalCost=homepage.checkPizzaCost(entryData4.get("quantity"),pizzaDetails4.get("pizzaCost"));
            homepage.buttonClick("Order",entryData4.get("phone"));
            homepage.checkTheDialogueBoxForDefect();
        } catch (Exception e) {
            //test.get().log(Status.FAIL,e,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
            Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Defect"})
    public void OrderingNoPizza()    {
        //This test is for checking if we can order 0 pizza with two toppings
        //Name and phone numbers are checked for integrity
        //Total cost is checked with the expected value, although this test failing it should pass.
        //The defect priority: High
        try {
            Map<String, String> entryData5 = new HashMap<>();
            entryData5.put("quantity", "0");
            entryData5.put("name", "Mustafa");
            entryData5.put("email", "kucukkal@gmail.com");
            entryData5.put("phone", "7036891476");
            Map<String, String> pizzaDetails5 = new HashMap<>();
            pizzaDetails5.put("pizzaChoice", PizzaTypes.LARGE_TWOTOPPINGS.getDisplayName());
            pizzaDetails5.put("pizzaCost", String.valueOf(PizzaTypes.LARGE_TWOTOPPINGS.getCost()));
            pizzaDetails5.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails5.put("topping2", PizzaToppings.ONIONS.getDisplayName());
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails5);
            homepage.enterCustomerDetails(entryData5);
            String totalCost=homepage.checkPizzaCost(entryData5.get("quantity"),pizzaDetails5.get("pizzaCost"));
            homepage.choosePaymentType("Cash",null);
            homepage.buttonClick("Order", entryData5.get("phone"));
            homepage.checkTheDialogueBoxForDefect();
        } catch (Exception e) {
            Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Defect"})
    public void CheckingValuesAfterReset()   {
        //This test is for checking if all the values on the page returns the default values after hitting the reset button
        //Name and phone numbers are checked for integrity
        //Total cost is checked with the expected value, although this test failing it should pass.
        //The defect priority: High
        try {
            Map<String, String> entryData6 = new HashMap<>();
            entryData6.put("quantity", "1");
            entryData6.put("name", "Mustafa");
            entryData6.put("email", "kucukkal@gmail.com");
            entryData6.put("phone", "7036891476");
            Map<String, String> pizzaDetails6 = new HashMap<>();
            pizzaDetails6.put("pizzaChoice", PizzaTypes.LARGE_TWOTOPPINGS.getDisplayName());
            pizzaDetails6.put("pizzaCost", String.valueOf(PizzaTypes.LARGE_TWOTOPPINGS.getCost()));
            pizzaDetails6.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails6.put("topping2", PizzaToppings.ONIONS.getDisplayName());
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails6);
            homepage.enterCustomerDetails(entryData6);
            String totalCost=homepage.checkPizzaCost(entryData6.get("quantity"),pizzaDetails6.get("pizzaCost"));
            homepage.choosePaymentType("Cash",null);
            homepage.buttonClick("Reset",null);
            homepage.compareDefaultValuesAfterReset();
        } catch (Exception e) {
           Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Smoke"})
    public void MissingCustomerInformation()  {
        //This test is for checking the error messages (for missing name or/and phone number) during ordering pizza with two toppings
        //Name and phone numbers are checked for integrity
        //Total cost is checked with the expected value. Both error messages are checked and also pizza can be ordered after successful closing and fixing the problems

        try {
            Map<String, String> entryData7 = new HashMap<>();
            entryData7.put("quantity", "1");
            entryData7.put("name", "Mustafa");
            entryData7.put("email", "kucukkal@gmail.com");
            entryData7.put("phone", null);
            Map<String, String> pizzaDetails7 = new HashMap<>();
            pizzaDetails7.put("pizzaChoice", PizzaTypes.LARGE_TWOTOPPINGS.getDisplayName());
            pizzaDetails7.put("pizzaCost", String.valueOf(PizzaTypes.LARGE_TWOTOPPINGS.getCost()));
            pizzaDetails7.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails7.put("topping2", PizzaToppings.ONIONS.getDisplayName());
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails7);
            homepage.enterCustomerDetails(entryData7);
            String totalCost = homepage.checkPizzaCost(entryData7.get("quantity"), pizzaDetails7.get("pizzaCost"));
            List<String> missingValue = new ArrayList<>();
            missingValue.add("phone number");
            homepage.buttonClick("Order", null);
            homepage.checkErrorMessages(missingValue);
            homepage.name.clear();
            missingValue.remove(0);
            missingValue.add("name");
            homepage.enterValueToTextBox("phone", "7038691476");
            homepage.buttonClick("Order", null);
            homepage.checkErrorMessages(missingValue);
            missingValue.add("phone number");
            homepage.phone.clear();
            homepage.buttonClick("Order", null);
            homepage.checkErrorMessages(missingValue);
            homepage.enterValueToTextBox("name", "Mustafa");
            homepage.enterValueToTextBox("phone", "7038691476");
            homepage.buttonClick("Order", null);
            List<String> valuesToBeChecked=new ArrayList<>(){{add("Mustafa");add("7038691476");}};
            homepage.compareOrderDetails(pizzaDetails7.get("pizzaChoice"), totalCost,valuesToBeChecked);
       } catch (Exception e) {
            Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Defect"})
    public void CheckingTheNameOfCustomer()  {
        //This test is for checking if the name of the customer contains invalid characters
        //Total cost is checked with the expected value, although this test failing it should pass.
        //The defect priority: High
        try {

            Map<String, String> entryData8 = new HashMap<>();
            entryData8.put("quantity", "1");
            entryData8.put("name", "Mustafa%^&");
            entryData8.put("email", "kucukkal@gmail.com");
            entryData8.put("phone", "7036891476");
            Map<String, String> pizzaDetails8 = new HashMap<>();
            pizzaDetails8.put("pizzaChoice", PizzaTypes.LARGE_TWOTOPPINGS.getDisplayName());
            pizzaDetails8.put("pizzaCost", String.valueOf(PizzaTypes.LARGE_TWOTOPPINGS.getCost()));
            pizzaDetails8.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails8.put("topping2", PizzaToppings.ONIONS.getDisplayName());
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails8);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData8);
            String totalCost = homepage.checkPizzaCost(entryData8.get("quantity"), pizzaDetails8.get("pizzaCost"));
            homepage.buttonClick("Order",entryData8.get("phone"));
            homepage.compareOrderDetails(pizzaDetails8.get("pizzaChoice"), totalCost,valuesToBeChecked);

        }catch (Exception e)
        {
            Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Defect"})
    public void CheckingThePhoneNumberOfCustomer()  {
        //This test is for checking if the name of the customer contains invalid characters
        //Name is checked for integrity
        //Total cost is checked with the expected value, this test is passing, since name came can be considered as a second important detail in the requirements that I have gathered (based on personal experience).
        //The defect priority: Medium
        try {
            Map<String, String> entryData9 = new HashMap<>();
            entryData9.put("quantity", "1");
            entryData9.put("name", "Mustafa");
            entryData9.put("email", "kucukkal@gmail.com");
            entryData9.put("phone", "7036891%$^");
            Map<String, String> pizzaDetails9 = new HashMap<>();
            pizzaDetails9.put("pizzaChoice", PizzaTypes.LARGE_TWOTOPPINGS.getDisplayName());
            pizzaDetails9.put("pizzaCost", String.valueOf(PizzaTypes.LARGE_TWOTOPPINGS.getCost()));
            pizzaDetails9.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails9.put("topping2", PizzaToppings.ONIONS.getDisplayName());
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails9);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData9);
            String totalCost = homepage.checkPizzaCost(entryData9.get("quantity"), pizzaDetails9.get("pizzaCost"));
            homepage.buttonClick("Order",entryData9.get("phone"));
            homepage.compareOrderDetails(pizzaDetails9.get("pizzaChoice"), totalCost,valuesToBeChecked);

        }catch (Exception e)
        {
            Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Defect"})
    public void CheckingTheToppingForNoToppingPizza ()  {
        //This test is for checking if the phone number of the customer contains invalid characters or number of digits is not equal to 10
        //Total cost is checked with the expected value, although this test failing it should pass.
        //The defect priority: High
        try {
            Map<String, String> entryData10 = new HashMap<>();
            entryData10.put("quantity", "1");
            entryData10.put("name", "Mustafa");
            entryData10.put("email", "kucukkal@gmail.com");
            entryData10.put("phone", "7036891476");
            Map<String, String> pizzaDetails10 = new HashMap<>();
            pizzaDetails10.put("pizzaChoice", PizzaTypes.LARGE_NOTOPPINGS.getDisplayName());
            pizzaDetails10.put("pizzaCost", String.valueOf(PizzaTypes.LARGE_NOTOPPINGS.getCost()));
            pizzaDetails10.put("topping1", PizzaToppings.MANGOS.getDisplayName());
            pizzaDetails10.put("topping2", PizzaToppings.ONIONS.getDisplayName());
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails10);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData10);
            String totalCost = homepage.checkPizzaCost(entryData10.get("quantity"), pizzaDetails10.get("pizzaCost"));
            homepage.buttonClick("Order",entryData10.get("phone"));
            homepage.checkTheDialogueBoxForDefect();

        }catch (Exception e)
        {
            Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Defect"})
    public void CheckingTheMissingToppingForOneToppingPizza ()  {
        //This test is for checking if missing the topping in the order will affect its status.
        //Small pizza with one topping is ordered but no topping is chosen.
        //Total cost is checked with the expected value, although this test failing it should pass.
        //The defect priority: High
        try {
            Map<String, String> entryData11 = new HashMap<>();
            entryData11.put("quantity", "1");
            entryData11.put("name", "Mustafa");
            entryData11.put("email", "kucukkal@gmail.com");
            entryData11.put("phone", "7036891476");
            Map<String, String> pizzaDetails11 = new HashMap<>();
            pizzaDetails11.put("pizzaChoice", PizzaTypes.SMALL_ONETOPPINGS.getDisplayName());
            pizzaDetails11.put("pizzaCost", String.valueOf(PizzaTypes.SMALL_ONETOPPINGS.getCost()));
            pizzaDetails11.put("topping1", null);
            pizzaDetails11.put("topping2", null);
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails11);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData11);
            String totalCost = homepage.checkPizzaCost(entryData11.get("quantity"), pizzaDetails11.get("pizzaCost"));
            homepage.buttonClick("Order",entryData11.get("phone"));
            homepage.checkTheDialogueBoxForDefect();

        }catch (Exception e)
        {
            Assert.fail("Exception caught" + e);
        }
    }
    @Test(groups ={"Defect"})
    public void CheckingTheMissingToppingsForTwoToppingPizza ()  {
        //This test is for checking if missing the toppings in the order will affect its status.
        //Medium pizza with two toppings is ordered but no toppings is chosen.
        //Total cost is checked with the expected value, although this test failing it should pass.
        //The defect priority: High
        try{
            Map<String, String> entryData12 = new HashMap<>();
            entryData12.put("quantity", "1");
            entryData12.put("name", "Mustafa");
            entryData12.put("email", "kucukkal@gmail.com");
            entryData12.put("phone", "7036891476");
            Map<String, String> pizzaDetails12 = new HashMap<>();
            pizzaDetails12.put("pizzaChoice", PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName());
            pizzaDetails12.put("pizzaCost", String.valueOf(PizzaTypes.MEDIUM_TWOTOPPINGS.getCost()));
            pizzaDetails12.put("topping1", null);
            pizzaDetails12.put("topping2", null);
            WebDriver driver=LocalDriver.getInstance().getDriver();
            HomePage homepage=new HomePage(driver);
            homepage.enterPizzaOrderDetails(pizzaDetails12);
            List<String> valuesToBeChecked=homepage.enterCustomerDetails(entryData12);
            String totalCost = homepage.checkPizzaCost(entryData12.get("quantity"), pizzaDetails12.get("pizzaCost"));
            homepage.buttonClick("Order",entryData12.get("phone"));
            homepage.checkTheDialogueBoxForDefect();

        }catch (Exception e)
        {
           Assert.fail("Exception caught" + e);
        }
    }


}
