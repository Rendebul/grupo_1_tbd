package service;

import java.util.List;
import java.util.Collection;
import java.util.logging.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject; 

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;  
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.core.Response;

import facade.UserFacade;
import model.User;
import model.Tuitero;
import model.Neo4jModel;

@Path("/usuarios")
public class UserService {
    
    @EJB 
    UserFacade userFacadeEJB;

    Logger logger = Logger.getLogger(UserService.class.getName());
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<User> findAll(){
        return userFacadeEJB.findAll();
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public User find(@PathParam("id") Integer id) {
        return userFacadeEJB.find(id);
    }

    /*
    *Inicio codigo crear nodos usuarios
    *Asegurarse de tener neo4j y mongo abiertos y el repo descargado y editado con su usuario de neo4j
    *La direccion despues del cd probablemente cambie dependiendo de donde descarguen el repo
    */
    @GET
    @Path("crearNodos")
    @Produces("text/plain")
    public String createNodes() {
        Neo4jModel n4 = new Neo4jModel();
        try {
            n4.crearGrafos();
        } catch (IOException e) {
            return "Error";
        }
        return "Base de datos Neo4j con usuarios y sus relaciones creado";
    }
    /*
    *Se demora unos 30 seg en crear todo
    *Fin codigo crear nodos usuarios
    */

    /*
    **Inicio codigo obtener usuarios leaders
    */
    @GET
    @Path("leaders")
    @Produces({"application/xml", "application/json"})
    public Response getLeaders() {
        Neo4jModel n4Leaders = new Neo4jModel();
        GenericEntity<List<Tuitero>> leaders = new GenericEntity<List<Tuitero>>(n4Leaders.getLeaders()) {};
        return Response.ok(leaders).build(); 
    }
    /*
    **Fin codigo obtener usuarios leaders
    */

    /*
    **Inicio codigo obtener usuarios spreaders
    */
    @GET
    @Path("spreaders")
    @Produces({"application/xml", "application/json"})
    public Response getSpreaders() {
        Neo4jModel n4Spreaders = new Neo4jModel();
        GenericEntity<List<Tuitero>> spreaders = new GenericEntity<List<Tuitero>>(n4Spreaders.getSpreaders()) {};
        return Response.ok(spreaders).build(); 
    }
    /*
    **Fin codigo obtener usuarios spreaders
    */

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(User entity) {
        userFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, User entity) {
        entity.setUserId(id.intValue());
        userFacadeEJB.edit(entity);
    }
}