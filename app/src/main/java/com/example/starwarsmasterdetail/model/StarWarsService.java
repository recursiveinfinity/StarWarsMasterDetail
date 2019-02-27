package com.example.starwarsmasterdetail.model;

import com.example.starwarsmasterdetail.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StarWarsService {

    @GET(Constants.FILMS_ENDPOINT)
    Call<FilmsResponse> getAllFilms();


    @GET(Constants.PLANET_ENDPOINT)
    Call<PlanetResponse> getPlanetDetails(@Path("planetNumber")int planetNumber);
}
