import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Alerts extends TestBase {
    private final By SIMPLE_ALERT = By.xpath("//button[@onclick='jsAlert()']");
    private final By CONFIRM_ALERT = By.xpath("//button[@onclick='jsConfirm()']");
    private final By PROMPT_ALERT = By.xpath("//button[@onclick='jsPrompt()']");
    private final By RESULT = By.id("result");



    @Override
    @BeforeTest
    public void setup() {
        super.setup();
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void testSimpleAlert() {

        //Clicking button will show a simple Alert with OK Button
        WebElement button = driver.findElement(SIMPLE_ALERT);
        button.click();

        try {
            //Get the Alert
            Alert alert = driver.switchTo().alert();

            //Get the Text displayed on Alert using getText() method of Alert class
            String textOnAlert = alert.getText();

            //Click OK button, by calling accept() method of Alert Class
            alert.accept();

            //Verify Alert displayed correct message to user
            assertEquals("I am a JS Alert",textOnAlert);

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAlertConfirmOk() {
        WebElement button = driver.findElement(CONFIRM_ALERT);
        button.click();

        try {
            //Get the Alert
            Alert alert = driver.switchTo().alert();

            //Click OK button, by calling accept() method of Alert Class
            alert.accept();

            String resultText = driver.findElement(RESULT).getText();

            //Verify Alert displayed correct message to user
            assertEquals("You clicked: Ok", resultText);

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAlertConfirmCancel() {
        WebElement button = driver.findElement(CONFIRM_ALERT);
        button.click();

        try {
            //Get the Alert
            Alert alert = driver.switchTo().alert();

            //Click OK button, by calling accept() method of Alert Class
            alert.dismiss();

            String resultText = driver.findElement(RESULT).getText();

            //Verify Alert displayed correct message to user
            assertEquals("You clicked: Cancel", resultText);

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAlertPromptOk() {
        String textForAlert = "Here's my message to you";
        WebElement button = driver.findElement(PROMPT_ALERT);
        button.click();

        try {
            //Get the Alert
            Alert alert = driver.switchTo().alert();

            //Click OK button, by calling accept() method of Alert Class
            alert.sendKeys(textForAlert);
            alert.accept();

            String resultText = driver.findElement(RESULT).getText();

            //Verify Alert displayed correct message to user
            assertEquals("You entered: " + textForAlert, resultText);

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAlertPromptCancel() {
        String textForAlert = "Here's my message to you";
        WebElement button = driver.findElement(PROMPT_ALERT);
        button.click();

        try {
            //Get the Alert
            Alert alert = driver.switchTo().alert();

            //Click OK button, by calling accept() method of Alert Class
            alert.sendKeys(textForAlert);
            alert.dismiss();

            String resultText = driver.findElement(RESULT).getText();

            //Verify Alert displayed correct message to user
            assertEquals("You entered: null", resultText);

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }
}
