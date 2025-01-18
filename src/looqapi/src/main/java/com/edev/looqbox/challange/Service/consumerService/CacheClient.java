package com.edev.looqbox.challange.Service.consumerService;

import com.edev.looqbox.challange.Model.CacheRequest;
import com.edev.looqbox.challange.Model.PokemonDTO;
import org.springframework.http.ResponseEntity;

public interface CacheClient {
    boolean addToCache(String key, PokemonDTO pokemonDTO);
    PokemonDTO getPokemonDto(String key);
    boolean isInCache(String key);
}
