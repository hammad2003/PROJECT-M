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

  public static void main(String[] args) {
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    FirefoxOptions options = new FirefoxOptions();
    options.setBinary("/usr/bin/firefox");
  
    WebDriver driver = new FirefoxDriver(options);
    driver.get("https://legacy.curseforge.com/");

    WebDriverWait wait = new WebDriverWait(driver, 10);


    driver.switchTo().frame("sp_message_iframe_905599");

    WebElement elementoEnIframe = driver.findElement(By.xpath("/html/body/div/div[2]/div[5]/button[2]"));
    elementoEnIframe.click();

    WebElement tarjeta = driver.findElement(By.className("flex-steady"));
    // wait.until(ExpectedConditions.elementToBeClickable(tarjeta));
    tarjeta.click();


    // Nombre de los Mod's
    List<WebElement> nameElements = driver.findElements(By.className("text-lg"));

    for (WebElement element : nameElements) {
      String nombre = element.getText();
      if (!nombre.equals("Install") && !nombre.equals("Minecraft") && !nombre.equals("All Modpacks") && !nombre.equals("Become an author") && !nombre.equals("Share content and earn points towards our rewards program")) {
        System.out.println(nombre);
      }
    }


    // Nombre del creardor los Mod's
    List<WebElement> creatornameElements = driver.findElements(By.className("hover:no-underline"));

    for (WebElement element : creatornameElements) {
      String nombre = element.getText();
      System.out.println(nombre);
    }

    driver.quit();

  }
}
