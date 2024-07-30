package dev.neuralkatana.roombatch.runner;

import dev.neuralkatana.roombatch.clients.RoomServiceRest;
import dev.neuralkatana.roombatch.models.Room;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@QuarkusMain
public class MainRunner implements QuarkusApplication {

    @Inject
    @RestClient
    RoomServiceRest roomServiceRest;

    @Override
    public int run(String... args) {
        List<Room> rooms = roomServiceRest.getAllRooms();
        rooms.forEach(System.out::println);
        return 0;
    }

}
