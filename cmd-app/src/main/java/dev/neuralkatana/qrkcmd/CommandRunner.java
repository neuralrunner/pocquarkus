package dev.neuralkatana.qrkcmd;

import io.agroal.pool.DataSource;

import io.quarkus.arc.Arc;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.persistence.EntityManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@QuarkusMain
public class CommandRunner implements QuarkusApplication {
    private static final Logger LOG = Logger.getLogger(CommandRunner.class);

    @ConfigProperty(defaultValue = "Studies", name="application.greeting.recipient")
    String recipient;

    @ConfigProperty(name="application.bed-info")
    private String bedInfo;

    @Inject
    DataSource datasource;

    @Inject
    EntityManager entityManager;

    @Override
    public int run(String... args) throws Exception {
        LOG.info("//// STARTING COMMANDLINE RUNNER ////");

        runQueryAllRoomsWithJDBC();
        runQueryAllRoomsWithHibernate();
        runQueryBedInfoWithHibernate();

        LOG.info("//// ENDING COMMANDLINE RUNNER ////");
        return 0;
    }

    //In order to use the Postgres Methods, first use the Scripts on the bin folder
    //You will need docker installed on your machine in order to run these
    public void runQueryAllRoomsWithHibernate(){
        LOG.info("// RUNNING... QUERY FOR ALL ROOMS - POWERED BY HIBERNATE //");
        Arc.container().requestContext().activate();
        List<Room> rooms = entityManager.createQuery("select r from Room r", Room.class).getResultList();
        rooms.forEach(System.out::println);
        Arc.container().requestContext().deactivate();
        LOG.info("// ... END RUN //");
    }

    public void runQueryBedInfoWithHibernate(){
        LOG.info("// RUNNING QUERY FOR ROOMS WHERE BED_INFO IS EQUAL TO:"+bedInfo+" // - POWERED BY HIBERNATE");
        Arc.container().requestContext().activate();
        List<Room> rooms = entityManager.createQuery("select r from Room r where r.bedInfo=:bedInfo", Room.class)
                .setParameter("bedInfo",bedInfo)
                .getResultList();
        rooms.forEach(System.out::println);
        Arc.container().requestContext().deactivate();
        LOG.info("// ... END RUN //");
    }

    public void runQueryAllRoomsWithJDBC(){
        LOG.info("// RUNNING... QUERY FOR ALL ROOMS - POWERED BY JDBC //");
        String sql = "SELECT NAME, ROOM_NUMBER, BED_INFO FROM ROOM";
        List<Room> rooms = new ArrayList<>();
        try{
            Connection connection = datasource.getConnection();
            try(Statement stmt = connection.createStatement()){
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    rooms.add(new Room(rs.getString("NAME"), rs.getString("ROOM_NUMBER"), rs.getString("BED_INFO")));
                }
            }finally {
                connection.close();
            }
            rooms.forEach(System.out::println);
            LOG.info("// ... END RUN //");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
