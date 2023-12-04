import javax.xml.bind.annotation.XmlElement;

public class Categoria {
    private String nombre;

    // Constructor vacío requerido para JAXB
    public Categoria() {
    }

    // Constructor con parámetros
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}