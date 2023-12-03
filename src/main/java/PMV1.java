import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PMV1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getenv("PATH"));
        System.out.println(System.getenv("HOME"));

        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox");

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://curseforge.com/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(10000);

        // Privacy Notice Acept
        WebElement botonPrivacidadInicio = driver.findElement(By.id("cookiebar-ok"));
        botonPrivacidadInicio.click();


        // Haz clic en el botón "View all" para ver todos los juegos
        WebElement openerButtonn = driver.findElement(By.className("view-all-link")); // Reemplaza "opener" con el id real de tu botón
        openerButtonn.click();


        List<String> links = new ArrayList<>();

        // Encuentra el elemento <div> con la clase "browse-module"
        WebElement divElement = driver.findElement(By.className("browse-module"));

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

            //  Nombre del JuegoMods
            List<WebElement> juegoNombreMod = driver.findElements(By.className("game-description"));

            for (WebElement JuegoElementsMods : juegoNombreMod) {
                System.out.println(JuegoElementsMods.findElement(By.tagName("h1")).getText() + "\n");
                System.out.println(JuegoElementsMods.findElement(By.tagName("p")).getText() + "\n");

                WebElement openerBsuttonn = driver.findElement(By.className("view-all-link")); // Reemplaza "opener" con el id real de tu botón
                openerBsuttonn.click();

                List<String> juegosMods = new ArrayList<>();

                // Encuentra el elemento con la clase "results-container"
                WebElement linkss = driver.findElement(By.className("results-container"));
                // Encuentra todos los elementos con la clase "project-card"
                List<WebElement> projectCards = linkss.findElements(By.className("project-card"));

                // Itera sobre los elementos "project-card"
                for (WebElement projectCard : projectCards) {
                    // Encuentra el elemento <a> dentro de cada "project-card"
                    WebElement linkElement = projectCard.findElement(By.tagName("a"));

                    // Obtén el valor del atributo "href" y agrégalo a la lista
                    juegosMods.add(linkElement.getAttribute("href"));
                }

                // Itera sobre los enlaces de juegos y visita cada uno
                for (String modsLink : juegosMods) {
                    driver.get(modsLink);
                    Thread.sleep(2000);

                    //  Nombre del Autor y del Mod
                    List<WebElement> byautors = driver.findElements(By.className("project-header"));

                    for (WebElement autorElements : byautors) {
                        System.out.println("\n" + autorElements.findElement(By.tagName("h1")).getText());
                        System.out.println(autorElements.findElement(By.className("by-author-link")).getText().replaceAll("\\n", " ").replaceAll("\\r", "") + autorElements.findElement(By.className("ellipsis")).getText() + "\n");
                    }

                    WebElement categorii = driver.findElement(By.className("aside-box"));
                    List<WebElement> byasssssutors = categorii.findElements(By.id("project-categories"));

                    for (WebElement autorElements : byasssssutors) {
                        System.out.println(autorElements.findElement(By.className("links")).findElement(By.tagName("a")).getText());
                    }

                }
            }
        }

        driver.quit();
    }
}
