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
@Table(name="nationality")
@NamedQuery(name="Nationality.findAll", query="SELECT a FROM Nationality a")
public class Nationality implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_nationality", unique=true, nullable=false)
    private int nationalityId;

    @Column(name="country", nullable=false, length=45)
    private String country;

    
    @OneToMany(mappedBy="nationality")
    private Collection<Artist> artistCollection;

    public Nationality() {
    }

    public int getNationalityId() {
        return this.nationalityId;
    }

    public void setNationalityId(int id) {
        this.nationalityId = id;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country){
        this.country = country;
    }

}