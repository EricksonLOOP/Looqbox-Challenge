package com.edev.looqbox.challange.Controllers;


import com.edev.looqbox.challange.Service.PokemonServices.PokemonServiceImpl;
import com.edev.looqbox.challange.Service.PokemonServices.PokemonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {

    @Autowired
    private final PokemonServices pokemonServices;

    public PokemonController(PokemonServiceImpl pokemonServiceImpl, PokemonServices pokemonServices) {
        this.pokemonServices = pokemonServices;

    }

    @GetMapping("/pokemons")
    public ResponseEntity<?> getPokemons(
            @RequestParam(required = false) String query,
            @RequestParam(required = false, defaultValue = "alphabetical") String sort
    ) {
        return pokemonServices.getPokemons(query, sort.trim());

    }
    @GetMapping("/pokemons/highlight")
    public ResponseEntity<?> getPokemonstHighlighted(
            @RequestParam(required = false) String query,
            @RequestParam(required = false, defaultValue = "alphabetical") String sort
    ){
        return pokemonServices.getPokemonsWithHighligth(query,sort);
    }
}
