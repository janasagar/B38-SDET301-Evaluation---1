package testsuite;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

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
    ExtentReports extent;
    ExtentTest test;
    
    @BeforeTest
    public void setup() throws MalformedURLException {
        // Initialize the driver and set capabilities
        driver = Capabilities();
        // Set an implicit wait time
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
     // Set up ExtentReports
     	ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        test = extent.createTest("Register Test", "Test to validate user registration");
    }
    
    @Test
    public void test1() throws InterruptedException {
    	test.log(Status.INFO, "Starting testFdroidApp");
        // Click on the 'Settings' button using Accessibility ID
        driver.findElement(MobileBy.AccessibilityId("Settings")).click();
        
        // Find the switch widget and perform a tap action using TouchAction
        MobileElement element = driver.findElementById("org.fdroid.fdroid:id/switchWidget");
        TouchAction action = new TouchAction(driver);
        action.tap(ElementOption.element(element)).perform();
        test.log(Status.PASS, "Tapped on element");
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
//        test.log(Status.PASS, "Performed scroll action");
        
        // Open the notification drawer
        driver.openNotifications();
        
        // Click on the fourth ImageView element in the notifications
        driver.findElements(MobileBy.className("android.widget.ImageView")).get(4).click();
        test.log(Status.PASS, "Enabled battery saver");
        driver.findElements(MobileBy.className("android.widget.ImageView")).get(4).click();
        test.log(Status.PASS, "Disabled battery saver");
        // Press the back button to close the notification drawer
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        
        // Activate the messaging app
        driver.activateApp("com.google.android.apps.messaging");
        test.log(Status.PASS, "Swtich to messageing app");
        // Click on a message container in the messaging app
        driver.findElement(MobileBy.id("com.google.android.apps.messaging:id/swipeableContainer")).click();
        
        // Retrieve and print the text of a message
        String msg = driver.findElements(MobileBy.className("android.view.View")).get(6).getText();
        System.out.println(msg);
        test.log(Status.PASS, "Text is printed");
        // Switch back to the F-Droid app
        driver.activateApp("org.fdroid.fdroid");
        test.log(Status.PASS, "Switch Back to F-Droid app");
        // Press the home button to go to the home screen
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        test.log(Status.PASS, "Navigate to Home");
    }
    @AfterTest
    public void close() {
        extent.flush();
    }
}
