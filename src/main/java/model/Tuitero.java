package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class Tuitero {

    //falta places
    private String name;
    private String id;
    private int score;


    public void setName(String name)
    {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
    public void setScore(int score)
    {
        this.score = score;
    }
    public int getScore()
    {
        return this.score;
    }
}