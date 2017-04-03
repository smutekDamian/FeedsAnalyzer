package database.POJO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by damian on 03.04.17.
 */
@Entity
@Table(name = "feeds")
public class Feeds {
    @Id@GeneratedValue
    @Column
    private int ID;
    @Column(length = 100)
    private String name;
    @ManyToOne
    @JoinColumn(name = "newspaperID")
    private Newspapers newspaperID;
    @Column(length = 25)
    private String section;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "feedID")
    private Set<PressReleases> pressReleases = new HashSet<PressReleases>(0);

    public Feeds() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Newspapers getNewspaperID() {
        return newspaperID;
    }

    public void setNewspaperID(Newspapers newspaperID) {
        this.newspaperID = newspaperID;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Set<PressReleases> getPressReleases() {
        return pressReleases;
    }

    public void setPressReleases(Set<PressReleases> pressReleases) {
        this.pressReleases = pressReleases;
    }
}
