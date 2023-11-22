import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    FirefoxOptions options = new FirefoxOptions();
    options.setBinary("/usr/bin/firefox");
  
    WebDriver driver = new FirefoxDriver(options);
    driver.get("https://curseforge.com/");


    WebDriverWait wait = new WebDriverWait(driver, 10);


    // Privacy Notice Acept

	Thread.sleep(10000);

    WebElement botonEnIframe = driver.findElement(By.id("cookiebar-ok"));
	botonEnIframe.click();


    WebElement divElementMine = driver.findElement(By.xpath("/html/body/div[1]/main/div/section[1]/div/ul/li[1]/a"));
    divElementMine.click();


    // Nombre de los Mod's
    List<WebElement> nameElements = driver.findElements(By.className("ellipsis"));

    for (WebElement element : nameElements) {
      String nombre = element.getText();
      if (!nombre.equals("By")) {
        System.out.println(nombre);
      }
    }


    //  Nombre del Autor
    List<WebElement> autorElements = driver.findElements(By.className("author"));

    for (WebElement element : autorElements) {
      String nombre = element.getText();
      System.out.println(nombre);
    }

    driver.quit();

  }
}
