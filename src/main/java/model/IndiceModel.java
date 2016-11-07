package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;


public class IndiceModel implements Serializable {
    private String nombre;
    private long contador;

    public IndiceModel() {

    }

    public IndiceModel(String nombre, long contador)
    {
        this.nombre = nombre;
        this.contador = contador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getContador()
    {
        return this.contador;
    }

    public void setContador(long contador)
    {
        this.contador = contador;
    }
}