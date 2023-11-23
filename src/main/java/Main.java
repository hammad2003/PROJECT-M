import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    FirefoxOptions options = new FirefoxOptions();
    options.setBinary("/usr/bin/firefox");

    List<String> links = new ArrayList<>();

    WebDriver driver = new FirefoxDriver(options);
    driver.get("https://curseforge.com/");


    WebDriverWait wait = new WebDriverWait(driver, 10);



//	Thread.sleep(8000);
//
//    WebElement botonPrivacidadInicio = driver.findElement(By.id("cookiebar-ok"));
//    botonPrivacidadInicio.click();
//
//    driver.quit();


    driver = new FirefoxDriver(options);
    driver.get("https://curseforge.com/games");

    Thread.sleep(10000);

    // Privacy Notice Acept
    WebElement botonPrivacidadInicio = driver.findElement(By.id("cookiebar-ok"));
    botonPrivacidadInicio.click();


// Encuentra el elemento <div> con la clase "browse-module"
    WebElement divElement = driver.findElement(By.xpath("/html/body/div[1]/main/section/div[2]/div"));

// Dentro del elemento <div>, busca el elemento <ul>
    List<WebElement> tittle = divElement.findElements(By.className("game-tile"));

    for (WebElement titulosJuego : tittle) {
      System.out.println(titulosJuego.findElement(By.tagName("h3")).getText());
    }

    for (WebElement a : tittle) {
      links.add(a.getAttribute("href"));
    }

    for (String juegosLink : links) {
      driver.get(juegosLink);
      Thread.sleep(2000);


        //  Nombre del Autor y del Mod
        List<WebElement> byautors = driver.findElements(By.className("details"));

        for (WebElement autorElements : byautors) {
          System.out.println(autorElements.findElement(By.tagName("h3")).getText());
          System.out.println(autorElements.findElement(By.className("by-author-link")).getText().replaceAll("\\n", " ").replaceAll("\\r", "") + autorElements.findElement(By.className("ellipsis")).getText() + "\n");
        }


    }




    driver.quit();

  }
}
