package POJO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by damian on 03.04.17.
 */
@Entity
@Table(name = "languages")
public class Languages {
    @Id@GeneratedValue
    @Column
    private int ID;
    @Column(length = 25)
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "languageID")
    private Set<Newspapers> newspapers = new HashSet<Newspapers>(0);

    public Languages() {
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

    public Set<Newspapers> getNewspapers() {
        return newspapers;
    }

    public void setNewspapers(Set<Newspapers> newspapers) {
        this.newspapers = newspapers;
    }
}
