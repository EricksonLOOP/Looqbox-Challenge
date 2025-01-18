package com.edev.looqbox.challange.Service.cacheServices;

import com.edev.looqbox.challange.Model.CacheItem;
import com.edev.looqbox.challange.Model.PokemonDTO;

import com.edev.looqbox.challange.Model.PokemonDTOWithHighlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheServicesImpl implements CacheServices{

    private final Map<String, CacheItem> cache = new HashMap<>();

    @Override
    public void addtoCache(String key, PokemonDTO value) {
        cache.put(key, new CacheItem(value, System.currentTimeMillis()));
    }

    @Override
    public PokemonDTO getFromCacheAsPokemonDTO(String key) {
        CacheItem cacheItem = cache.get(key);
        if (cacheItem != null && isExpired(cacheItem)) {
            return cacheItem.getPokemonDTO();
        }
        return null; // Retorna null se o item estiver expirado ou não existir
    }

    private boolean isExpired(CacheItem cacheItem) {
        long cacheExpiryTime = 600000;
        return (System.currentTimeMillis() - cacheItem.getTimestamp()) <= cacheExpiryTime;
    }
    @Override
    public boolean isInCache(String key) {
        return getFromCacheAsPokemonDTO(key) != null;
    }


}
