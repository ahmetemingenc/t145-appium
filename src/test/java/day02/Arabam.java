package day02;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Arabam {

    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\legen\\IdeaProjects\\T145_Appium2\\Apps\\arabam-com-5-4-1.apk");
        caps.setCapability("appPackage", "com.dogan.arabam");
        caps.setCapability("appActivity", "com.dogan.arabam.presentation.feature.home.HomeActivity");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void arabam() throws InterruptedException {

        // uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        // uygulamanin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementById("com.dogan.arabam:id/ivArabamLogo").isDisplayed());

        // alt menuden ilan ara butonuna tiklanir
        driver.findElementByXPath("(//android.widget.ImageView[@resource-id=\"com.dogan.arabam:id/navigation_bar_item_icon_view\"])[2]").click();

        // kategori olarak otomobil secilir
        driver.findElementByXPath("//*[@text='Otomobil']").click();

        // arac olarak Volkswagen secilir
        Thread.sleep(1500);
        TouchAction action = new TouchAction<>(driver);

        action.press(PointOption.point(531, 1967)).
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).
                moveTo(PointOption.point(531, 326)).release().perform();

        Thread.sleep(1000);

        driver.findElementByXPath("//*[@text='Volkswagen']").click();

        Thread.sleep(1000);

        // arac markasi olarak passat secilir
        driver.findElementByXPath("//*[@text='Passat']").click();

        Thread.sleep(1000);

        // 1.4 TSI BlueMotion secilir
        driver.findElementByXPath("//*[@text='1.4 TSi BlueMotion']").click();

        // Paket secimi olarak comfortline yapilir
        driver.findElementByXPath("//*[@text='Comfortline']").click();

        Thread.sleep(1000);

        // Ucuzdan pahaliya siralama yaparak filtreleme yapilir
        driver.findElementByXPath("//*[@text='Sıralama']").click();

        Thread.sleep(500);

        driver.findElementByXPath("//*[@text='Fiyat - Ucuzdan Pahalıya']").click();

        Thread.sleep(1000);

        // Gelen en ucuz aracin 500.000 tl den buyuk oldugu dogrulanir
        int expectedMinPrice = 500001;
        String actualPriceString = driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvPrice'])[1]").getText();
        int actualPrice = Integer.parseInt(actualPriceString.replaceAll("\\D", ""));

        Assert.assertTrue(actualPrice >= expectedMinPrice);
    }
}
