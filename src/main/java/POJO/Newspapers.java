package POJO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by damian on 03.04.17.
 */
@Entity
@Table(name = "newspapers")
public class Newspapers {
    @Id@GeneratedValue
    @Column
    private int ID;
    @Column(length = 100)
    private String name;
    @ManyToOne
    @JoinColumn(name = "countryID")
    private Countries countryID;
    @ManyToOne
    @JoinColumn(name = "languageID")
    private Languages languageID;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "newspaperID")
    private Set<Feeds> feeds = new HashSet<Feeds>(0);

    public Newspapers() {
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

    public Countries getCountryID() {
        return countryID;
    }

    public void setCountryID(Countries countryID) {
        this.countryID = countryID;
    }

    public Languages getLanguageID() {
        return languageID;
    }

    public void setLanguageID(Languages languageID) {
        this.languageID = languageID;
    }
}
