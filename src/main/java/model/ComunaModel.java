package model;



public class ComunaModel
{
	
	private String nombre;
	private int tweets;
	private Double emoteScoreAvg;


	public Double getEmoteScoreAvg()
	{
		return this.emoteScoreAvg;
	}

	public void setEmoteScoreAvg(Double emoteScoreAvg)
	{
		this.emoteScoreAvg = emoteScoreAvg;
	}


	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getTweets()
	{
		return this.tweets;
	}

	public void setTweets(int tweets)
	{
		this.tweets = tweets;
	}

	
}

