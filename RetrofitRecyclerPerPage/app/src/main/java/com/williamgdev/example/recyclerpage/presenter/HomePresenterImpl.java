package com.williamgdev.example.recyclerpage.presenter;

import com.williamgdev.example.recyclerpage.view.HomeView;
import com.williamgdev.example.recyclerpage.dto.CatResponse;
import com.williamgdev.example.recyclerpage.interactor.CatsInteractor;

import java.util.List;

public class HomePresenterImpl implements HomePresenter {
    private HomeView view;
    private CatsInteractor interactor;
    private int currentPage = 0;

    @Override
    public void attachView(HomeView homeView) {
        view = homeView;
        interactor = CatsInteractor.getInstance();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getCatsFirstPage() {
        view.showProgress();
        interactor.getCatsPage(currentPage = 0, apiListener);
    }

    @Override
    public void getCatsNextPage() {
        view.showProgress();
        interactor.getCatsPage(currentPage++, apiListener);
    }

    private CatsInteractor.ApiListener<List<CatResponse>> apiListener = new CatsInteractor.ApiListener<List<CatResponse>>() {

        @Override
        public void onSuccess(List<CatResponse> result) {
            view.setCats(result);
            view.hideProgress();
        }

        @Override
        public void onError(String error) {
            view.showError(error);
            view.hideProgress();
        }
    };
}
