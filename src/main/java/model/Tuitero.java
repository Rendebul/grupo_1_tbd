package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class  Tuitero{
	public String nombre;

	public Tuitero(String nombre){
		this.nombre=nombre;
	}

	public void setNombre(String nombre){
		this.nombre=nombre;
	}

	public String getNombre(){
		return(this.nombre);
	}

}
