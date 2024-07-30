package dev.neuralkatana.romservice.controllers;

import dev.neuralkatana.romservice.models.Room;
import dev.neuralkatana.romservice.services.RoomService;
import io.quarkus.runtime.util.StringUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/rooms")
public class RoomResource {

    @Inject
    RoomService roomService;

    @Inject
    MeterRegistry meterRegistry;

    private final static Logger LOG = Logger.getLogger(RoomResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(@QueryParam("bedInfo") String bedInfo){

        //Needed for the Metrics using Micrometer
        Timer timer = Timer
                .builder("roomService")
                .tag("method", "getAllRooms")
                .register(meterRegistry);
        long start = System.nanoTime();

        LOG.info("RoomService - getAllRooms - start");
        if(!StringUtil.isNullOrEmpty(bedInfo)){
            return roomService.getRoomsByBedInfo(bedInfo);
        }
        LOG.info("RoomService - getAllRooms - ending");
        timer.record(System.nanoTime()-start, TimeUnit.NANOSECONDS);

        return roomService.getAllRooms();
    }

    @GET
    @Path("/{roomNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoom(@PathParam("roomNumber")String roomNumber){
        LOG.info("RoomService - getRoom");
        return roomService.getRoomsByRoomNumber(roomNumber);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Room createRoom(@RequestBody Room room){
        LOG.info("RoomService - createRoom");
        return roomService.addRoom(room);
    }
}
