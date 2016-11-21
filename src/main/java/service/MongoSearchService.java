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
import model.DataModel;
import model.TweetModel;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    @GET
    @Path("{concierto}/{fecha}")
    @Produces({"application/xml", "application/json"})
    public List<TweetModel> findConciertoFecha(@PathParam("concierto") Integer concierto, @PathParam("fecha") String fecha)
    {
        MongoModel mongo = new MongoModel();
        Festival festival = festivalFacadeEJB.find(concierto);
        //String words[] = fecha.split(".");
        //String fechaInicio = words[0];
        //String fechaFin = words[1];
        return mongo.searchDate(festival, fecha);
    }

    @GET
    @Path("indices/{fechaInicio}/hasta/{fechaFinal}")
    @Produces({"application/xml", "application/json"})
    public List<IndiceModel> findFechas(@PathParam("concierto") Integer concierto, @PathParam("fechaInicio") String fechaInicio, @PathParam("fechaFinal") String fechaFinal)
    {
        @SuppressWarnings("resource")
        MongoModel mongo = new MongoModel();
        List<Festival> festivales = festivalFacadeEJB.findAll();
        List<IndiceModel> indices = new ArrayList<>();
        try { 
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateInicio = (Date) formatter.parse(fechaInicio);
            Date dateFinal = (Date) formatter.parse(fechaFinal);
            Date dateIterator = new Date();
            dateIterator = dateInicio;

            for (Festival festival : festivales) {
                IndiceModel indice = new IndiceModel(festival.getFestivalName(), mongo.contarFestival(festival));
                List<DataModel> datos = new ArrayList<>();
                while (dateIterator.compareTo(dateFinal) <= 0) {
                    String dia = formatter.format(dateIterator);
                    DataModel dato = new DataModel();
                    List<TweetModel> tweets = mongo.searchDate(festival, dia);
                    dato.setTweets(tweets);
                    dato.setFecha(dia);
                    dato.setContador(tweets.size());
                    datos.add(dato);
                    dateIterator = mongo.addDays(dateIterator, 1);
                }
                dateIterator = dateInicio;
                indice.setValues(datos);
                indices.add(indice);
            }
            mongo.closeConnection();
            return indices;
        } catch (ParseException e) {
            mongo.closeConnection();
            return indices;
        }
    }


}