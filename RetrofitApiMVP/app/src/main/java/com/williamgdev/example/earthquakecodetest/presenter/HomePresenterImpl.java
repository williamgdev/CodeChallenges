package com.williamgdev.example.earthquakecodetest.presenter;

import com.williamgdev.example.earthquakecodetest.dto.LastDaysResponse;
import com.williamgdev.example.earthquakecodetest.interactor.EarthQuakeInteractor;
import com.williamgdev.example.earthquakecodetest.model.EarthQuake;
import com.williamgdev.example.earthquakecodetest.view.HomeView;

import java.util.List;

public class HomePresenterImpl implements HomePresenter {
    private HomeView view;
    private EarthQuakeInteractor interactor;

    @Override
    public void attachView(HomeView homeView) {
        view = homeView;
        interactor = EarthQuakeInteractor.getInstance();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getLastDays() {
        view.showProgress();
        interactor.getLastDaysOccurrence(new EarthQuakeInteractor.ApiListener<LastDaysResponse>() {
            @Override
            public void onSuccess(LastDaysResponse result) {
                List<EarthQuake> earthQuake = EarthQuake.create(result);
                view.setEarthQuake(earthQuake);
                view.hideProgress();
            }

            @Override
            public void onError(String error) {
                view.showError(error);
                view.hideProgress();
            }
        });
    }
}
