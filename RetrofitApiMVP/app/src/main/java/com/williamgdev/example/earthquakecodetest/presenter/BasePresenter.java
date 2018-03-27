package com.williamgdev.example.earthquakecodetest.presenter;

import com.williamgdev.example.earthquakecodetest.view.BaseView;

public interface BasePresenter <V extends BaseView>{
    void attachView(V v);
    void detachView();
}
