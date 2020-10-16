package ru.unclediga.saburov.city.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/check")
public class CheckPersonService {
    @GET
    public String checkPerson(){
        return "Test String";
    }

    @GET
    @Path("{id}")
    // localhost:8080/city/rest/check/101?name=SimpleName
    public String checkWithParam(@PathParam("id") int simpleId,
                                 @QueryParam("name") String simpleName) {
        return "params:" + simpleId + ":" + simpleName;
    }
}
