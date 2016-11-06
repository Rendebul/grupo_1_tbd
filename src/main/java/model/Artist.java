package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;


/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@Table(name="artist")
@NamedQuery(name="Artist.findAll", query="SELECT a FROM Artist a")
public class Artist implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_artist", unique=true, nullable=false)
    private int artistId;

    @Column(name="artist_name", nullable=false, length=45)
    private String artistName;

    @ManyToOne
    @JoinColumn(name="nationality_id")
    private Nationality nationality;

    @JoinTable(name="festival_artist" , 
            joinColumns = { 
                   @JoinColumn(name = "id_artist", referencedColumnName = "id_artist")
            }, 
            inverseJoinColumns = { 
                   @JoinColumn(name = "id_festival", referencedColumnName = "id_festival")
            })
    @ManyToMany 
    private Collection<Festival> festivalCollection;

    @JoinTable(name="user_artist" , 
            joinColumns = { 
                   @JoinColumn(name = "id_artist", referencedColumnName = "id_artist")
            }, 
            inverseJoinColumns = { 
                   @JoinColumn(name = "id_user", referencedColumnName = "id_user")
            })
    @ManyToMany 
    private Collection<User> userCollection;

    public Artist() {
    }

    public int getArtistId() {
        return this.artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public void setArtistName(String name) {
        this.artistName = name;
    }

    public Nationality getNationality() {
        return this.nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

}