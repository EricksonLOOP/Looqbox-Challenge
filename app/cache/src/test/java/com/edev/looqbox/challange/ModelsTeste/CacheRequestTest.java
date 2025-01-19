package com.edev.looqbox.challange.ModelsTeste;

import com.edev.looqbox.challange.Model.CacheRequest;
import com.edev.looqbox.challange.Model.PokemonDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheRequestTest {
    @Mock
    private PokemonDTO pokemonDTO = new PokemonDTO(List.of("Pikachu", "Bulbasaur"));

    @Test
    void createCacheRequest(){
        CacheRequest cacheRequest = new CacheRequest("pi", pokemonDTO);
        assertEquals("pi", cacheRequest.getKey(), "Deve me retornar 'pi'");
        assertEquals(pokemonDTO, cacheRequest.getPokemonDTO(), "Deve ser um obj PokemonDTO");
    }
}
