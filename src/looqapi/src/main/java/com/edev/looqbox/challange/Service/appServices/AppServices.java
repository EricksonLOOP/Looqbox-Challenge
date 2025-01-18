package com.edev.looqbox.challange.Service.appServices;

import com.edev.looqbox.challange.Model.Pokemon;
import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Model.PokemonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppServices {
    PokemonDTO createPokemonDto(PokemonResponse response);
    PokemonDTO sort(String sort, PokemonDTO pokemonDTO);
    PokemonDTO filterByQuery(String query, PokemonDTO pokemonDTO);
    List<Pokemon> generateHighlightedResponse(PokemonDTO filteredPokemonDto, String query);
}
