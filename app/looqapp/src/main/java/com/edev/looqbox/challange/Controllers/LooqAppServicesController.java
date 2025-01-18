package com.edev.looqbox.challange.Controllers;


import com.edev.looqbox.challange.Model.*;
import com.edev.looqbox.challange.Service.AppServices.AppServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/looqapp")
public class LooqAppServicesController {
    @Autowired
    private final AppServices appServices;

    public LooqAppServicesController(AppServices appServices) {

        this.appServices = appServices;
    }


    @PostMapping("/create/pokemondto")
    public PokemonDTO createPokemonDTO(
            @RequestBody PokemonResponse pokemonResponse
    ) {
        return appServices.createPokemonDto(pokemonResponse);

    }

    @PostMapping("/sort")
    public PokemonDTO sort(
            @RequestBody SortRequest sortRequest
    ) {
        return appServices.sort(sortRequest.getSort(), sortRequest.getPokemonDTO());
    }

    @PostMapping("/filterByQuery")
    public PokemonDTO filterByQuery(
            @RequestBody FilterRequest filterRequest
    ) {
        return appServices.filterByQuery(filterRequest.getQuery(), filterRequest.getPokemonDTO());
    }

    @PostMapping("/create/highlight")
    public List<Pokemon> generateHighlightedResponse(
            @RequestBody GenerateHighlightedResponseRequest generateHighlightedResponseRequest
    ) {
        return appServices.generateHighlightedResponse(generateHighlightedResponseRequest.getPokemonDTO(), generateHighlightedResponseRequest.getQuery());

    }
}
