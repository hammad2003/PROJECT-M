import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Juego {
    private String title;
    private String description;
    private List<Mod> mods;

    // Getter and setter methods

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "mods")
    public List<Mod> getProjectCards() {
        return mods;
    }

    public void setProjectCards(List<Mod> mods) {
        this.mods = mods;
    }
}