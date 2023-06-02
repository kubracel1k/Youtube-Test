import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class YoutubeTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testYoutube() {
        // Anahtar kelimeyi ara
        driver.get("https://www.youtube.com/");
        WebElement searchBox = driver.findElement(By.cssSelector("input#search"));
        searchBox.sendKeys("Selenium Java Tutorial");
        searchBox.sendKeys(Keys.RETURN);

        // Videoları bul
        List<WebElement> videos = driver.findElements(By.cssSelector("a#video-title"));
        List<String> videoUrls = new ArrayList<>();
        for (WebElement video : videos) {
            videoUrls.add(video.getAttribute("href"));
        }

        // Rastgele 5 videoyu aç
        Collections.shuffle(videoUrls);
        for (int i = 0; i < 5; i++) {
            driver.get(videoUrls.get(i));
            try {
                Thread.sleep(5000); // 5 saniye beklet
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.cssSelector("button.ytp-play-button")).click(); // Videoyu oynat
        }
    }
}
