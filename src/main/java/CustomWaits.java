import base.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertTrue;

public class CustomWaits extends TestBase {
    @Test
    public void testExplicitWait()
    {
        //Go to Sample Application
        driver.get("http://dl.dropbox.com/u/55228056/AjaxDemo.html");
        try {
            //Get the link for Page 4 and click on it, this will call AJAX code
            //for loading the contents for Page 4

            WebElement page4button = driver.findElement(By.linkText("Page 4"));
            page4button.click();

            WebElement message = (new WebDriverWait(driver, 5))
                    .until(new ExpectedCondition<WebElement>(){
                        @Override
                        public WebElement apply(WebDriver d) {
                            return d.findElement(By.id("page4"));
                        }});
            assertTrue(message.getText().contains("Nunc nibh tortor"));
        } catch (NoSuchElementException e) {
            fail("Element not found!!");
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }

    @Test
    public void waitForJQuery() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return (Boolean)js.executeScript("return jQuery.active === 0");
            }});
    }
}
