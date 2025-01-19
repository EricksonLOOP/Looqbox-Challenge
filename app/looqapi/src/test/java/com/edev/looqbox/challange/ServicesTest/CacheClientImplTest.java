package com.edev.looqbox.challange.ServicesTest;

import com.edev.looqbox.challange.Model.CacheRequest;
import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Service.consumerService.CacheClient.CacheClientImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CacheClientImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CacheClientImpl cacheClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPokemonDto() {
        String key = "pikachu";
        PokemonDTO pokemonDTO = new PokemonDTO(List.of("Pikachu", "Bulbasaur"));

        String url = "http://localhost:8081/cache/get/pokemons?key=" + key;
        ResponseEntity<PokemonDTO> responseEntity = new ResponseEntity<>(pokemonDTO, HttpStatus.OK);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), eq(null), eq(PokemonDTO.class)))
                .thenReturn(responseEntity);

        PokemonDTO pokemonDto = cacheClient.getPokemonDto(key);

        assertNotNull(pokemonDto, "O Pokémon não deve ser nulo");
        assertEquals("Pikachu", pokemonDto.result.get(0), "O nome do Pokémon deveria ser 'Pikachu'");
    }

    @Test
    public void testIsInCacheTrue() {
        String key = "pikachu";
        String url = "http://localhost:8081/cache/verify?key=" + key;
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), eq(null), eq(Boolean.class)))
                .thenReturn(responseEntity);

        boolean result = cacheClient.isInCache(key);

        assertTrue(result, "O Pokémon deveria estar no cache");
    }

    @Test
    public void testIsInCacheFalse() {
        String key = "bulbasaur";
        String url = "http://localhost:8081/cache/verify?key=" + key;
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(false, HttpStatus.OK);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), eq(null), eq(Boolean.class)))
                .thenReturn(responseEntity);

        boolean result = cacheClient.isInCache(key);

        assertFalse(result, "O Pokémon não deveria estar no cache");
    }
}
