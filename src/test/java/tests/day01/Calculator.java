package tests.day01;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Calculator {

    AndroidDriver<AndroidElement> driver;


    @Test
    public void calculator() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\legen\\IdeaProjects\\T145_Appium2\\Apps\\Calculator_8.4 (503542421)_Apkpure (3).apk");
        //caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\legen\\IdeaProjects\\T145_Appium2\\Apps\\Apk Bilgisi_2.3.4_apkcombo.com.apk");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Assert.assertTrue(driver.isAppInstalled("com.google.android.calculator"));

        Assert.assertTrue(driver.findElementByAccessibilityId("clear").isDisplayed());

        driver.findElementByAccessibilityId("4").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("multiply").click();
        driver.findElementByAccessibilityId("3").click();

        int expectedResult = 1200;
        String actualResult = driver.findElementById("com.google.android.calculator:id/result_preview").getText();

        Assert.assertEquals(Integer.parseInt(actualResult), expectedResult);
    }
}