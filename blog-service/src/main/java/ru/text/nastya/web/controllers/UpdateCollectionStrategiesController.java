package ru.text.nastya.web.controllers;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.text.nastya.dto.mapper.collection.strategy.UpdateCollectionStrategyFactory;

import java.util.Set;

/**
 * Controller for getting update collection strategy types
 */
@RestController
@RequestMapping("/updateCollectionStrategy")
public class UpdateCollectionStrategiesController {

    private final UpdateCollectionStrategyFactory strategyFactory;


    @Autowired
    public UpdateCollectionStrategiesController(UpdateCollectionStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 500, message = "Server Error")})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<String> getAvailableStrategies() {
        return strategyFactory.getAvailableStrategies();
    }

}
