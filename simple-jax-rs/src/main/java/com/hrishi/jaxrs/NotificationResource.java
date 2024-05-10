package com.hrishi.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/notifications")
public class NotificationResource {

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("REST ping is working!").build();
    }
}
