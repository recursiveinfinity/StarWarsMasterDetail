package com.example.starwarsmasterdetail.model;

import com.example.starwarsmasterdetail.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {

    private final DataSource.DataListener listener;
    private final StarWarsService starWarsService;

    public RemoteDataSource(DataSource.DataListener listener) {
        this.listener = listener;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        starWarsService = retrofit.create(StarWarsService.class);

    }


    @Override
    public void getAllFilms() {
        starWarsService.getAllFilms()
                .enqueue(new Callback<FilmsResponse>() {
                    @Override
                    public void onResponse(Call<FilmsResponse> call, Response<FilmsResponse> response) {
                        if (response.isSuccessful()) {
                            listener.onFilmsRetrieved(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmsResponse> call, Throwable t) {
                            listener.onError(t);
                    }
                });
    }

    @Override
    public void getFilmData(int planetNumber) {
        starWarsService.getPlanetDetails(planetNumber)
                .enqueue(new Callback<PlanetResponse>() {
                    @Override
                    public void onResponse(Call<PlanetResponse> call, Response<PlanetResponse> response) {
                        if (response.isSuccessful()) {
                            listener.onPlanetDetailRetrieved(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<PlanetResponse> call, Throwable t) {
                        listener.onError(t);
                    }
                });
    }
}
