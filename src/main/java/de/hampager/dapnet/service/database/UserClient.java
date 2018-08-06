package de.hampager.dapnet.service.database;

import java.io.IOException;

import javax.json.JsonObject;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.configuration2.ImmutableConfiguration;

final class UserClient extends RestClient {

	private final WebTarget resourceTarget;

	public UserClient(ImmutableConfiguration config) {
		super(config);

		resourceTarget = rootTarget.path("users");
	}

	public JsonObject getAll() throws IOException {
		Response r = resourceTarget.path("_all_docs").queryParam("include_docs", "true")
				.request(MediaType.APPLICATION_JSON_TYPE).get();
		return r.readEntity(JsonObject.class);
	}

	public JsonObject get(String username) throws IOException {
		Response r = resourceTarget.path(username).request(MediaType.APPLICATION_JSON_TYPE).get();
		return r.readEntity(JsonObject.class);
	}

}
