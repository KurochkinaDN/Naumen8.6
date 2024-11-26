import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class smpTest2 {
    static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1400, 1200));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void addObject() throws InterruptedException {
        //залогиниться
        login();

        //создать карточку
        addCard();

        //пауза
        Thread.sleep(5000);

        //открыть избранное
        click("//div[@id='gwt-debug-c5af86c7-6e4d-a611-55f9-d3fc8dcc236c']/div");

        //пауза
        Thread.sleep(5000);

        //проверка
        WebElement element = driver.findElement(By.xpath("//a[@id='gwt-debug-title']"));
        String test = element.getText();
        String msg = String.format("Ошибка: название объекта. Ожидалось: %s, Получено: %s", "test", test);
        Assertions.assertEquals("test", test, msg);

        //пауза
        Thread.sleep(5000);

        //удаление карточки
        deleteCard();

        //пауза
        Thread.sleep(5000);

        //закрытие "Избранного"
        click("//div[6]/div/div[2]/div");

        //пауза
        Thread.sleep(5000);

        //выход из личного кабинета
        click("//a[contains(text(),'Выйти')]");
    }

    @Test
    public void deleteObject() throws InterruptedException {
        //залогиниться
        login();

        //создание карточки
        addCard();

        //пауза
        Thread.sleep(5000);

        //открыть избранное
        click("//div[@id='gwt-debug-c5af86c7-6e4d-a611-55f9-d3fc8dcc236c']/div");

        //пауза
        Thread.sleep(5000);

        //удаление карточки
        deleteCard();

        //пауза
        Thread.sleep(5000);

        //проверка
        List<WebElement> elements = driver.findElements(By.xpath("//a[@id='gwt-debug-title']/div"));
        Assertions.assertTrue(elements.isEmpty(), "Ошибка. Объект не удалён.");

        //пауза
        Thread.sleep(5000);

        //закрыть избранное
        click("//div[6]/div/div[2]/div");

        //пауза
        Thread.sleep(5000);

        //выход из личного кабинета
        click("//a[contains(text(),'Выйти')]");
    }

    private void login() {
        driver.get("http://5.181.254.246:8080/sd/");

        //залогиниться
        driver.findElement(By.id("username")).sendKeys("user22");
        driver.findElement(By.id("password")).sendKeys("Dasha023nov!");
        click("//input[@id='submit-button']");
    }

    public void addCard() throws InterruptedException {
        //пауза
        Thread.sleep(5000);

        //перейти на вкладку Autotesting
        //click("//a[contains(text(),'Autotesting')]");

        //пауза
       // Thread.sleep(5000);

        //нажать на кнопку избранное
        click("//div[@id='gwt-debug-favorite']");

        //пауза
        Thread.sleep(5000);

        //ввести название карточки
        send("//input[@id='gwt-debug-itemTitle-value']", "test");

        //пауза
        Thread.sleep(5000);

        //нажать на кнопку сохранить
        click("//div[@id='gwt-debug-apply']");
    }

    public void deleteCard() throws InterruptedException {
        //пауза
        Thread.sleep(5000);
        //нажать на кнопку "Редактировать избранное"
        click("//div[2]/div/div/div/div[2]/span");
        //пауза
        Thread.sleep(5000);
        //удалить карточку
        click("//table[@id='gwt-debug-favoritesEditTable']/tbody/tr/td[6]/div/span");
        //пауза
        Thread.sleep(5000);
        //подтвердить удаление
        click("//div[@id='gwt-debug-yes']");
        //пауза
        Thread.sleep(5000);
        //сохранение изменений
        click("//div[@id='gwt-debug-apply']");
    }

    public WebElement waitElement(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void click(String xpath) {
        waitElement(xpath).click();
    }

    public void send(String xpath, String arg) {
        driver.findElement(By.xpath(xpath)).clear();
        waitElement(xpath).sendKeys(arg);
    }

    @AfterAll
    public static void close() {
        driver.close();
    }
}
