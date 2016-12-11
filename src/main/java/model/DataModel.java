package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;


public class DataModel implements Serializable {
    private String fecha;
    private long contador;
    private long score;
    private List<TweetModel> tweets;

    public DataModel() {
        this.fecha = "";
        this.contador = 0;
        this.score = 4;
        this.tweets = new ArrayList<>();
    }

    public DataModel(String fecha, List<TweetModel> tweets)
    {
        this.fecha = fecha;
        this.contador = tweets.size();
        this.score = 123;
        this.tweets = tweets;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getContador()
    {
        return this.contador;
    }

    public void setContador(long contador)
    {
        this.contador = contador;
    }

    public void setTweets(List<TweetModel> tweets)
    {
        this.tweets = tweets;
    }

    public List<TweetModel> getTweets()
    {
        return this.tweets;
    }

    public void addTweets(TweetModel tweet)
    {
        this.tweets.add(tweet);
    }

    public void setScore(long score)
    {
        this.score = score;
    }

    public long getScore()
    {
        return this.score;
    }

    public void setScore()
    {
        int largo = this.tweets.size();
        long contador = 0;
        for (TweetModel tweet : this.tweets ) {
            contador += tweet.getEmoteScore();
        }
        if (largo > 0) {
            this.score = contador/largo;    
        } else {
            this.score = 0;
        }
    }
}