import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controlador {

    public static void main(String[] args) {
        // Lógica para realizar el web scraping y obtener los datos
        PMCurseForge pmCurseForge = webScrapingLogic();

        // Generar XML
        generarXML(pmCurseForge);

        // Generar CSV
        generarCSV(pmCurseForge);
    }

    private static PMCurseForge webScrapingLogic() {
        // Aquí debes realizar la lógica de web scraping y devolver una instancia de PMCurseForge
        // poblada con los datos recopilados utilizando las clases Juego, Mod y Categoria.
        return new PMCurseForge(); // Reemplaza esto con la lógica real.
    }

    private static void generarXML(PMCurseForge pmCurseForge) {
        try {
            // Configura el contexto JAXB
            JAXBContext context = JAXBContext.newInstance(PMCurseForge.class);

            // Crea el marshaller
            Marshaller marshaller = context.createMarshaller();

            // Convierte el objeto PMCurseForge a un documento DOM
            Document document = createDocument();

            // Marshal el objeto a un documento DOM
            marshaller.marshal(pmCurseForge, document);

            // Guarda el documento XML en un archivo
            saveXMLToFile(document, "output.xml");

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void generarCSV(PMCurseForge pmCurseForge) {
        try (FileWriter writer = new FileWriter("output.csv")) {
            // Escribe las cabeceras del CSV
            writer.append("Game Title,Game Description,Mod Project Name,Mod Author,Mod Description,Mod Details,Mod Categories\n");

            // Recorre los juegos y mods para escribir en el CSV
            for (Juego juego : pmCurseForge.getJuegos()) {
                for (Mod mod : juego.getMods()) {
                    // Escribe la información en el CSV
                    writer.append(String.format("%s,%s,%s,%s,%s,%s,%s\n",
                            juego.getTitle(),
                            juego.getDescription(),
                            mod.getProjectName(),
                            mod.getAuthor(),
                            mod.getProjectDescription(),
                            String.join("; ", mod.getDetails()),
                            String.join("; ", mod.getCategories())));
                }
            }

            System.out.println("CSV generado exitosamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Resto del código necesario, como los métodos createDocument y saveXMLToFile, permanecen sin cambios.
}
