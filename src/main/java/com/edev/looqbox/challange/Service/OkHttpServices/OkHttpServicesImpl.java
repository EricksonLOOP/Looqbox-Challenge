package com.edev.looqbox.challange.Service.OkHttpServices;

import com.edev.looqbox.challange.Model.Pokemon;
import com.edev.looqbox.challange.Model.PokemonResponse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OkHttpServicesImpl implements OkHttpServices{
    private final OkHttpClient client = new OkHttpClient();
    // Esta classe vai uma chamada HTTP para a URL do PokéAPI e nos retorna TODOS os pokémons de uma só vez
    // E é automáticamente registrado ao "noquery" key cache no inMemorieCache para que haja menos chamadas HTTP
    // Isso poupou 1.2seg nas requisições
    @Override
    public PokemonResponse getPokemons(String url) {
        try {
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();
            Response res = client.newCall(request).execute();
            if (res.isSuccessful()) {
                return createPokemonResponse(res);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }
    // Aqui é criado um objeto do tipo PokemonResponse
    private PokemonResponse createPokemonResponse(Response res) {
        try {
            String responseBody = res.body().string();

            JSONObject resBodytoJSONObject = new JSONObject(responseBody);

            List<Pokemon> pokemons = new ArrayList<>();


            JSONArray results = resBodytoJSONObject.optJSONArray("results");
            if (results != null) {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject myResult = results.getJSONObject(i);


                    Pokemon pokemon = new Pokemon(myResult.optString("name"), myResult.optString("url"));
                    pokemons.add(pokemon);
                }
            }

            return new PokemonResponse(
                    resBodytoJSONObject.optInt("count"),
                    resBodytoJSONObject.optString("next"),
                    resBodytoJSONObject.optString("previous"),
                    pokemons
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
