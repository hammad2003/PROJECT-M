import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PMV2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getenv("PATH"));
        System.out.println(System.getenv("HOME"));

        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox");

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://curseforge.com/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 0,500);
        Thread.sleep(500);

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
            Thread.sleep(500);

            //  Nombre del JuegoMods
            List<WebElement> juegoNombreMod = driver.findElements(By.className("game-description"));

            for (WebElement JuegoElementsMods : juegoNombreMod) {
                System.out.println(JuegoElementsMods.findElement(By.tagName("h1")).getText() + "\n");
                System.out.println(JuegoElementsMods.findElement(By.tagName("p")).getText() + "\n");

                WebElement openerBsuttonn = driver.findElement(By.className("view-all-link")); // Reemplaza "opener" con el id real de tu botón
                openerBsuttonn.click();


//                WebElement messageOverlay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notice")));
//
//                // Hacer clic en el botón "Accept"
//                WebElement acceptButton = messageOverlay.findElement(By.className("sp_choice_type_11"));
//                acceptButton.click();


                List<String> juegosMods = new ArrayList<>();

                // Encuentra el elemento con la clase "results-container"
                WebElement linkss = driver.findElement(By.className("results-container"));
                // Encuentra todos los elementos con la clase "project-card"
                List<WebElement> projectCards = linkss.findElements(By.className("project-card"));

                // Itera sobre los elementos "project-card"
                for (WebElement projectCard : projectCards) {
                    // Obtener el nombre del proyecto
                    System.out.println(projectCard.findElement(By.className("name")).findElement(By.tagName("span")).getText());

                    // Obtener el nombre del autor
                    System.out.println(projectCard.findElement(By.className("by-author-link")).getText().replaceAll("\\n", " ").replaceAll("\\r", "") + projectCard.findElement(By.className("ellipsis")).getText() + "\n");

                    // Descripcion
                    System.out.println(projectCard.findElement(By.className("description")).getText());

                    // Descripcion
                    System.out.println("\n");

                    WebElement detailsList = projectCard.findElement(By.className("details-list"));
                    List<WebElement> detailItems = detailsList.findElements(By.tagName("li"));

                    for (WebElement detailItem : detailItems) {
                        System.out.println(detailItem.getText());
                    }

                    System.out.println("\n");

                    WebElement categoriesList = projectCard.findElement(By.className("categories"));
                    List<WebElement> categoryItems = categoriesList.findElements(By.tagName("li"));

                    for (WebElement categoryItem : categoryItems) {
                        System.out.println(categoryItem.getText());
                    }

                    System.out.println("\n");
                }
            }
        }

        driver.quit();
    }
}
