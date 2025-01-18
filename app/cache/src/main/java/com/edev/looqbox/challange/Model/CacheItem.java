package com.edev.looqbox.challange.Model;

public class CacheItem {
    private Object object;
    private long timestamp;

    public CacheItem(Object object, long timestamp) {
        this.object = object;
        this.timestamp = timestamp;
    }

    public PokemonDTO getPokemonDTO() {
        return (PokemonDTO) object;
    }
    public PokemonDTOWithHighlight getPokemonHighligthed() {
        return (PokemonDTOWithHighlight) object;
    }

    public long getTimestamp() {
        return timestamp;
    }
}