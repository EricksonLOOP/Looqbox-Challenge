package com.edev.looqbox.challange.Service.consumerService.CacheClient;

import com.edev.looqbox.challange.Model.PokemonDTO;

public interface CacheClient {
    boolean addToCache(String key, PokemonDTO pokemonDTO);
    PokemonDTO getPokemonDto(String key);
    boolean isInCache(String key);
}
