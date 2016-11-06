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
@Table(name="rol")
@NamedQuery(name="Rol.findAll", query="SELECT a FROM Rol a")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_rol", unique=true, nullable=false)
    private int rolId;

    @Column(name="rol_name", nullable=false, length=45)
    private String rolName;

    
    @OneToMany(mappedBy="rol")
    private Collection<User> userCollection;

    public Rol() {
    }

    public int getRolId() {
        return this.rolId;
    }

    public void setRolId(int id) {
        this.rolId = id;
    }

    public String getRolName() {
        return this.rolName;
    }

    public void setRolName(String rolName){
        this.rolName = rolName;
    }

}