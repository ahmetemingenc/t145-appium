package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class KiwiPages {

    public KiwiPages(){PageFactory.initElements((WebDriver) Driver.getAndroidDriver(), this);}

    @FindBy(xpath = "//*[@text='Continue as a guest']")
    public WebElement continueAsAGuestButton;

    @FindBy(xpath = "//*[@text='Return']")
    public WebElement tripTypeButton;

    @FindBy(xpath = "//*[@text='From:']")
    public WebElement fromButton;

    @FindBy(xpath = "//*[@text='To:']")
    public WebElement toButton;

    @FindBy(xpath = "//*[@text='Departure:']")
    public WebElement departureButton;

    @FindBy(xpath = "//*[@text='One way']")
    public WebElement oneWayButton;

    @FindBy(xpath = "//com.kiwi.android.shared.ui.view.base.picker.DialogComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.widget.Button")
    public WebElement deleteDefaultButton;

    @FindBy(xpath = "(//*[@class='android.widget.Button'])[2]")
    public WebElement secondFromOption;

    @FindBy(xpath = "(//*[@class='android.widget.Button'])[1]")
    public WebElement firstToOption;

    @FindBy(xpath = "//*[@text='Choose']")
    public WebElement chooseButton;

    @FindBy(id = "com.skypicker.main:id/saveButton")
    public WebElement setDateButton;

    @FindBy(xpath = "//*[@text='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@text='Best']")
    public WebElement bestButton;

    @FindBy(xpath = "//*[@text='Cheapest']")
    public WebElement cheapestButton;

    @FindBy(xpath = "//*[@text='Stops']")
    public WebElement stopsButton;

    @FindBy(xpath = "//*[@text='Nonstop']")
    public WebElement nonstopButton;

    @FindBy(xpath = "(//*[@class='android.widget.TextView'])[11]")
    public WebElement firstPriceOption;

}
