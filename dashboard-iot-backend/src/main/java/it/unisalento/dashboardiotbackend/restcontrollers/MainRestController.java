package it.unisalento.dashboardiotbackend.restcontrollers;

import it.unisalento.dashboardiotbackend.domain.Anomaly;
import it.unisalento.dashboardiotbackend.domain.Route;
import it.unisalento.dashboardiotbackend.dto.AddAnomalyDTO;
import it.unisalento.dashboardiotbackend.dto.ApiResponseDTO;
import it.unisalento.dashboardiotbackend.dto.RouteDTO;
import it.unisalento.dashboardiotbackend.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MainRestController {

    @Autowired
    RouteRepository routeRepository;

    @RequestMapping(value="/getRoutes", method= RequestMethod.GET)
    public List<RouteDTO> getAllRoutes() {
        List<RouteDTO> routeDTOS = new ArrayList<>();
        for (Route route : routeRepository.findAll()) {
            RouteDTO tempRouteDTO = new RouteDTO();
            tempRouteDTO.setAnomalies(route.getAnomalies());
            tempRouteDTO.setDate(route.getDate());
            tempRouteDTO.setStartClock(route.getStartClock());
            tempRouteDTO.setEndClock(route.getEndClock());
            tempRouteDTO.setDescription(route.getDescription());
            tempRouteDTO.setId(route.getId());
            routeDTOS.add(tempRouteDTO);

        }
            return routeDTOS;
    }

    @RequestMapping(value="/addRoute", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseDTO addRoute(@RequestBody RouteDTO routeDTO) {

        ApiResponseDTO responseDTO = new ApiResponseDTO();

        Route route = new Route();
        route.setAnomalies(routeDTO.getAnomalies());
        route.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        route.setStartClock(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        route.setEndClock(routeDTO.getEndClock());
        route.setDescription(routeDTO.getDescription());
        routeRepository.save(route);

        responseDTO.setResponse(routeRepository.findByDateAndStartClock(route.getDate(), route.getStartClock()).get().getId().toString());
        return responseDTO;
    }

    @RequestMapping(value="/endRoute", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseDTO endRoute(@RequestBody RouteDTO routeDTO) {

        ApiResponseDTO responseDTO = new ApiResponseDTO();
        Optional<Route> route = routeRepository.findById(routeDTO.getId());

        if (route.isEmpty()){
            responseDTO.setResponse("Questa route non esiste");
            return responseDTO;
        }

        route.get().setEndClock(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        routeRepository.save(route.get());

        responseDTO.setResponse("ok");
        return responseDTO;
    }

    @RequestMapping(value="/addAnomaly", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseDTO addAnomaly(@RequestBody AddAnomalyDTO newAnomaly) {

        ApiResponseDTO responseDTO = new ApiResponseDTO();
        Optional<Route> routeOptional = routeRepository.findById(newAnomaly.getRouteId());

        if (routeOptional.isEmpty()){
            responseDTO.setResponse("Questa route non esiste");
            return responseDTO;
        }

        Route route = routeOptional.get();
        List<Anomaly> anomalies = route.getAnomalies();

        if (anomalies == null){
            responseDTO.setResponse("La lista di anomalie è nulla");
            return responseDTO;
        }

        boolean anomalyExisting = false;

        for (Anomaly anomaly : anomalies) {
            if (anomaly.getType().equals(newAnomaly.getType())) {
                anomaly.addValue(newAnomaly.getValue(), newAnomaly.getTime());
                anomalyExisting = true;
                break; // Exit the loop once the anomaly is found
            }
        }

        if (!anomalyExisting){
            responseDTO.setResponse("Questa anomalia non esiste");
            return responseDTO;
        }

        routeRepository.save(route);
        responseDTO.setResponse("ok");
        return responseDTO;
    }



}
