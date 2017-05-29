/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075;

import ee.ttu.idu0075._2015.ws.theatre.AddTheatreRequest;
import ee.ttu.idu0075._2015.ws.theatre.GetShowListRequest;
import ee.ttu.idu0075._2015.ws.theatre.GetShowListResponse;
import ee.ttu.idu0075._2015.ws.theatre.GetTheatreRequest;
import ee.ttu.idu0075._2015.ws.theatre.TheatreType;
import java.math.BigInteger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author sigri
 */
@Path("theatres")
public class TheatresResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TheatresResource
     */
    public TheatresResource() {
    }

    @GET
    @Produces("application/json")
    public GetShowListResponse getProductList(@QueryParam("token") String token) {
        ShowsTheatresWS sts = new ShowsTheatresWS();
        GetShowListRequest request = new GetShowListRequest();
        request.setToken(token);
        return sts.getShowList(request);
    }
    
    @GET
    @Path("{id : \\d+}") //supports digits only
    @Produces("application/json")
    public TheatreType getTheatre(@PathParam("id") String id,
            @QueryParam("token") String token) {
        ShowsTheatresWS sts = new ShowsTheatresWS();
        GetTheatreRequest request = new GetTheatreRequest();
        request.setId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return sts.getTheatre(request);
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public TheatreType addTheatre(TheatreType content, 
                                @QueryParam("token") String token) {
        ShowsTheatresWS sts = new ShowsTheatresWS();
        AddTheatreRequest request = new AddTheatreRequest();
        request.setName(content.getName());
        request.setToken(token);
        return sts.addTheatre(request);
    }
}
