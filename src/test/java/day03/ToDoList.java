package day03;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ToDoList {

    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\legen\\IdeaProjects\\T145_Appium2\\Apps\\To-Do List - Schedule Planner_1.02.61.0928_APKPure.apk");
        caps.setCapability("appPackage", "todolist.scheduleplanner.dailyplanner.todo.reminders");
        caps.setCapability("appActivity", "app.todolist.activity.SplashActivity");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void toDoListTest() throws InterruptedException {

        // uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("todolist.scheduleplanner.dailyplanner.todo.reminders"));
        Thread.sleep(1000);

        // uygulaminin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementByXPath("//*[@text='CONTINUE']").isDisplayed());

        // Ileri butonlarina tiklanir ve pop-up lar kapatilir
        driver.findElementByXPath("//*[@text='CONTINUE']").click();
        Thread.sleep(1000);

        driver.findElementByXPath("//*[@text='CONTINUE']").click();
        Thread.sleep(1000);

        driver.findElementByXPath("//*[@resource-id='todolist.scheduleplanner.dailyplanner.todo.reminders:id/toolbar_back']").click();
        Thread.sleep(1000);

        driver.findElementByXPath("//*[@resource-id='todolist.scheduleplanner.dailyplanner.todo.reminders:id/dialog_pro_first_close']").click();
        Thread.sleep(1000);

        // görev ekleme adimina gecilir
        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/iv_task_add").click();
        Thread.sleep(500);

        // görev adi girilir
        driver.findElementByXPath("//*[@text='Input new task here']").sendKeys("deneme");

        // görev kaydedilir
        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_create_btn").click();
        Thread.sleep(1500);

        // görev silinir
        TouchAction action = new TouchAction<>(driver);

        for (int i = 0; i < 3; i++) {

            action.press(PointOption.point(880, 1200)).release().perform();
            Thread.sleep(300);
        }

        driver.findElementByXPath("(//*[@resource-id='todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_contains_bg1'])[1]").click();

        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_detail_more").click();

        driver.findElementByXPath("//*[@text='Delete']").click();
        Thread.sleep(500);

        driver.findElementByXPath("//*[@text='DELETE']").click();
        Thread.sleep(1000);

        // Görev olusturma sayfasina geri dönüldügü dogrulanir
        Assert.assertTrue(driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/iv_task_add").isDisplayed());
    }
}
