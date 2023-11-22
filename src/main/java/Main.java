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


    // Privacy Notice Acept

//	Thread.sleep(8000);
//
//    WebElement botonPrivacidadInicio = driver.findElement(By.id("cookiebar-ok"));
//    botonPrivacidadInicio.click();
//
//    driver.quit();


    driver = new FirefoxDriver(options);
    driver.get("https://curseforge.com/games");

    Thread.sleep(10000);

    WebElement botonPrivacidadInicio = driver.findElement(By.id("cookiebar-ok"));
    botonPrivacidadInicio.click();
//
//    WebElement botonPrivacidadJuegos = driver.findElement(By.id("cookiebar-ok"));
//    botonPrivacidadJuegos.click();

//    WebElement divElementMine = wait.until(ExpectedConditions.elementToBeClickable(By.className("view-all-link")));
//    divElementMine.click();

    // Nombre de todos los Juegos

//    WebElement divElemednt = driver.findElement(By.className(" game-tile"));
//    List<WebElement> nameElements = driver.findElements(By.tagName("h3"));
//
//    for (WebElement element : nameElements) {
//      String nombre = element.getText();
//      System.out.println(nombre);
//    }

//    WebElement divElement = driver.findElement(By.tagName("h3"));
//    String nombre = divElement.getText();
//    System.out.println(nombre);




// Encuentra el elemento <div> con la clase "browse-module"
    WebElement divElement = driver.findElement(By.xpath("/html/body/div[1]/main/section/div[2]/div"));

// Dentro del elemento <div>, busca el elemento <ul>
    List<WebElement> tittle = divElement.findElements(By.className("game-tile"));

    for (WebElement a : tittle) {
      System.out.println(a.findElement(By.tagName("h3")).getText());
    }

    for (WebElement a : tittle) {
      links.add(a.getAttribute("href"));
    }

    for (String a:links) {
      driver.get(a);
      Thread.sleep(2000);
    }

// Dentro del elemento <ul>, busca todos los elementos <li>
    //List<WebElement> liElements = ulElement.findElements(By.tagName("li"));

// Itera sobre los elementos <li>
    /*for (WebElement liElement : liElements) {
      // Dentro de cada <li>, busca el elemento <a> con la clase "game-tile"
      WebElement aElement = liElement.findElement(By.className("game-tile"));

      // Dentro del elemento <a>, busca el elemento <h3>
      WebElement h3Element = aElement.findElement(By.tagName("h3"));

      // Obtiene el texto del elemento <h3>
      String nombre = h3Element.getText();

      // Imprime el resultado
      System.out.println(nombre);
    }*/





//    // Nombre de los Mod's
//    List<WebElement> nameElements = driver.findElements(By.className("ellipsis"));
//
//    for (WebElement element : nameElements) {
//      String nombre = element.getText();
//      if (!nombre.equals("By")) {
//        System.out.println(nombre);
//      }
//    }
//
//
//    //  Nombre del Autor
//    List<WebElement> autorElements = driver.findElements(By.className("author"));
//
//    for (WebElement element : autorElements) {
//      String nombre = element.getText();
//      System.out.println(nombre);
//    }

    driver.quit();

  }
}
