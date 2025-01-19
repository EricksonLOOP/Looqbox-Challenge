package com.edev.looqbox.challange.CacheServicesTest;

import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Service.cacheServices.CacheServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CacheServiceImplTest {

    private CacheServicesImpl cacheServices;

    @BeforeEach
    public void setUp() {
        cacheServices = new CacheServicesImpl();
    }

    @Test
    public void testAddToCache() {
        PokemonDTO pokemon = new PokemonDTO(List.of("Pikachu", "Bulbasaur"));
        String key = "pikachu";

        cacheServices.addtoCache(key, pokemon);
        assertTrue(cacheServices.isInCache(key), "O item deveria estar no cache.");
    }

    @Test
    public void testGetFromCacheBeforeExpiry() {
        PokemonDTO pokemon = new PokemonDTO(List.of("Pikachu", "Bulbasaur"));
        String key = "pikachu";

        cacheServices.addtoCache(key, pokemon);

        PokemonDTO cachedPokemon = cacheServices.getFromCacheAsPokemonDTO(key);
        assertNotNull(cachedPokemon, "O item deveria ser retornado.");
        assertEquals(List.of("Pikachu", "Bulbasaur"), cachedPokemon.result, "O nome do Pokémon deveria ser Pikachu.");
    }


    @Test
    public void testIsInCacheWhenItemDoesNotExist() {
        String key = "charizard";
        assertFalse(cacheServices.isInCache(key), "O item não deveria estar no cache.");
    }

}
