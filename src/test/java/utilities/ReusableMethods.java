package utilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class ReusableMethods {

    public static void wait(int seconds) {

        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clickToCoordinate(int x, int y, int wait, int repeat){

        TouchAction action = new TouchAction<>(Driver.getAndroidDriver());

        for (int i = 0; i < repeat ; i++) {

            action.press(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(wait))).release().perform();
            ReusableMethods.wait(1);
        }
    }
}
