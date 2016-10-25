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

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MongoModel    {
    private Mongo mongo;
    private  DB db;
    private DBObject textSearchCommand;
    private DBCollection collection;

    private static MongoModel INSTANCE;

    public static MongoModel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MongoModel();
            return INSTANCE;
        }else
            return INSTANCE;
    }

    private MongoModel() {
        this.mongo = new Mongo("localhost", 27017);
        this.textSearchCommand = new BasicDBObject();
        this.db = mongo.getDB("tbd");
        this.collection = db.getCollection("tweets");

    }
    
    public List<TweetModel> search(String concierto) {
        List<TweetModel> list = new ArrayList<>();
        String searchString = this.selectBase(concierto);
        DBCursor cursor = this.collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchString)));
        while (cursor.hasNext()) {
            DBObject document = cursor.next();
            TweetModel data = new TweetModel();
            data.setText((String) document.get("text"));
            list.add(data);
        }

        return list;     
    }

    public List<TweetModel> searchDate(String concierto, String str_date){
        List<TweetModel> list = new ArrayList<>();
        String searchString = this.selectBase(concierto);
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            Date date = (Date) formatter.parse(str_date);
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
            BasicDBObject busqueda = new BasicDBObject("timestamp_ms", timeStampDate );
            busqueda.append("$text", new BasicDBObject("$search", searchString));
            DBCursor cursor = this.collection.find(busqueda);
            while (cursor.hasNext()) {
                DBObject document = cursor.next();
                TweetModel data = new TweetModel();
                data.setText((String) document.get("text"));
                list.add(data);
            }
            return list;  
        } catch (ParseException e) {
            return list;
        }
    }

    public List<TweetModel> searchConcepto(String concierto, String concepto) {
        List<TweetModel> list = new ArrayList<>();
        String searchString = this.selectBase(concierto);
        concepto = concepto.replace("+"," ");
        BasicDBObject busqueda = new BasicDBObject("$text", new BasicDBObject("$search", searchString));
        busqueda.append("$text", new BasicDBObject("$search", concepto));
        DBCursor cursor = this.collection.find(busqueda);
        while (cursor.hasNext()) {
            DBObject document = cursor.next();
            TweetModel data = new TweetModel();
            data.setText((String) document.get("text"));
            list.add(data);
        }
        return list;     
    }

    public String selectBase(String concierto) {
        if (concierto.equals("Lollapalooza")) {
            return "LollaCL @lollapaloozacl PreguntaLollaCL RPLollaWeekend /Lollapalooza versión Chile2017/ lollapaloozachile2017 RPLolla LollaCL2017";  
        } else if (concierto.equals("Creamfields")) {
            return "CreamfieldsCL2016 CreamfieldsChile creamfieldsCL";
        } else if (concierto.equals("Defqon1")){
            return "Defqon1Chile DEFQON1 Defqon1CL defqon1chile2016 DEFQON12016 DefqonChile";
        } else if (concierto.equals("Fauna")){
            return "FaunaPrimavera FaunaPrimavera PrimaveraFauna FaunaPrimaveraFest pf2016";
        } else {
            return "";
        }
    }
}
