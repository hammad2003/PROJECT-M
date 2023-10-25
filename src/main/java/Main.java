import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) {
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    FirefoxOptions options = new FirefoxOptions();
    options.setBinary("/usr/bin/firefox");
  
    WebDriver driver = new FirefoxDriver(options);
    driver.get("https://legacy.curseforge.com/");

    WebDriverWait wait = new WebDriverWait(driver, 10);

    WebElement tarjeta = driver.findElement(By.className("flex-steady"));
   // wait.until(ExpectedConditions.elementToBeClickable(tarjeta));
    tarjeta.click();

//    List<WebElement> elements = driver.findElements(By.className("flex-steady"));


  }
}
