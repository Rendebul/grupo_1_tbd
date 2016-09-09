package service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
@ApplicationPath("/")
public class HelloWorldService {

    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        return "Hello World";
    }
	
}

