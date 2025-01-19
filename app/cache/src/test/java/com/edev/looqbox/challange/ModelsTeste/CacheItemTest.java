package com.edev.looqbox.challange.ModelsTeste;

import com.edev.looqbox.challange.Model.CacheItem;
import com.edev.looqbox.challange.Model.Pokemon;
import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Model.PokemonResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CacheItemTest {
    @Mock
    private PokemonDTO pokemonDTO = new PokemonDTO(List.of("Pikachu", "Bulbasaur"));
    @Test
    public void CreateCacheItem() {
        CacheItem cacheItem = new CacheItem(pokemonDTO, 10000);

        assertEquals(10000, cacheItem.getTimestamp(), "O timestamp deveria ser 10000.");
        assertEquals(pokemonDTO, (PokemonDTO) cacheItem.getPokemonDTO(), "O objeto armazenado deveria ser 'pi'.");
    }
}
