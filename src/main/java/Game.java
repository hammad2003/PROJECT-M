import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Game {
    private String title;
    private String description;
    private List<ProjectCard> projectCards;

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

    @XmlElement(name = "projectCards")
    public List<ProjectCard> getProjectCards() {
        return projectCards;
    }

    public void setProjectCards(List<ProjectCard> projectCards) {
        this.projectCards = projectCards;
    }
}