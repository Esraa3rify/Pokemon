package com.example.pokemon.network;

import com.example.pokemon.Model.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;

import retrofit2.http.GET;

public interface PokemonAPIService {

    @GET("pokemon")
    Observable<PokemonResponse> getPokemons();
}
