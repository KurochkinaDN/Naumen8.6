import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class smpTest {
    static WebDriver driver;
    @Test
    public void addObject() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Открытие тестовой страницы
        driver.get("http://5.181.254.246:8080/sd/");

        //залогиниться
        driver.findElement(By.id("username")).sendKeys("user22");
        driver.findElement(By.id("password")).sendKeys("Dasha023nov!");
        driver.findElement(By.xpath("//input[@id='submit-button']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='submit-button']")));


        //нажать кнопку добавление в избранное
        driver.findElement(By.xpath("//div[@id='gwt-debug-favorite']")).click();
        Thread.sleep(5000);
        //нажать сохранить
        driver.findElement(By.xpath("//div[@id='gwt-debug-apply']")).click();
        Thread.sleep(5000);
        //проверка

        //разлогиниться
        driver.findElement(By.xpath("//a[contains(text(),'Выйти')]")).click();
    }

    public WebElement waitElement(String xpath)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void click(String xpath)
    {
        waitElement(xpath).click();
    }

    public void send(String xpath, String arg)
    {
        driver.findElement(By.xpath(xpath)).clear();
        waitElement(xpath).sendKeys(arg);
    }

    @AfterAll
    public static void close()
    {
        driver.close();
    }
}
