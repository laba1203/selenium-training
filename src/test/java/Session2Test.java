import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Session2Test {

    WebDriver drv, drv1, drv2;

    @BeforeClass
    public static void setup() {
        ChromeDriverManager.getInstance().setup();
        InternetExplorerDriverManager.getInstance().arch32().setup();
    }

    @Test
    @Ignore
    public void cookieChrome() {
        drv1 = new ChromeDriver();
        drv2 = new ChromeDriver();

        // drv1.manage().addCookie(new Cookie("test", "test"));

        drv1.get("http://google.com");
        drv2.get("http://google.com");
        System.out.println("Drv1 before: " + drv1.manage().getCookies());
        System.out.println("Drv2 before: " + drv2.manage().getCookies());

        drv1.manage().addCookie(new Cookie("Security_ID", "KJILSDuf984jmmcoidshnfrdoiut98e"));
        System.out.println("Drv1 after: " + drv1.manage().getCookies());
        System.out.println("Drv1 NID: " + drv1.manage().getCookieNamed("NID"));

        drv1.manage().deleteCookieNamed("NID");
        System.out.println("Drv1 NID deleted: " + drv1.manage().getCookies());

        drv1.manage().deleteAllCookies();
        System.out.println("Drv1 delete: " + drv1.manage().getCookies());
        System.out.println("Drv2 delete: " + drv2.manage().getCookies());
    }

    @Test
    @Ignore
    public void cookieIE() {

        drv1 = new InternetExplorerDriver();
        drv2 = new InternetExplorerDriver();

        drv1.get("http://google.com");
        System.out.println("Drv1 before: " + drv1.manage().getCookies());

        drv2.get("http://google.com");
        System.out.println("Drv2 before: " + drv2.manage().getCookies());

        drv1.manage().deleteAllCookies();
        System.out.println("Drv1 delete: " + drv1.manage().getCookies());
        System.out.println("Drv2 delete: " + drv2.manage().getCookies());
    }


    @Test
    @Ignore
    public void getElement() {
        drv = new ChromeDriver();

        drv.get("http://barancev.github.io/slow-loading-pages/");
        WebElement el = drv.findElement(By.id("page-wrapper"));

        System.out.println(el.getAttribute("id"));

        System.out.println(el.getAttribute("innerHTML"));
        System.out.println(el.getAttribute("textContent"));
//
//        el.sendKeys("Test");
//        drv.navigate().refresh();
//        //el.sendKeys("Test2");
//
//        el = drv.findElement(By.name("q"));
//        el.sendKeys("Test2");

    }

    @Test
    @Ignore
    public void headLess() {
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(true);
        drv = new ChromeDriver(opt);
        drv.get("http://google.com");
        System.out.println(drv.getPageSource());
    }

    @Test
    @Ignore
    public void elementNotFound() {
        drv = new ChromeDriver();
        drv.get("http://google.com");

        Assert.assertFalse(isElementPresent(drv, By.xpath("[")));
        Assert.assertFalse(areElementsPresent(drv, By.xpath("[")));
    }

    @Test
    @Ignore
    public void jsExecutor() {
        drv = new ChromeDriver();

        drv.get("https://www.w3.org/");

        List<WebElement> links = (List<WebElement>) ((JavascriptExecutor) drv)
                .executeScript("return document.getElementsByClassName('headline')");

        for (WebElement item : links) {
            System.out.println(item.getTagName());
        }
    }

    @After
    public void cleanup() {
        if (drv != null) drv.quit();
        if (drv1 != null) drv1.quit();
        if (drv2 != null) drv2.quit();
    }


    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }
}