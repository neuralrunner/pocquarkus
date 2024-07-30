package dev.neuralkatana.services;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import dev.neuralkatana.models.Room;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RoomService {
    @Inject
    CqlSession cqlSession;

    @ConfigProperty(name="application.query.room.all")
    private String getAllCql;

    public List<Room> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        ResultSet rs = cqlSession.execute(getAllCql);
        for(Row row : rs){
            rooms.add(new Room(row.getString("room_number"),
                    row.getString("bed_info"),
                    row.getString("name")));
        }
        return rooms;
    }

}
