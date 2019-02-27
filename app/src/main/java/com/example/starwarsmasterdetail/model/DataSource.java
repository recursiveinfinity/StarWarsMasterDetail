package com.example.starwarsmasterdetail.model;

public interface DataSource {
    void getAllFilms();
    void getFilmData(int planetNumber);


    interface DataListener {
        void onFilmsRetrieved(FilmsResponse filmsResponse);
        void onError(Throwable throwable);
        void onPlanetDetailRetrieved(PlanetResponse planetResponse);
    }
}
