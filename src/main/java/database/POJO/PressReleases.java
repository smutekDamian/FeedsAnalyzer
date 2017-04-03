package database.POJO;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by damian on 03.04.17.
 */
@Entity
@Table(name = "pressreleases")
public class PressReleases {
    @Id@GeneratedValue
    @Column
    private int ID;
    @ManyToOne
    @JoinColumn(name = "feedID")
    private Feeds feedID;
    @Column(length = 250)
    private String title;
    @Column
    private Date date;
    @Column(length = 500)
    private String content;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pressReleaseID")
    private Set<PressReleasesTags> pressReleasesTags = new HashSet<PressReleasesTags>(0);

    public PressReleases() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Feeds getFeedID() {
        return feedID;
    }

    public void setFeedID(Feeds feedID) {
        this.feedID = feedID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<PressReleasesTags> getPressReleasesTags() {
        return pressReleasesTags;
    }

    public void setPressReleasesTags(Set<PressReleasesTags> pressReleasesTags) {
        this.pressReleasesTags = pressReleasesTags;
    }
}
