package database.POJO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by damian on 03.04.17.
 */
@Entity
@Table(name = "tags")
public class TAG {
    @Id@GeneratedValue
    @Column
    private int ID;
    @Column(length = 5)
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID")
    private Country countryID;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tagID")
    private Set<PressReleasesTag> pressReleasesTags = new HashSet<PressReleasesTag>(0);

    public TAG() {
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

    public Country getCountryID() {
        return countryID;
    }

    public void setCountryID(Country countryID) {
        this.countryID = countryID;
    }

    public Set<PressReleasesTag> getPressReleasesTags() {
        return pressReleasesTags;
    }

    public void setPressReleasesTags(Set<PressReleasesTag> pressReleasesTags) {
        this.pressReleasesTags = pressReleasesTags;
    }
}
