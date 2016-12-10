package model;
import java.util.ArrayList;
import java.util.List;


public class FestivalComunaModel
{
	private String festival;
	private List<ComunaModel> comunas;

	public String getFestival()
	{
		return this.festival;
	}

	public void setFestival(String festival)
	{
		this.festival = festival;
	}

	public List<ComunaModel> getComunas()
	{
		return this.comunas;
	}

	public void setComunas(List<ComunaModel> comunas)
	{
		this.comunas = comunas;
	}
}