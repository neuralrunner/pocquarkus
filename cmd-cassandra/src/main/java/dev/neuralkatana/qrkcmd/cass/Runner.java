package dev.neuralkatana.qrkcmd.cass;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;

@QuarkusMain
public class Runner implements QuarkusApplication {

    private static final Logger LOG = Logger.getLogger(Runner.class);

    @ConfigProperty(name="application.bed-info")
    private String bedInfo;

    @Inject
    QuarkusCqlSession cqlSession;

    @Override
    public int run(String... args) throws Exception {
        LOG.info("//// STARTING COMMANDLINE RUNNER ////");

        runRoomQuery();
        runBedInfoQuery();

        LOG.info("//// ENDING COMMANDLINE RUNNER ////");
        return 0;
    }

    private void runBedInfoQuery(){
        LOG.info("// RUNNING QUERY FOR ROOMS WHERE BED_INFO IS EQUAL TO:"+bedInfo+" //");
        String cql = "select room_number, bed_info, name from lil.rooms where bed_info=?";

        List<Room> rooms = new ArrayList<>();

        PreparedStatement ps = cqlSession.prepare(cql);
        BoundStatement bs = ps.bind(bedInfo);
        ResultSet rs = cqlSession.execute(bs);

        for(Row row : rs){
            rooms.add(new Room(row.getString("room_number"),
                    row.getString("bed_info"),
                    row.getString("name")));
        }

        rooms.forEach(System.out::println);
        LOG.info("// ... END RUN //");
    }

    private void runRoomQuery(){
        LOG.info("// RUNNING... QUERY FOR ALL ROOMS //");
        String cql = "select room_number, bed_info, name from lil.rooms";

        List<Room> rooms = new ArrayList<>();

        ResultSet rs = cqlSession.execute(cql);

        for(Row row : rs){
            rooms.add(new Room(row.getString("room_number"),
                    row.getString("bed_info"),
                    row.getString("name")));
        }

        rooms.forEach(System.out::println);
        LOG.info("// ... END RUN //");
    }
}
