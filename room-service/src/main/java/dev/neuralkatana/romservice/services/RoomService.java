package dev.neuralkatana.romservice.services;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import dev.neuralkatana.romservice.models.Room;
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

    @ConfigProperty(name="application.query.room.by.info")
    private String getRoomsByBedInfCql;

    @ConfigProperty(name="application.query.room.by.roomnumber")
    private String getRoomByNumberCql;

    @ConfigProperty(name="application.query.room.add")
    private String addRoomsCql;

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

    public List<Room> getRoomsByBedInfo(String bedInfo){
        List<Room> rooms = new ArrayList<>();
        PreparedStatement ps = cqlSession.prepare(getRoomsByBedInfCql);
        BoundStatement bs = ps.bind(bedInfo);
        ResultSet rs = cqlSession.execute(bs);
        for(Row row : rs){
            rooms.add(new Room(row.getString("room_number"),
                    row.getString("bed_info"),
                    row.getString("name")));
        }
        return rooms;
    }

    public Room getRoomsByRoomNumber(String roomNumber){
        Room room = null;
        PreparedStatement ps = cqlSession.prepare(getRoomByNumberCql);
        BoundStatement bs = ps.bind(roomNumber);
        ResultSet rs = cqlSession.execute(bs);
        for(Row row : rs){
            room = new Room(row.getString("room_number"),
                    row.getString("bed_info"),
                    row.getString("name"));
        }
        return room;
    }

    public Room addRoom(Room room){
        if(room == null){
            return null;
        }
        PreparedStatement ps = cqlSession.prepare(addRoomsCql);
        BoundStatement bs = ps.bind(room.getRoom(),room.getBedInfo(),room.getName());
        ResultSet rs = cqlSession.execute(bs);
        for(Row row : rs){
            System.out.println(row);
        }
        return room;
    }

}
