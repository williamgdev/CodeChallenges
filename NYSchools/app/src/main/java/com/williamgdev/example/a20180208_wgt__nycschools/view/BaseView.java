package com.williamgdev.example.a20180208_wgt__nycschools.view;

import android.content.Context;

public interface BaseView {
    void showProgress();

    void hideProgress();

    void showError(String error);

    Context getContext();
}
