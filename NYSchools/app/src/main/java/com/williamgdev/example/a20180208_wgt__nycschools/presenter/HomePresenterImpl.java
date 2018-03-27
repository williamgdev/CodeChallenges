package com.williamgdev.example.a20180208_wgt__nycschools.presenter;

import com.williamgdev.example.a20180208_wgt__nycschools.interactor.OpenDataInteractor;
import com.williamgdev.example.a20180208_wgt__nycschools.model.School;
import com.williamgdev.example.a20180208_wgt__nycschools.view.HomeView;

import java.util.List;

public class HomePresenterImpl implements HomePresenter {
    private HomeView view;
    private OpenDataInteractor openDataInteractor;

    @Override
    public void attachView(HomeView homeView) {
        view = homeView;
        openDataInteractor = OpenDataInteractor.getInstance();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getSchools() {
        view.showProgress();
        openDataInteractor.getSchools(new OpenDataInteractor.ApiListener<List<School>>() {
            @Override
            public void onSuccess(List<School> result) {
                view.setSchools(result);
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
