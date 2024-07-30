package dev.neuralkatana.roombatch.clients;

import dev.neuralkatana.roombatch.models.Room;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/rooms")
@ApplicationScoped
@RegisterRestClient(configKey="rooms-api")
public interface RoomServiceRest {

    @GET
    List<Room> getAllRooms();
}
