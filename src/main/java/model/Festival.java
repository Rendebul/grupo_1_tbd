package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;


/**
 * The persistent class for the festival database table.
 * 
 */
@Entity
@Table(name="festival")
@NamedQuery(name="Festival.findAll", query="SELECT a FROM Festival a")
public class Festival implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_festival", unique=true, nullable=false)
    private int festivalId;

    @Column(name="festival_name", nullable=false, length=45)
    private String festivalName;

    @Column(name="filters", length=255)
    private String filters;

    @JoinTable(name="festival_artist" , 
            joinColumns = { 
                   @JoinColumn(name = "id_festival", referencedColumnName = "id_festival")
            },
            inverseJoinColumns = { 
                   @JoinColumn(name = "id_artist", referencedColumnName = "id_artist")
            })
    @ManyToMany 
    private Collection<Artist> artistCollection;

    @JoinTable(name="festival_user" , 
            joinColumns = { 
                   @JoinColumn(name = "id_festival", referencedColumnName = "id_festival")
            }, 
            inverseJoinColumns = { 
                   @JoinColumn(name = "id_user", referencedColumnName = "id_user")
            })
    @ManyToMany 
    private Collection<User> userCollection;

    public Festival() {
    }

    public int getFestivalId() {
        return this.festivalId;
    }

    public void setFestivalId(int festivalId) {
        this.festivalId = festivalId;
    }

    public String getFestivalName() {
        return this.festivalName;
    }

    public void setFestivalName(String name) {
        this.festivalName = name;
    }

    public String getFilters() {
        return this.filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public Collection<Artist> getArtistCollection() {
        return this.artistCollection;
    }
}