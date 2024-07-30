package dev.neuralkatana.controllers;

import dev.neuralkatana.models.Room;
import dev.neuralkatana.services.RoomService;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class RoomResource {
    @Inject
    RoomService roomService;

    @Query("allRooms")
    @Description("Get all rooms for the hotel")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }
}
