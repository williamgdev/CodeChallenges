package com.williamgdev.example.a20180208_wgt__nycschools.presenter;

import com.williamgdev.example.a20180208_wgt__nycschools.view.BaseView;

public interface BasePresenter <V extends BaseView>{
    void attachView(V v);
    void detachView();
}
