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

import facade.UserFacade;
import model.User;

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