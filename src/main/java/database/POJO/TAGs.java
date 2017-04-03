package database.POJO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by damian on 03.04.17.
 */
@Entity
@Table(name = "tags")
public class TAGs {
    @Id@GeneratedValue
    @Column
    private int ID;
    @Column(length = 5)
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID")
    private Countries countryID;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tagID")
    private Set<PressReleasesTags> pressReleasesTags = new HashSet<PressReleasesTags>(0);

    public TAGs() {
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

    public Set<PressReleasesTags> getPressReleasesTags() {
        return pressReleasesTags;
    }

    public void setPressReleasesTags(Set<PressReleasesTags> pressReleasesTags) {
        this.pressReleasesTags = pressReleasesTags;
    }
}
