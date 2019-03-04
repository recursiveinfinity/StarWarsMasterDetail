package com.example.starwarsmasterdetail.home;

import com.example.starwarsmasterdetail.ItemListActivity;
import com.example.starwarsmasterdetail.model.DataSource;
import com.example.starwarsmasterdetail.model.FilmsResponse;
import com.example.starwarsmasterdetail.model.PlanetResponse;
import com.example.starwarsmasterdetail.model.RemoteDataSource;
import com.example.starwarsmasterdetail.model.Result;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeContract.Presenter, DataSource.DataListener {

    private final HomeContract.View view;
    private final DataSource dataSource;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        dataSource = new RemoteDataSource(this);


    }

    @Override
    public void loadAllFilms() {
        dataSource.getAllFilms();
    }

    @Override
    public void onFilmSelected(Result result) {
        String url = result.getPlanets().get(0);
        String planetNumber = String.valueOf(url.charAt(url.length() - 2));
        dataSource.getFilmData(Integer.parseInt(planetNumber));
    }

    @Override
    public void onFilmsRetrieved(FilmsResponse filmsResponse) {
        view.showFilms(filmsResponse.getResults());
    }

    @Override
    public void onError(Throwable throwable) {
        view.showError(throwable.getMessage());
    }

    @Override
    public void onPlanetDetailRetrieved(PlanetResponse planetResponse) {
        view.showPlanetDetails(planetResponse.getName(), planetResponse.getFilms());
    }
}
