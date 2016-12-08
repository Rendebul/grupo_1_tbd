package model;

import java.util.ArrayList;
import java.util.List;


public class CoordenadasModel
{

	// primero longitud, despues latitud
	private double x;
	private double y;
	private String type;

	public CoordenadasModel(double x, double y)
	{
		this.x = x;
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