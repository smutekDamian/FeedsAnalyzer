package POJO;

import javax.persistence.*;

/**
 * Created by damian on 03.04.17.
 */
@Entity
@Table(name = "pressreleasestags")
public class PressReleasesTags {
    @Id@GeneratedValue
    @Column
    private int ID;
    @ManyToOne
    @JoinColumn(name = "pressReleaseID")
    private PressReleases pressReleaseID;
    @ManyToOne
    @JoinColumn(name = "tagID")
    private TAGs tagID;

    public PressReleasesTags() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public PressReleases getPressReleaseID() {
        return pressReleaseID;
    }

    public void setPressReleaseID(PressReleases pressReleaseID) {
        this.pressReleaseID = pressReleaseID;
    }

    public TAGs getTagID() {
        return tagID;
    }

    public void setTagID(TAGs tagID) {
        this.tagID = tagID;
    }
}
