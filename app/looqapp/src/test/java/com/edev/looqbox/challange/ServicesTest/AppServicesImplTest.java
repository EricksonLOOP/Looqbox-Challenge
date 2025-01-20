package com.edev.looqbox.challange.ServicesTest;

import com.edev.looqbox.challange.Model.Pokemon;
import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Model.PokemonResponse;
import com.edev.looqbox.challange.Service.AppServices.AppServicesImpl;
import com.edev.looqbox.challange.Service.SorterServices.SorterServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AppServicesImplTest {

    @Mock
    private SorterServices sorterServices;
    @Autowired
    private AppServicesImpl appServices;

    @Test
    public void testCreatePokemonDto() {

        PokemonResponse response = new PokemonResponse(1000, "http://next.url", "http://previous.url", List.of(new Pokemon("Pikachu", "http://pokemon.url"), new Pokemon("Charmander", "http://pokemon.url")));
        PokemonDTO result = appServices.createPokemonDto(response);

        assertEquals(2, result.getResult().size());
        assertTrue(result.getResult().contains("Pikachu"));
        assertTrue(result.getResult().contains("Charmander"));
    }

    @Test
    public void testSortByLength() {
        List<String> pokemons = List.of("Pikachu", "Charmander");
        when(sorterServices.sortbByLength(pokemons)).thenReturn(List.of("Pikachu", "Charmander"));
        PokemonDTO result = appServices.sort("length", new PokemonDTO(pokemons));

        assertEquals(2, result.getResult().size());
        assertEquals("Pikachu", result.getResult().get(0));
    }


    @Test
    public void testSortByName() {

        List<String> pokemons = List.of("Charmander", "Pikachu");
        when(sorterServices.sortbByName(pokemons)).thenReturn(List.of("Charmander", "Pikachu"));


        PokemonDTO result = appServices.sort("alphabetical", new PokemonDTO(pokemons));

        assertEquals(2, result.getResult().size());
        assertEquals("Charmander", result.getResult().get(0));
    }

    @Test
    public void testFilterByQuery() {

        List<String> pokemons = List.of("Pikachu", "Charmander", "Bulbasaur");
        PokemonDTO pokemonDTO = new PokemonDTO(pokemons);

        PokemonDTO result = appServices.filterByQuery("pi", pokemonDTO);

        assertEquals(1, result.getResult().size());
        assertEquals("Pikachu", result.getResult().get(0));
    }

    @Test
    public void testGenerateHighlightedResponse() {

        List<String> pokemons = List.of("Pikachu", "Charmander");
        PokemonDTO pokemonDTO = new PokemonDTO(pokemons);

        List<Pokemon> result = appServices.generateHighlightedResponse(pokemonDTO, "pi");

        assertEquals(2, result.size());
        assertTrue(result.get(0).getHighlight().contains("<pre>Pi</pre>"));
    }
}
