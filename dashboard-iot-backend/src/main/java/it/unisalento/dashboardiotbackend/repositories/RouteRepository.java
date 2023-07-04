package it.unisalento.dashboardiotbackend.repositories;

import it.unisalento.dashboardiotbackend.domain.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteRepository extends MongoRepository<Route, String> {
}



