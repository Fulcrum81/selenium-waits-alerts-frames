import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Frames extends TestBase {

    private static final By FRAMES_LINK = By.xpath("//a[@href='/nested_frames']");
    private static final By IFRAMES_LINK = By.xpath("");

    private static final By BODY = By.tagName("body");

    @Override
    @BeforeTest
    public void setup() {
        super.setup();
        driver.get("http://the-internet.herokuapp.com/frames");
    }

    @Test
    public void testFrameWith()
    {
        driver.findElement(FRAMES_LINK).click();

        //Activate the frame on left side using it's id attribute
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");

        //Get an element from the frame on left side and verify it's contents
        WebElement leftmsg = driver.findElement(BODY);
        assertEquals("LEFT", leftmsg.getText());

        //Activate the Page, this will move context from frame back to the Page
        driver.switchTo().defaultContent();

        //Activate the frame on right side using it's name attribute
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-right");

        //Get an element from the frame on right side and verify it's contents
        WebElement rightmsg = driver.findElement(BODY);
        assertEquals("RIGHT", rightmsg.getText());

        //Activate the Page, this will move context from frame back to the Page
        driver.switchTo().defaultContent();
    }

    @Test
    public void testIFrame() {

    }


}
