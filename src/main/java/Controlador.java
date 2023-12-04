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

public class Controlador {
    List<Juego> juegos;

    public Controlador(List<Juego> juegos) {
        this.juegos = juegos;
    }

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

            for(Juego juego: juegos){
                Node juegoNode = document.createElement("Juego");
                rootNode.appendChild(juegoNode);

                Node juegoNombre = document.createElement("Nombre");
                juegoNombre.appendChild(document.createTextNode(juego.getTitle()));
                juegoNode.appendChild(juegoNombre);

                Node juegoDescripcion = document.createElement("Descripcion");
                juegoDescripcion.appendChild(document.createTextNode(juego.getTitle()));
                juegoNode.appendChild(juegoDescripcion);


                Node nodeMods = document.createElement("Mods");

                for(Mod mod : juego.getProjectCards()){
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


                    Node nodeDestalles = document.createElement("Detalles");

                    for(String string : mod.getDetails()){
                        Node detailNode = document.createElement("Detalle");
                        nodeDestalles.appendChild(modNode);

                        Node details = document.createElement("Descripcion");
                        details.appendChild(document.createTextNode(string));
                        detailNode.appendChild(details);
                    }


                    Node nodeCategories = document.createElement("Categorias");

                    for(Categoria categoria : mod.getCategories()){
                        Node categoriaNode = document.createElement("Categoria");
                        nodeCategories.appendChild(modNode);

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
}
