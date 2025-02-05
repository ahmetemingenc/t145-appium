package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static AndroidDriver<AndroidElement> appiumDriver;
    private static IOSDriver<IOSElement> iosDriver;

    static final String DEVICE_NAME = "Pixel";
    static final String PLATFORM_VERSION = "10.0";
    static final String PLATFORM_NAME = "Android";
    static final String AUTOMATION_NAME = "UiAutomator2";


    public static AndroidDriver getAndroidDriver() {

        URL appiumServerURL = null;
        try {
            appiumServerURL = new URL("http:127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (appiumDriver == null) {

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
            caps.setCapability("appPackage", ConfigReader.getProperty("kiwiAppPackage"));
            caps.setCapability("appActivity", ConfigReader.getProperty("kiwiAppActivity"));
            caps.setCapability(MobileCapabilityType.NO_RESET, false);

            if (ConfigReader.getProperty("platformName").equals("Android")) {

                assert appiumServerURL != null;
                appiumDriver = new AndroidDriver<AndroidElement>(appiumServerURL, caps);
                appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            } else {

                assert appiumServerURL != null;
                iosDriver = new IOSDriver<IOSElement>(appiumServerURL, caps);
                iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

                throw new UnsupportedOperationException("Cihaz IOS");
            }
        }

        return appiumDriver;
    }

    public static void quitAppiumDriver() {
        if (appiumDriver != null) {
            appiumDriver.closeApp();
            appiumDriver = null;
        }
    }

}