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

import facade.RolFacade;
import model.Rol;

@Path("/roles")
public class RolService {
    
    @EJB 
    RolFacade rolFacadeEJB;

    Logger logger = Logger.getLogger(RolService.class.getName());
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Rol> findAll(){
        return rolFacadeEJB.findAll();
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Rol find(@PathParam("id") Integer id) {
        return rolFacadeEJB.find(id);
    }
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Rol entity) {
        rolFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Rol entity) {
        entity.setRolId(id.intValue());
        rolFacadeEJB.edit(entity);
    }
}