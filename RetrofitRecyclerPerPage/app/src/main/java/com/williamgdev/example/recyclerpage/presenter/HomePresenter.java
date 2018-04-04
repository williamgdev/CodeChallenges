package com.williamgdev.example.recyclerpage.presenter;


import com.williamgdev.example.recyclerpage.view.HomeView;

public interface HomePresenter extends BasePresenter<HomeView> {
    void getCatsFirstPage();

    void getCatsNextPage();
}
