import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.lang.Math;
public class OpenPageTest  {
    public  WebDriver driver;
    @Before
    public void setupDriver(){
        System.setProperty("webdriver.gecko.driver","C:\\Users\\enes\\Downloads\\geckodriver.exe");
        driver = new FirefoxDriver();
        String url = "https://www.gittigidiyor.com/";
        driver.get(url);  //url yer alan adrese gitmesini sağlar.
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TestHome(){
        driver.findElement(By.xpath(".//*[@id=\"main-header\"]/div[3]/div/div/div/div[3]/div/div[1]")).click();  // Giriş butonu için hoverın  gelmesini sağlar.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //giriş butonuna click
        WebElement signbtn= driver.findElement(By.xpath(".//*[@id=\"main-header\"]/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div/a"));
        signbtn.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //mail inputu find
        WebElement mailbox= driver.findElement(By.id("L-UserNameField"));
        mailbox.click();
        mailbox.sendKeys("testing@gmail.com");

        //password find
        WebElement passwordbox= driver.findElement(By.id("L-PasswordField"));
        passwordbox.click();
        passwordbox.sendKeys("12345");
        driver.findElement(By.id("gg-login-enter")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //search find
        WebElement searchBox = driver.findElement(By.xpath(".//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input"));
        searchBox.click();
        searchBox.sendKeys("Bilgisayar");
        driver.findElement(By.xpath(".//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Bilgisyar sonuçlarının 2. sayfasına gitme.
        driver.findElement(By.cssSelector("#best-match-right > div.pager.pt30.hidden-m.gg-d-24 > ul > li:nth-child(2) > a")).click();

        //Rastgele ürüne girmek için random değer üretilir.
        int random = new Random().nextInt(10);
        WebElement productElement = driver.findElement(By.cssSelector(".products-container > li:nth-child(" + random + ")"));
        productElement.findElement(By.cssSelector("a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Ürünün sayfada ki fiyatı get edilir.
        WebElement price= driver.findElement(By.xpath(".//*[@class='newPrice']/ins[1]"));
        String priceText= price.getText();
        //sepete ekleme yapılır
        WebElement element = driver.findElement(By.id("add-to-basket"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        element.click();

        //sepete gidilir.
        driver.findElement(By.xpath(".//*[@id=\"header_wrapper\"]/div[4]/div[3]/div/div/div/div[2]/div[4]/div[1]/a")).click();

        //Ürünün sepette ki fiyatı alınır.
        String newPriceText = driver.findElement(By.className("new-price")).getText();
        //Sepeteki ürün fiyatı ile sayfasında ki fiyat karşılaştırılır.
        if (priceText ==newPriceText) {
            //ürün adeti 2 set edilir.
            driver.findElement(By.id("amount")).sendKeys("2");
            driver.findElement(By.id("amount")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String newPriceText2 = driver.findElement(By.className("new-price")).getText();
            //sepette ki ürünler silinir.
            driver.findElement(By.className("btn-delete")).click();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    @After
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit();
    }
}
