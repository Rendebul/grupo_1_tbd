package service;

import java.util.List;
import java.util.ArrayList;
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

import facade.FestivalFacade;
import model.Festival;
import model.IndiceModel;
import model.MongoModel;
import model.TweetModel;

@Path("/twitter")
public class MongoSearchService { 

    @EJB 
    FestivalFacade festivalFacadeEJB;
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<TweetModel> findAll(){
        MongoModel mongo = new MongoModel();
        Festival festival = festivalFacadeEJB.find(1);
        return mongo.search(festival);
    }

    @GET
    @Path("indicesContar")
    @Produces({"application/xml", "application/json"})
    public List<IndiceModel> getCount(@PathParam("concierto") Integer concierto){
        MongoModel mongo = new MongoModel();
        List<Festival> festivales = festivalFacadeEJB.findAll();
        List<IndiceModel> indices = new ArrayList<>();
        for (Festival festival : festivales) {
            IndiceModel indice = new IndiceModel(festival.getFestivalName(), mongo.contarFestival(festival));
            indices.add(indice);
        }
        return indices;
    }     


    @GET
    @Path("{concierto}")
    @Produces({"application/xml", "application/json"})
    public List<TweetModel> findConcierto(@PathParam("concierto") Integer concierto){
        MongoModel mongo = new MongoModel();
        Festival festival = festivalFacadeEJB.find(concierto);
        return mongo.search(festival);
    }     

    @GET
    @Path("{concierto}/concepto/{concepto}")
    @Produces({"application/xml", "application/json"})
    public List<TweetModel> findConciertoConcepto(@PathParam("concierto") Integer concierto, @PathParam("concepto") String concepto){
        MongoModel mongo = new MongoModel();
        Festival festival = festivalFacadeEJB.find(concierto);
        return mongo.searchConcepto(festival, concepto);
    }

}