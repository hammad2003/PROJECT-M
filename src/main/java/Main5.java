import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main5 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getenv("PATH"));
        System.out.println(System.getenv("HOME"));

        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox");

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://curseforge.com/");


        WebDriverWait wait = new WebDriverWait(driver, 10);

        Thread.sleep(10000);

        // Privacy Notice Acept
        WebElement botonPrivacidadInicio = driver.findElement(By.id("cookiebar-ok"));
        botonPrivacidadInicio.click();

        // Haz clic en el botón "opener"
        WebElement openerButtonn = driver.findElement(By.className("view-all-link")); // Reemplaza "opener" con el id real de tu botón
        openerButtonn.click();

        List<String> links = new ArrayList<>();

        // Encuentra el elemento <div> con la clase "browse-module"
        WebElement divElement = driver.findElement(By.xpath("/html/body/div[1]/main/section/div[2]/div"));

        // Dentro del elemento <div>, busca el elemento <ul>
        List<WebElement> tittle = divElement.findElements(By.className("game-tile"));

//        System.out.println("JUEGOS");

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
            List<WebElement> juegoNombreMod = driver.findElements(By.xpath("/html/body/div[1]/main/div[2]/div"));

            for (WebElement JuegoElementsMods : juegoNombreMod) {
                System.out.println(JuegoElementsMods.findElement(By.tagName("h1")).getText());
                System.out.println(JuegoElementsMods.findElement(By.tagName("p")).getText() + "\n");

                // Haz clic en el botón "opener"
                WebElement openerButton = driver.findElement(By.className("view-all-link")); // Reemplaza "opener" con el id real de tu botón
                openerButton.click();

//                WebElement resultsContainer = driver.findElement(By.className("results-container"));
//
//                List<WebElement> projectCards = driver.findElements(By.className("project-card"));
//
//                for (WebElement projectCard : projectCards) {
//                    // Obtener el nombre del proyecto
//                    System.out.println(projectCard.findElement(By.className("name")).findElement(By.tagName("span")).getText());
//
//                    // Obtener el nombre del autor
//                    System.out.println(projectCard.findElement(By.className("by-author-link")).getText().replaceAll("\\n", " ").replaceAll("\\r", ""));
//
//                    // Descripcion
//                    System.out.println(projectCard.findElement(By.className("description")).getText());
//
//                    // Descripcion
//                    System.out.println("\n");
//
//                    WebElement detailsList = projectCard.findElement(By.className("details-list"));
//                    List<WebElement> detailItems = detailsList.findElements(By.tagName("li"));
//
//                    for (WebElement detailItem : detailItems) {
//                        System.out.println(detailItem.getText());
//                    }
//
//                    System.out.println("\n");
//
//                    WebElement categoriesList = projectCard.findElement(By.className("categories"));
//                    List<WebElement> categoryItems = categoriesList.findElements(By.tagName("li"));
//
//                    for (WebElement categoryItem : categoryItems) {
//                        System.out.println(categoryItem.getText());
//                    }
//
//                    System.out.println("\n");

                    List<String> juegosMods = new ArrayList<>();

                    // Encuentra todos los elementos <a> dentro del elemento con la clase "shelf"
                    List<WebElement> tittle2 = driver.findElements(By.className("overlay-link"));

                    // Itera sobre los elementos <a> y obtén el valor del atributo "href"
                    for (WebElement a : tittle2) {
                        juegosMods.add(a.getAttribute("href"));
                    }

                    // Itera sobre los enlaces de juegos y visita cada uno
                    for (String modsLink : juegosMods) {
                        driver.get(modsLink);
                        Thread.sleep(2000);

                        List<WebElement> byautosssrs = driver.findElements(By.className("project-header"));

                        for (WebElement autorElements : byautosssrs) {
                            System.out.println("\n" + autorElements.findElement(By.tagName("h1")).getText());
                            System.out.println(autorElements.findElement(By.className("by-author-link")).getText().replaceAll("\\n", " ").replaceAll("\\r", "") + autorElements.findElement(By.className("ellipsis")).getText() + "\n");
                        }

                        WebElement categorii = driver.findElement(By.className("aside-box"));

                        List<WebElement> byautors = categorii.findElements(By.id("project-categories"));

                        for (WebElement autorElements : byautors) {
                            System.out.println(autorElements.findElement(By.className("links")).findElement(By.tagName("a")).getText());
                        }
                    }

//                    driver.navigate().back();
//                }
            }
        }
        driver.quit();
    }
}


