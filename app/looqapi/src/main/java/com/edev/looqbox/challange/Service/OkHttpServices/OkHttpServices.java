package com.edev.looqbox.challange.Service.OkHttpServices;

import com.edev.looqbox.challange.Model.PokemonResponse;

public interface OkHttpServices {
    PokemonResponse getPokemons(String url);
}
