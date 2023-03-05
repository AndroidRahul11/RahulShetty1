import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class BaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void ConfiguredAppium() throws MalformedURLException {


        //AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS((new File("/opt/homebrew/bin/appium"))).withIPAddress("127.0.0.1:4723").usingPort(4723).build();
        //service.start();
        //Launch simulator
        //caps.setCapability(MobileCapabilityType.UDID, "");
        //caps.setCapability("simulatorStartupTimeout", 180000)
        //to click on SEarch //driver.findelement(AppiumBy.AccessibilityId("Enter").click

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("rahulemu");
        //options.setChromedriverExecutable("//Users//rahul.vyavahare//Desktop//CloverGO//chromedriver");
        //options.setApp("//Users//rahul.vyavahare//Downloads//Learn1//src//test//resources//ApiDemos-debug.apk");
        options.setApp("//Users//rahul.vyavahare//Downloads//Learn1//src//test//resources//General-Store.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));

    }

    public void scrollToEndAction() {
        boolean CanScrollMore;
        do {
            CanScrollMore = (boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0));

        } while (CanScrollMore);
    }

    public void swipeAction(WebElement ele, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", "left",
                "percent", 0.75
        ));

    }


    @AfterClass

    public void teardown() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
        //service.stop();

    }
}
