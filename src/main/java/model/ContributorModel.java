package model;

import java.util.List;
import java.util.ArrayList;


public class ContributorModel
{
	private long id;
	private String idStr;
	private String screenName;

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getidStr()
	{
		return this.idStr;
	}

	public void setIdStr(String id)
	{
		this.idStr = id;
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