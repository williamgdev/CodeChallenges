package com.williamgdev.example.earthquakecodetest.presenter;

import com.williamgdev.example.earthquakecodetest.view.HomeView;

public interface HomePresenter extends BasePresenter<HomeView> {
    void getLastDays();
}
