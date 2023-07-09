package it.unisalento.dashboardiotbackend.restcontrollers;

import it.unisalento.dashboardiotbackend.domain.Route;
import it.unisalento.dashboardiotbackend.dto.ApiResponseDTO;
import it.unisalento.dashboardiotbackend.dto.RouteDTO;
import it.unisalento.dashboardiotbackend.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
            tempRouteDTO.setDateEnd(route.getDateEnd());
            tempRouteDTO.setDateStart(route.getDateStart());
            tempRouteDTO.setDescription(route.getDescription());
            routeDTOS.add(tempRouteDTO);
        }
            return routeDTOS;
    }

    @RequestMapping(value="/addRoute", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseDTO addRoute(@RequestBody RouteDTO routeDTO) {

        ApiResponseDTO responseDTO = new ApiResponseDTO();

        Route route = new Route();
        route.setAnomalies(routeDTO.getAnomalies());
        route.setDateStart(routeDTO.getDateStart());
        route.setDateEnd(routeDTO.getDateEnd());
        route.setDescription(routeDTO.getDescription());
        routeRepository.save(route);

        responseDTO.setResponse("ok");
        return responseDTO;
    }
}
