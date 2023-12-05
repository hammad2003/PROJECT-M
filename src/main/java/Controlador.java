import com.opencsv.CSVWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 * Clase Controlador que gestiona la persistencia de datos en formatos XML y CSV.
 * Permite guardar la información de juegos y mods en archivos XML y CSV.
 */
public class Controlador {
    List<Juego> juegos;

    /**
     * Constructor de la clase Controlador.
     *
     * @param juegos La lista de juegos a ser gestionada.
     */
    public Controlador(List<Juego> juegos) {
        this.juegos = juegos;
    }

    /**
     * Guarda la información de juegos y mods en un archivo XML.
     */
    public void guardarXML(){

        // En la siguiente variable es necesario poner la ruta en la que deseas guardar el fichero.
        String xmlFilePath="src/main/CurseForge.xml";

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.newDocument();


            //La siguiente línea es para declarar cuál es el nodo raíz del documento XML

            Node rootNode = document.createElement("Juegos");
            document.appendChild(rootNode);

            //El siguiente for es para que cree un nodo por cada game, lo mismo para cualquier bucle que se vea de este tipo

            for (Juego juego : juegos) {
                Node juegoNode = document.createElement("Juego");
                rootNode.appendChild(juegoNode);

                Node juegoNombre = document.createElement("Nombre");
                juegoNombre.appendChild(document.createTextNode(juego.getTitle()));
                juegoNode.appendChild(juegoNombre);

                Node juegoDescripcion = document.createElement("Descripcion");
                juegoDescripcion.appendChild(document.createTextNode(juego.getDescription()));
                juegoNode.appendChild(juegoDescripcion);

                Node nodeMods = document.createElement("Mods");
                juegoNode.appendChild(nodeMods);

                for (Mod mod : juego.getProjectCards()) {
                    Node modNode = document.createElement("Mod");
                    nodeMods.appendChild(modNode);

                    Node modName = document.createElement("Nombre");
                    modName.appendChild(document.createTextNode(mod.getProjectName()));
                    modNode.appendChild(modName);

                    Node modAuthor = document.createElement("Autor");
                    modAuthor.appendChild(document.createTextNode(mod.getAuthor()));
                    modNode.appendChild(modAuthor);

                    Node modDescription = document.createElement("Descripcion");
                    modDescription.appendChild(document.createTextNode(mod.getProjectDescription()));
                    modNode.appendChild(modDescription);

                    Node nodeDetalles = document.createElement("Detalles");
                    modNode.appendChild(nodeDetalles);

                    for (String string : mod.getDetails()) {
                        Node detailNode = document.createElement("Detalle");
                        nodeDetalles.appendChild(detailNode);

                        Node details = document.createElement("Descripcion");
                        details.appendChild(document.createTextNode(string));
                        detailNode.appendChild(details);
                    }

                    Node nodeCategories = document.createElement("Categorias");
                    modNode.appendChild(nodeCategories);

                    for (Categoria categoria : mod.getCategories()) {
                        Node categoriaNode = document.createElement("Categoria");
                        nodeCategories.appendChild(categoriaNode);

                        Node nombre = document.createElement("Nombre");
                        nombre.appendChild(document.createTextNode(categoria.getNombre()));
                        categoriaNode.appendChild(nombre);
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // El yes es para que tabule de forma automática

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);

            System.out.println("Datos guardados correctamente en el archivo XML");

        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Guarda la información de juegos y mods en un archivo CSV.
     */
    public void guardarCSV() {

        // En la siguiente variable es necesario poner la ruta en la que deseas guardar el fichero.
        String csvFilePath = "src/main/CurseForge.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {

            // Escribir encabezados del CSV
            writer.writeNext(new String[]{"NombreJuego", "DescripcionJuego", "NombreMod", "AutorMod", "DescripcionMod", "DetalleMod", "CategoriaMod"});

            // Iterar sobre la lista de juegos y escribir en el archivo CSV
            for (Juego juego : juegos) {
                String nombreJuego = juego.getTitle();
                String descripcionJuego = juego.getDescription();

                for (Mod mod : juego.getProjectCards()) {
                    String nombreMod = mod.getProjectName();
                    String autorMod = mod.getAuthor();
                    String descripcionMod = mod.getProjectDescription();

                    for (String detalle : mod.getDetails()) {
                        for (Categoria categoria : mod.getCategories()) {
                            String nombreCategoria = categoria.getNombre();

                            // Escribir línea en el archivo CSV
                            writer.writeNext(new String[]{nombreJuego, descripcionJuego, nombreMod, autorMod, descripcionMod, detalle, nombreCategoria});
                        }
                    }
                }
            }

            System.out.println("Datos guardados correctamente en el archivo CSV");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
