import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase PMCurseForge que realiza web scraping en el sitio web "https://curseforge.com/"
 * para obtener información sobre juegos y sus respectivos mods.
 */
public class PMCurseForge {
    /**
     * Método principal que inicia el proceso de web scraping.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     * @throws InterruptedException Si ocurre un error al pausar la ejecución.
     */
    public static void main(String[] args) throws InterruptedException {
        List<Juego> juegos = new ArrayList<>();

        // Imprime la configuración del entorno
        System.out.println(System.getenv("PATH"));
        System.out.println(System.getenv("HOME"));

        // Configuración del WebDriver para Firefox
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox");

        // Inicialización del WebDriver
        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://curseforge.com/");

        // Configuración de objetos para manipulación avanzada del navegador
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Espera breve para garantizar que la página se cargue completamente
        Thread.sleep(5000);

        // Aceptar el aviso de privacidad
        WebElement botonPrivacidadInicio = driver.findElement(By.id("cookiebar-ok"));
        botonPrivacidadInicio.click();

        // Mensaje informativo
        System.out.println("CurseForge" + "\n");

        // Hacer clic en "View all" para ver todos los juegos
        WebElement openerButton = driver.findElement(By.className("view-all-link"));
        openerButton.click();

        // Mensaje informativo
        System.out.println("\n" + "Games");

        // Lista para almacenar enlaces a los juegos
        List<String> links = new ArrayList<>();

        // Encuentra el elemento <div> con la clase "browse-module"
        WebElement divElement = driver.findElement(By.className("browse-module"));

        // Dentro del elemento <div>, busca el elemento <ul>
        List<WebElement> tittle = divElement.findElements(By.className("game-tile"));

        // Imprime los títulos de los juegos
        List<String> NombreJuegos = new ArrayList<>();
        for (WebElement titulosJuego : tittle) {
            String NombreJuego = titulosJuego.findElement(By.tagName("h3")).getText();
            System.out.println(NombreJuego);
            NombreJuegos.add(NombreJuego);
        }

        // Agrega enlaces de juegos a la lista
        for (WebElement a : tittle) {
            links.add(a.getAttribute("href"));
        }

            // Itera sobre los enlaces de los juegos
            for (String juegosLink : links) {
                driver.get(juegosLink);
                Thread.sleep(5000);

                //  Nombre del JuegoMods
                List<WebElement> juegoNombreMod = driver.findElements(By.className("game-description"));

                // Imprime información sobre el juego
                for (WebElement JuegoElementsMods : juegoNombreMod) {
                    try {
                        String NombreJuego = JuegoElementsMods.findElement(By.tagName("h1")).getText();
                        System.out.println(NombreJuego + "\n");

                        String JuegoDescription = JuegoElementsMods.findElement(By.tagName("p")).getText() ;
                        System.out.println(JuegoDescription + "\n");

                        Juego juego = new Juego(NombreJuego,JuegoDescription);

                        // Hacer clic en "View all" para ver todos los mods del juego
                        WebElement openerButtonMods = driver.findElement(By.className("view-all-link"));
                        openerButtonMods.click();

                        // Mensaje informativo
                        System.out.println("Mods" + "\n");

                        // Encuentra el elemento con la clase "results-container"
                        WebElement linksContainer = driver.findElement(By.className("results-container"));

                        // Encuentra todos los elementos con la clase "project-card"
                        List<WebElement> projectCards = linksContainer.findElements(By.className("project-card"));

                        List<Mod> mods = new ArrayList<>();

                        // Itera sobre los elementos "project-card"
                        for (WebElement projectCard : projectCards) {
                            // Mensaje informativo
                            System.out.println("Description" + "\n");

                            // Obtener el nombre del proyecto
                            String NombreMod = projectCard.findElement(By.className("name")).findElement(By.tagName("span")).getText();
                            System.out.println(NombreMod);

                            // Obtener el nombre del autor
                            String NombreModCreador = "By " + projectCard.findElement(By.className("ellipsis")).getText();
                            System.out.println(NombreModCreador);

                            // Descripcion
                            String DescripcionMod = projectCard.findElement(By.className("description")).getText();
                            System.out.println(DescripcionMod);

                            // Descripcion
                            System.out.println("\n" + "\n" + "Details" + "\n");

                            // Detalles del proyecto
                            List<String> DestallesMod = new ArrayList<>();
                            WebElement detailsList = projectCard.findElement(By.className("details-list"));
                            List<WebElement> detailItems = detailsList.findElements(By.tagName("li"));

                            // Imprime detalles del proyecto
                            for (WebElement detailItem : detailItems) {
                                String Detalles = detailItem.getText();
                                System.out.println(Detalles);
                                DestallesMod.add(Detalles);
                            }

                            Mod mod = new Mod(NombreMod,NombreModCreador,DescripcionMod,DestallesMod);

                            // Mensaje informativo
                            System.out.println("\n" + "\n" + "Categories" + "\n");

                            // Categorías del proyecto
                            List<Categoria> CategoriaMod = new ArrayList<>();
                            WebElement categoriesList = projectCard.findElement(By.className("categories"));
                            List<WebElement> categoryItems = categoriesList.findElements(By.tagName("li"));

                            // Imprime categorías del proyecto
                            for (WebElement categoryItem : categoryItems) {
                                String Categoria = categoryItem.getText();
                                Categoria categoria = new Categoria(Categoria);
                                System.out.println(Categoria);
                                CategoriaMod.add(categoria);
                            }

                            mod.setCategories(CategoriaMod);
                            mods.add(mod);

                            // Espaciado para mejorar la legibilidad
                            System.out.println("\n");
                        }

                        juego.setProjectCards(mods);
                        juegos.add(juego);

                    } catch (Exception e) {
                        // Captura la excepción e imprime un mensaje indicando que no se encontró información
                        System.out.println("No se encontró información para el juego actual." + "\n");
                        // Continúa con el siguiente juego
                        continue;
                    }
                }
            }
        // Cerrar el navegador al finalizar
        driver.quit();
    }
}