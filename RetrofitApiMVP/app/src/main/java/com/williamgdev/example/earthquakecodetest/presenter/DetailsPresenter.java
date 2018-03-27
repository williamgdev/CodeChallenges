package com.williamgdev.example.earthquakecodetest.presenter;

import com.williamgdev.example.earthquakecodetest.view.DetailsView;

import java.io.Serializable;

public interface DetailsPresenter extends BasePresenter<DetailsView> {
    void getData(Serializable earthQuake);
}
