package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT a FROM User a")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_user", unique=true, nullable=false)
    private int userId;

    @Column(name="user_name", nullable=false, length=45)
    private String userName;

    @Column(name="first_name", nullable=false, length=45)
    private String firstName;

    @Column(name="last_name", nullable=false, length=45)
    private String lastName;

    @Column(name="password", nullable=false, length=45)
    private String password;

    @ManyToOne
    @JoinColumn(name="id_rol")
    private Rol rol;

    @JoinTable(name="user_artist" , 
            joinColumns = { 
                   @JoinColumn(name = "id_user", referencedColumnName = "id_user")
            },
            inverseJoinColumns = { 
                   @JoinColumn(name = "id_artist", referencedColumnName = "id_artist")
            })
    @ManyToMany 
    private Collection<Artist> artistCollection;

    public User() {
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Artist> getArtistCollection() {
        return this.artistCollection;
    }

}