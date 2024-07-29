package testsuite;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import hybrid.Fdroid;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;
import io.appium.java_client.TouchAction;

public class Test_fdroid extends Fdroid {
    AndroidDriver<AndroidElement> driver;
    
    @BeforeTest
    public void setup() throws MalformedURLException {
        // Initialize the driver and set capabilities
        driver = Capabilities();
        // Set an implicit wait time
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    
    @Test
    public void test1() throws InterruptedException {
        // Click on the 'Settings' button using Accessibility ID
        driver.findElement(MobileBy.AccessibilityId("Settings")).click();
        
        // Find the switch widget and perform a tap action using TouchAction
        MobileElement element = driver.findElementById("org.fdroid.fdroid:id/switchWidget");
        TouchAction action = new TouchAction(driver);
        action.tap(ElementOption.element(element)).perform();
        Thread.sleep(3000);  // Pause execution for 3 seconds
        
        // Scroll to the element with text "Expert mode"
//        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Expert mode\"))"));
        
        // Identify the start and end elements for the scroll action
//        MobileElement startElement = driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Manage installed apps\")"));
//        MobileElement endElement = driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Expert mode\")"));
        
        // Perform the scroll action using TouchAction
//        action.press(ElementOption.element(startElement))
//              .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
//              .moveTo(ElementOption.element(endElement))
//              .release()
//              .perform();
        
        // Open the notification drawer
        driver.openNotifications();
        
        // Click on the fourth ImageView element in the notifications
        driver.findElements(MobileBy.className("android.widget.ImageView")).get(4).click();
        driver.findElements(MobileBy.className("android.widget.ImageView")).get(4).click();
        
        // Press the back button to close the notification drawer
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        
        // Activate the messaging app
        driver.activateApp("com.google.android.apps.messaging");
        
        // Click on a message container in the messaging app
        driver.findElement(MobileBy.id("com.google.android.apps.messaging:id/swipeableContainer")).click();
        
        // Retrieve and print the text of a message
        String msg = driver.findElements(MobileBy.className("android.view.View")).get(6).getText();
        System.out.println(msg);
        
        // Switch back to the F-Droid app
        driver.activateApp("org.fdroid.fdroid");
        
        // Press the home button to go to the home screen
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
