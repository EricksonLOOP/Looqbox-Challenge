package com.edev.looqbox.challange.Service.cacheServices;

import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Model.PokemonDTOWithHighlight;
// O sistema de cache foi criado de forma simples em memória, pois no enunciado dizia não poder usar
// Quaisquers tipos de suporte para criação de cache
public interface CacheServices {
    void  addtoCache(String key, PokemonDTO value);
    PokemonDTO getFromCacheAsPokemonDTO(String key);
    boolean isInCache(String key);

}
