package com.williamgdev.example.recyclerpage.presenter;


import com.williamgdev.example.recyclerpage.view.BaseView;

public interface BasePresenter <V extends BaseView>{
    void attachView(V v);
    void detachView();
}
