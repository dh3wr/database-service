package de.hampager.dapnet.service.database;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Singleton
@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource {

	@Inject
	private UserClient client;

	@GET
	public Response getAll() {
		try {
			JsonObject obj = client.getAll();
			return Response.ok(obj).build();
		} catch (Exception ex) {
			return Response.serverError().status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@GET
	@Path("{username}")
	public Response get(@PathParam("username") String username) {
		try {
			JsonObject obj = client.get(username);
			if (obj != null) {
				return Response.ok(obj).build();
			} else {
				return Response.status(Status.NOT_FOUND).build();
			}
		} catch (Exception ex) {
			return Response.serverError().status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

}
