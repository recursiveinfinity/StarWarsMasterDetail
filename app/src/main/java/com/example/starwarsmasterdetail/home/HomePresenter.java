package com.example.starwarsmasterdetail.home;

import com.example.starwarsmasterdetail.model.DataSource;
import com.example.starwarsmasterdetail.model.FilmsResponse;
import com.example.starwarsmasterdetail.model.PlanetResponse;
import com.example.starwarsmasterdetail.model.RemoteDataSource;

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
    public void onPlanetSelected() {

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

    }
}
