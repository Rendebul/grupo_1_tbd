package model;

public class UserMentionModel
{
	private long id;
	private String idStr;
	private int[] indices;
	private String name;
	private String screenName;

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getIdStr()
	{
		return this.idStr;
	}

	public void setIdStr(String id)
	{
		this.idStr = id;
	}

	public int[] getIndices()
	{
		return this.indices;
	}

	public void setIndices(int[] indices)
	{
		this.indices = indices;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getScreenName()
	{
		return this.screenName;
	}

	public void setScreenName(String name)
	{
		this.screenName = name;
	}
}