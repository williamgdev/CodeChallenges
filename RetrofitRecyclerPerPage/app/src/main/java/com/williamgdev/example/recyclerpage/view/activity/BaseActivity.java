package com.williamgdev.example.recyclerpage.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.williamgdev.example.recyclerpage.presenter.BasePresenter;
import com.williamgdev.example.recyclerpage.view.BaseView;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

        setProgressBar();

        initializeUIComponents();
        initializePresenter();
        getPresenter().attachView(getView());

    }
    public void setProgressBar(){
        if (!isProgressBarAdded()) {
            return;
        }
        progressBar = (ProgressBar) findViewById(getProgressbarID());
    }

    @Override
    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void launch(Class<? extends Activity> activityClass) {
        startActivity(new Intent(this, activityClass));
    }

    public void launch(Class<? extends Activity> activityClass, Bundle bundle) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        getPresenter().detachView();
        super.onStop();
    }

    @Override
    public Context getContext() {
        return this;
    }
    public abstract <T extends BasePresenter> T getPresenter();

    public abstract <T extends BaseView> T getView();

    @LayoutRes
    protected abstract int getLayoutID();

    protected abstract void initializeUIComponents();

    protected abstract void initializePresenter();

    @IdRes
    protected abstract int getProgressbarID();

    protected abstract boolean isProgressBarAdded();
}
