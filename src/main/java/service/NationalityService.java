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

import facade.NationalityFacade;
import model.Nationality;

@Path("/nacionalidades")
public class NationalityService {
    
    @EJB 
    NationalityFacade nationalityFacadeEJB;

    Logger logger = Logger.getLogger(NationalityService.class.getName());
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Nationality> findAll(){
        return nationalityFacadeEJB.findAll();
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Nationality find(@PathParam("id") Integer id) {
        return nationalityFacadeEJB.find(id);
    }
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Nationality entity) {
        nationalityFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Nationality entity) {
        entity.setNationalityId(id.intValue());
        nationalityFacadeEJB.edit(entity);
    }
}