package com.edev.looqbox.challange.Service.cacheServices;

import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Model.PokemonDTOWithHighlight;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheServicesImpl implements CacheServices{
    private final Map<String, Object> inMemorieCache = new HashMap<>();
    @Override
    public void addtoCache(String key, PokemonDTO value) {
        inMemorieCache.put(key, value);

    }

    @Override
    public PokemonDTO getFromCacheAsPokemonDTO(String key) {
        return (PokemonDTO) inMemorieCache.get(key);
    }

    @Override
    public PokemonDTOWithHighlight getFromCacheAsPokemonDTOWithHighligth(String key) {
        return (PokemonDTOWithHighlight) inMemorieCache.get(key);
    }

    @Override
    public boolean isInCache(String key) {
        return inMemorieCache.containsKey(key);
    }
}
