package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class TweetModel {

    //falta places
    private String text;
    private long inReplyToStatusId;
    private String inReplyToStatusIdStr;
    private Date createdAt;
    private int favoriteCount;
    private boolean favorited;
    private String filterLevel;
    private long id;
    private String idStr;
    private String inReplyToScreenName;
    private long inReplyToUserId;
    private String inReplyToUserIdStr;
    private String lang;
    private boolean possiblySensitive;
    private long quotedStatusId;
    private String quotedStatusIdStr;
    private int retweetCount;
    private boolean retweeted;
    private String source;
    private boolean truncated;
    private TweetModel quotedStatus;
    private TweetModel retweetedStatus;
    private List<Double> coordinates;
    private HashtagModel[] hashtags;
    private UserMentionModel[] userMentions;
    private List<ContributorModel> contributors;
    private UserModel user;
    private PlaceModel place;
    private double emoteScore;
    private String comuna;

    public void setComuna(String comuna)
    {
        this.comuna = comuna;
    }

    public String getComuna()
    {
        return this.comuna;
    }

    public void setEmoteScore(double emoteScore)
    {
        this.emoteScore = emoteScore;
    }

    public double getEmoteScore()
    {
        return this.emoteScore;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setInReplyToStatusIdStr(String id)
    {
    	this.inReplyToStatusIdStr = id;
    }
    
    public String getInReplyToStatusIdStr()
    {
    	return this.inReplyToStatusIdStr;
    }

    public void setInReplyToStatusId(long id)
    {
    	this.inReplyToStatusId = id;
    }
    
    public long getInReplyToStatusId()
    {
    	return this.inReplyToStatusId;
    }

    public void setCreatedAt(Date ca)
    {
    	this.createdAt = ca;
    }

    public Date getCreatedAt()
    {
    	return this.createdAt;
    }

    public int getFavoriteCount()
    {
    	return this.favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount)
    {
    	this.favoriteCount = favoriteCount;
    }

    public boolean getFavorited()
    {
    	return this.favorited;
    }

    public void setFavorited(boolean fav)
    {
    	this.favorited = fav;
    }

    public String getFilterLevel()
    {
    	return this.filterLevel;
    }

    public void setFilterLevel(String level)
    {
    	this.filterLevel = level;
    }

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

    public String getInReplyToScreenName()
    {
    	return this.inReplyToScreenName;
    }

    public void setInReplyToScreenName(String name)
    {
    	this.inReplyToScreenName = name;
    }

    public long getInReplyToUserId()
    {
    	return this.inReplyToUserId;
    }

    public void setInReplyToUserId(long id)
    {
    	this.inReplyToUserId = id;
    }

    public String getInReplyToUserIdStr()
    {
    	return this.inReplyToUserIdStr;
    }

    public void setInReplyToUserIdStr(String id)
    {
    	this.inReplyToUserIdStr = id;
    }

    public String getLang()
    {
    	return this.lang;
    }

    public void setLang(String lang)
    {
    	this.lang = lang;
    }

    public boolean getPossiblySensitive()
    {
    	return this.possiblySensitive;
    }

    public void setPossiblySensitive(boolean sen)
    {
    	this.possiblySensitive = sen;
    }

    public long getQuotedStatusId()
    {
    	return this.quotedStatusId;
    }

    public void setQuotedStatusId(long id)
    {
    	this.quotedStatusId = id;
    }

    public String getQuotedStatusIdStr()
    {
    	return this.quotedStatusIdStr;
    }

    public void setQuotedStatusIdStr(String id)
    {
    	this.quotedStatusIdStr = id;
    }

    public int getRetweetCount()
    {
    	return this.retweetCount;
    }

    public void setRetweetCount(int tweets)
    {
    	this.retweetCount = tweets;
    }

    public boolean getRetweeted()
    {
    	return this.retweeted;
    }

    public void setRetweeted(boolean ret)
    {
    	this.retweeted = ret;
    }

    public String getSource()
    {
    	return this.source;
    }

    public void setSource(String source)
    {
    	this.source = source;
    }

    public boolean getTruncated()
    {
    	return this.truncated;
    }

    public void setTruncated(boolean truncated)
    {
    	this.truncated = truncated;
    }

    public TweetModel getQuotedStatus()
    {
        return this.quotedStatus;
    }

    public void setQuotedStatus(TweetModel tweet)
    {
        this.quotedStatus = tweet;
    }

    public TweetModel getRetweetedStatus()
    {
        return this.retweetedStatus;
    }

    public void setRetweetedStatus(TweetModel tweet)
    {
        this.retweetedStatus = tweet;
    }

    public List<Double> getCoordinates()
    {
        return this.coordinates;
    }

    public void setCoordinates(List<Double> coordinates)
    {
        this.coordinates = coordinates;
    }

    public HashtagModel[] getHashtags()
    {
        return this.hashtags;
    }

    public void setHashtags(HashtagModel[] hashtags)
    {
        this.hashtags = hashtags;
    }

    public UserMentionModel[] getUserMentions()
    {
        return this.userMentions;
    }

    public void setUserMentions(UserMentionModel[] userMentions)
    {
        this.userMentions = userMentions;
    }

    public List<ContributorModel> getContributors()
    {
        return this.contributors;
    }

    public void setContributors(List<ContributorModel> contributors)
    {
        this.contributors = contributors;
    }

    public UserModel getUser()
    {
        return this.user;
    }

    public void setUser(UserModel user)
    {
        this.user = user;
    }

    public PlaceModel getPlace()
    {
        return this.place;
    }

    public void setPlace(PlaceModel place)
    {
        this.place = place;
    }
}
