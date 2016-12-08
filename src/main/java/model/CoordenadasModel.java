package model;

import java.util.ArrayList;
import java.util.List;


public class CoordenadasModel
{

	// primero longitud, despues latitud
	private double x;
	private double y;
	private List<Double> coordenadas;


	public void setCoordenadas(ArrayList coordenadas)
	{
		this.coordenadas = coordenadas;
	}	

	public List<Double> getCoordenadas()
	{
		return this.coordenadas;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public double getX()
	{
		return this.x;
	}

	public double getY()
	{
		return this.y;
	}

}