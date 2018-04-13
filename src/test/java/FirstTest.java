import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirstTest {

    @Test
    public void firefoxTest()
    {
        //FirefoxDriverManager.getInstance().setup();
        //WebDriver drv = new FirefoxDriver();

        //Old way of setting capabilities by passing it into driver's constructor
        //Now is deprecated, use FirefoxOptions class for setting capabilities
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, false);
        //WebDriver drv = new FirefoxDriver(caps);

        FirefoxOptions opt = new FirefoxOptions();
        opt.setCapability(FirefoxDriver.MARIONETTE, false);

        WebDriver drv = new FirefoxDriver(opt);

        drv.get("http://google.com");
        drv.quit();
    }

    @Test
    public void chromeTest()
    {
        ChromeDriverManager.getInstance().setup();

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("start-maximized");

        WebDriver drv = new ChromeDriver(opt);

        drv.get("http://google.com");
        drv.quit();
    }

}
