package com.example.starwarsmasterdetail.home;

import com.example.starwarsmasterdetail.model.Result;

import java.util.List;

public interface HomeContract {
    interface Presenter {
        void loadAllFilms();
        void onFilmSelected(Result result);
    }

    interface View {
        void showFilms(List<Result> films);
        void showPlanetDetails(String name, List<String> films);
        void showError(String message);
    }
}
