package com.edev.looqbox.challange.ServicesTest;

import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Service.OkHttpServices.OkHttpServices;
import com.edev.looqbox.challange.Service.PokemonServices.PokemonServiceImpl;
import com.edev.looqbox.challange.Service.consumerService.LooqAppClient.LooqAppClient;
import com.edev.looqbox.challange.Service.consumerService.CacheClient.CacheClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PokemonServiceImplTest {

    @Mock
    private OkHttpServices okHttpServices;

    @Mock
    private LooqAppClient appClient;

    @Mock
    private CacheClient cacheClient;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPokemonsWithCache() {
        String query = "pikachu";
        String sort = "alphabetical";
        PokemonDTO cachedPokemon = new PokemonDTO(List.of("Pikachu", "Bulbasaur"));

        when(cacheClient.isInCache(query.toLowerCase())).thenReturn(true);
        when(cacheClient.getPokemonDto(query.toLowerCase())).thenReturn(cachedPokemon);
        when(appClient.sort(sort, cachedPokemon)).thenReturn(cachedPokemon);

        ResponseEntity<?> response = pokemonService.getPokemons(query, sort);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status HTTP deve ser OK");
        verify(cacheClient, times(1)).isInCache(query.toLowerCase());
        verify(appClient, times(1)).sort(sort, cachedPokemon);
    }

    @Test
    public void testGetPokemonsWithoutCache() {

        String query = "pikachu";
        String sort = "alphabetical";
        PokemonDTO expectedPokemon = new PokemonDTO(List.of("Pikachu", "Bulbasaur"));

        when(cacheClient.isInCache(query.toLowerCase())).thenReturn(false);
        when(cacheClient.isInCache("noquery")).thenReturn(true);
        when(cacheClient.getPokemonDto("noquery")).thenReturn(expectedPokemon);
        when(appClient.filterByQuery(query, expectedPokemon)).thenReturn(expectedPokemon);
        when(appClient.sort(sort, expectedPokemon)).thenReturn(expectedPokemon);


        ResponseEntity<?> response = pokemonService.getPokemons(query, sort);


        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status HTTP deve ser OK");
        verify(cacheClient, times(1)).isInCache(query.toLowerCase());
        verify(appClient, times(1)).filterByQuery(eq(query), eq(expectedPokemon));
        verify(appClient, times(1)).sort(eq(sort), eq(expectedPokemon));
    }


    @Test
    public void testGetPokemonsWithNullQuery() {
        String query = null;
        String sort = "alphabetical";
        PokemonDTO expectedPokemon = new PokemonDTO(List.of("Pikachu", "Bulbasaur"));

        when(cacheClient.isInCache("noquery")).thenReturn(true);
        when(cacheClient.getPokemonDto("noquery")).thenReturn(expectedPokemon);
        when(appClient.sort(eq(sort), eq(expectedPokemon))).thenReturn(expectedPokemon);

        ResponseEntity<?> response = pokemonService.getPokemons(query, sort);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status HTTP deve ser OK");
        verify(cacheClient, times(1)).isInCache("noquery");
        verify(appClient, times(1)).sort(eq(sort), eq(expectedPokemon));
    }


    @Test
    public void testGetPokemonsThrowsException() {

        String query = "pikachu";
        String sort = "alphabetical";
        when(cacheClient.isInCache(query.toLowerCase())).thenThrow(new RuntimeException("Test exception"));

        ResponseEntity<?> response = pokemonService.getPokemons(query, sort);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status HTTP deve ser INTERNAL_SERVER_ERROR");
        assertTrue(response.getBody().toString().contains("Error fetching pokemons"), "A resposta deve conter uma mensagem de erro");
    }
}
