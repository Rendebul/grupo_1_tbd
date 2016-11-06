package model;

import java.util.ArrayList;
import java.util.List;


public class CoordinatesModel
{

	// primero longitud, despues latitud
	private List<Float> coordinates;
	private String type;

	public List<Float> getCoordinates()
	{
		return this.coordinates;
	}

	public void setCoordinates(List<Float> coordinates)
	{
		this.coordinates = coordinates;
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}