package service;

import java.util.List;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import model.MongoModel;
import model.TweetModel;

@Path("/twitter")
public class MongoSearchService { 
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<TweetModel> findAll(){
        //MongoModel mongo = new MongoModel();
        return MongoModel.getInstance().search("Defqon1");
    }

    /*@GET
    @Path("Lollapalooza")
    @Produces({"application/xml", "application/json"})
    public List<TweetModel> findLolla(){
        MongoModel mongo = new MongoModel();
        return mongo.search("Lollapalooza");
    }

    @GET
    @Path("Creamfields")
    @Produces({"application/xml", "application/json"})
    public List<TweetModel> findCreamfield(){
        MongoModel mongo = new MongoModel();
        return mongo.search("Creamfields");
    }*/

    @GET
    @Path("{concierto}")
    @Produces({"application/xml", "application/json"})
    public List<TweetModel> findConcierto(@PathParam("concierto") String concierto){
        //MongoModel mongo = new MongoModel();
        return MongoModel.getInstance().search(concierto);
    }     

    @GET
    @Path("{concierto}/concepto/{concepto}")
    @Produces({"application/xml", "application/json"})
    public List<TweetModel> findConciertoConcepto(@PathParam("concierto") String concierto, @PathParam("concepto") String concepto){
        //MongoModel mongo = new MongoModel();
        return MongoModel.getInstance().searchConcepto(concierto, concepto);
    }

}