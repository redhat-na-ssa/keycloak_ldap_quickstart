package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import io.smallrye.mutiny.Uni;

@RegisterRestClient(configKey="iBackend")
@RegisterClientHeaders
@Path("/")
public interface IBackend {

    @GET
    @Path("/unsecured/sanityCheck")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Response> sanityCheck();

    @GET
    @Path("/secured/authNonly")
    @Produces(MediaType.TEXT_PLAIN)
    Uni<Response> authNonly();

    @GET
    @Path("/secured/roles-required")
    @Produces(MediaType.TEXT_PLAIN)
    Uni<Response> rolesRequired();

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        throw new RuntimeException("Remote Exception status: "+response.getStatus());
 
    }
    
}