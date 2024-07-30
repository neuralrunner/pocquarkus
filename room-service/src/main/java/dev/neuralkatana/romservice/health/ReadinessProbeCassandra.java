package dev.neuralkatana.romservice.health;

import dev.neuralkatana.romservice.services.RoomService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ReadinessProbeCassandra implements HealthCheck {

    @Inject
    RoomService roomService;

    @Override
    public HealthCheckResponse call(){
        return HealthCheckResponse.named("Table Room(Cassandra Readiness Check)")
                .up()
                .withData("roomCount",roomService.getAllRooms().size())
                .build();
    }
}
