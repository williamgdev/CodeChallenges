package com.williamgdev.example.a20180208_wgt__nycschools.activity;

import android.widget.TextView;
import android.widget.Toast;

import com.williamgdev.example.a20180208_wgt__nycschools.R;
import com.williamgdev.example.a20180208_wgt__nycschools.presenter.DetailsPresenter;
import com.williamgdev.example.a20180208_wgt__nycschools.presenter.DetailsPresenterImpl;
import com.williamgdev.example.a20180208_wgt__nycschools.view.DetailsView;


public class DetailsActivity extends BaseActivity implements DetailsView {

    private DetailsPresenter presenter;
    private TextView txtDetails;

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public DetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    public DetailsView getView() {
        return this;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_details;
    }

    @Override
    protected void initializeUIComponents() {
        txtDetails = findViewById(R.id.details);
    }

    @Override
    protected void initializePresenter() {
        presenter = new DetailsPresenterImpl();
        presenter.attachView(this);
        presenter.getData();
    }

    @Override
    protected int getProgressbarID() {
        return R.id.home_progress;
    }

    @Override
    protected boolean isProgressBarAdded() {
        return true;
    }

    @Override
    public void setSchool(String details) {
        txtDetails.setText(details);
    }
}
