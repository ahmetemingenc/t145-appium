package tests.day04;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.appium.java_client.touch.offset.PointOption;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.KiwiPages;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;


public class Kiwi {

    AndroidDriver<AndroidElement> driver = Driver.getAndroidDriver();
    KiwiPages kiwiPages = new KiwiPages();

    @Test
    public void kiwiTest(){

        // uygulamanin yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled(ConfigReader.getProperty("kiwiAppPackage")));

        // uygulamanin basariyla acildigi dogrulanir
        Assert.assertTrue(kiwiPages.continueAsAGuestButton.isDisplayed());

        // misafir olarak devam et e tiklanir
        kiwiPages.continueAsAGuestButton.click();

        // ardindan gelecek olan 3 adimda da yesil butona basilarak devam edilir
        ReusableMethods.clickToCoordinate(530, 2040, 500, 3);

        // Trip type,one way olarak secilir
        kiwiPages.tripTypeButton.click();

        ReusableMethods.wait(1);

        kiwiPages.oneWayButton.click();

        // kalkis ulkesi secenegine tiklanir ve default olan ulke kaldirilir
        kiwiPages.fromButton.click();
        ReusableMethods.wait(1);

        kiwiPages.deleteDefaultButton.click();

        ReusableMethods.wait(1);

        // kalkis yapilacak ulke/sehir girilir ve sec e tiklanir
        kiwiPages.secondFromOption.click();
        kiwiPages.chooseButton.click();

        ReusableMethods.wait(1);

        // varis ulkesi secenegine tiklanir ve gidilecek ulke girilir
        kiwiPages.toButton.click();
        kiwiPages.firstToOption.click();
        kiwiPages.chooseButton.click();

        ReusableMethods.wait(1);

        // gidis tarihi 27 Şubat olarak secilir ve set date e tiklanir
        kiwiPages.departureButton.click();

        ReusableMethods.clickToCoordinate(675, 1434, 0, 1);

        kiwiPages.setDateButton.click();

        ReusableMethods.wait(1);

        // search butonuna tiklanir
        kiwiPages.searchButton.click();

        ReusableMethods.wait(1);

        // en ucuz ve aktarmasiz filtrelemeleri yapilir
        kiwiPages.bestButton.click();
        kiwiPages.cheapestButton.click();

        ReusableMethods.wait(1);

        kiwiPages.stopsButton.click();
        kiwiPages.nonstopButton.click();

        ReusableMethods.wait(2);

        // gelen bilet fiyati kaydedilir ve kullanicin telefonuna sms olarak gonderilir
        String ticketPrice = driver.findElementByXPath("(//*[@class='android.widget.TextView'])[11]").getText();

        driver.sendSMS("111111111", "Ticket Price: " + ticketPrice);
    }

}
