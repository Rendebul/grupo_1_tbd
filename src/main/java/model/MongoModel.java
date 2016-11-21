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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MongoModel    {
    private Mongo mongo;
    private  DB db;
    private DBObject textSearchCommand;
    private DBCollection collection;

    private static MongoModel INSTANCE;

    public MongoModel() {
        this.mongo = new Mongo("localhost", 27017);
        this.textSearchCommand = new BasicDBObject();
        this.db = mongo.getDB("tbd");
        this.collection = db.getCollection("tweets");
    }
    
    public void convertirFechas()
    {
        DBCursor cursor = this.collection.find();
        while(cursor.hasNext())
        {
            DBObject document = cursor.next();
            DBObject updated = new BasicDBObject();
            updated.put("$set", new BasicDBObject("created_at", new Date((String)document.get("created_at"))));
            this.collection.update(document, updated);
        }
    }

    public List<TweetModel> search(Festival concierto) {
        List<TweetModel> list = new ArrayList<>();
        String searchString = concierto.getFilters();
        DBCursor cursor = this.collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchString)));
        while (cursor.hasNext()) {
            DBObject document = cursor.next();
            TweetModel data = new TweetModel();
            data.setText((String) document.get("text"));
            //por alguna razon no castea la fecha como string ni date
            //data.setCreatedAt((Date)document.get("created_at"));
            if(document.get("in_reply_to_status_id") != null)
                data.setInReplyToStatusId((long) document.get("in_reply_to_status_id"));
            if(document.get("in_reply_to_status_id_str") != null)
                data.setInReplyToStatusIdStr((String) document.get("in_reply_to_status_id_str"));
            if((document.get("favorite_count")) != null)
                data.setFavoriteCount((int) document.get("favorite_count"));
            if((document.get("favorited")) != null)
                data.setFavorited((boolean) document.get("favorited"));
            data.setFilterLevel((String) document.get("filter_level"));
            /*//data.setId((long) document.get("id"));
            if(document.get("in_reply_to_screen_name") != null)
                data.setInReplyToScreenName((String) document.get("in_reply_to_screen_name"));
            if(document.get("in_reply_to_user_id") != null)
                data.setInReplyToUserId((long) document.get("in_reply_to_user_id"));
            if(document.get("in_reply_to_user_id_str") != null)
                data.setInReplyToUserIdStr((String) document.get("in_reply_to_user_id_str"));*/
            if(document.get("lang") != null)
                data.setLang((String) document.get("lang"));
            if(document.get("possibly_sensitive") != null)
                data.setPossiblySensitive((boolean) document.get("possibly_sensitive"));
            if(document.get("quoted_status_id") != null)
                data.setQuotedStatusId((long) document.get("quoted_status_id"));
            if(document.get("quoted_status_id_str") != null)
                data.setQuotedStatusIdStr((String) document.get("quoted_status_id_str"));
            //if(document.get("retweet_count") != null)
              //  data.setRetweetCount((int) document.get("retweet_count"));
            data.setRetweeted((boolean) document.get("retweeted"));
            data.setSource((String) document.get("source"));
            data.setTruncated((boolean) document.get("truncated"));

            list.add(data);
        }

        return list;     
    }

    public List<TweetModel> searchDate(Festival concierto, String fecha){
        List<TweetModel> list = new ArrayList<>();
        String searchString = concierto.getFilters();

        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateInicio = (Date) formatter.parse(fecha);
            Calendar c = Calendar.getInstance();
            c.setTime(formatter.parse(fecha));
            c.add(Calendar.DATE, 1);  // agrega 1 dia a fecha inicio
            Date dateFin = formatter.parse(formatter.format(c.getTime()));  // fechafin = fechainicio + 1 dia
            //Date dateFin = (Date) formatter.parse(fechaInicio);
            //java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            //busca todos los tweets entre fecha inicio y fecha fin
            BasicDBObject busqueda = new BasicDBObject("created_at", new BasicDBObject("$gte", dateInicio)
                .append("$lte", dateFin));
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

    public List<TweetModel> searchConcepto(Festival concierto, String concepto) {
        List<TweetModel> list = new ArrayList<>();
        String searchString = concierto.getFilters();
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

    public long contarFestival(Festival concierto) {
        List<TweetModel> list = new ArrayList<>();
        String searchString = concierto.getFilters();
        long contar = this.collection.count(new BasicDBObject("$text", new BasicDBObject("$search", searchString)));
        return contar;   
    }

    public void closeConnection() {
        this.mongo.close();
    }

    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
                
        return cal.getTime();
    }

}
