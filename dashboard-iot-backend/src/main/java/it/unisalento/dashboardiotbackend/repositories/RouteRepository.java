package it.unisalento.dashboardiotbackend.repositories;

import it.unisalento.dashboardiotbackend.domain.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RouteRepository extends MongoRepository<Route, String> {
    Optional<Route> findById(String id);
    Optional<Route> findByDateAndStartClock(String date, String startClock);

}



