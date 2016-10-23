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
    private DBCollection collection;

    public MongoModel() {
        this.mongo = new Mongo("localhost", 27017);
        this.textSearchCommand = new BasicDBObject();
        this.db = mongo.getDB("tbd");
        this.collection = db.getCollection("tweets");

    }
    
    public List<TweetModel> searchLolla() {
        List<TweetModel> list = new ArrayList<>();
        String searchString = "LollaCl";
        DBCursor cursor = this.collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchString)));
        while (cursor.hasNext()) {
            DBObject document = cursor.next();
            TweetModel data = new TweetModel();
            data.setText((String) document.get("text"));
            list.add(data);
        }

        return list;     
    }
}