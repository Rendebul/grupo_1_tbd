package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class IndiceModel implements Serializable {
    private String key;
    private long contador;
    private List<DataModel> values;

    public IndiceModel() {
        this.values = new ArrayList<>();
    }

    public IndiceModel(String key, long contador)
    {
        this.key = key;
        this.contador = contador;
        this.values = new ArrayList<>();
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getContador()
    {
        return this.contador;
    }

    public void setContador(long contador)
    {
        this.contador = contador;
    }

    public List<DataModel> getValues() {
        return values;
    }

    public void setValues(List<DataModel> values) {
        this.values = values;
    }

    public void addValue(DataModel dato) {
        this.values.add(dato);
    }
}