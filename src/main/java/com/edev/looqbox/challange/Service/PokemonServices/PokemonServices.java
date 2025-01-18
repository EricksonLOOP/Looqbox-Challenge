package com.edev.looqbox.challange.Service.PokemonServices;

import org.springframework.http.ResponseEntity;

public interface PokemonServices {
    ResponseEntity<?> getPokemons(String query, String sort);

    ResponseEntity<?> getPokemonsWithHighligth(String query, String sort);
}
