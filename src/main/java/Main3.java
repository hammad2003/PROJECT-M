import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main3 {

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

                List<WebElement> projectCards = driver.findElements(By.className("project-card"));

                for (WebElement projectCard : projectCards) {
                    // Obtener el nombre del proyecto
                    System.out.println(projectCard.findElement(By.className("name")).findElement(By.tagName("span")).getText());

                    // Obtener el nombre del autor
                    System.out.println(projectCard.findElement(By.className("by-author-link")).getText().replaceAll("\\n", " ").replaceAll("\\r", ""));

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