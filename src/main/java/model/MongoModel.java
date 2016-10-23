package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoModel    {
    private Mongo mongo;
    private  DB db;
    private DBObject textSearchCommand;

    public MongoModel() {
        this.mongo = new Mongo("localhost", 27017);
        this.textSearchCommand = new BasicDBObject();
        this.db = mongo.getDB("tbd");
    }
    
    public CommandResult searchLolla() {
        textSearchCommand.put("text", "tweets");
        textSearchCommand.put("search", "LollaCL");
        return db.command(textSearchCommand);       
    }
}