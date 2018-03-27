package com.williamgdev.example.a20180208_wgt__nycschools.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.williamgdev.example.a20180208_wgt__nycschools.R;
import com.williamgdev.example.a20180208_wgt__nycschools.adapter.SchoolAdapter;
import com.williamgdev.example.a20180208_wgt__nycschools.model.School;
import com.williamgdev.example.a20180208_wgt__nycschools.presenter.HomePresenter;
import com.williamgdev.example.a20180208_wgt__nycschools.presenter.HomePresenterImpl;
import com.williamgdev.example.a20180208_wgt__nycschools.view.HomeView;

import java.util.List;

public class HomeActivity extends BaseActivity implements HomeView {
    HomePresenter presenter;
    private RecyclerView recyclerSchools;
    private SchoolAdapter schoolAdapter;

    @Override
    public HomePresenter getPresenter() {
        return presenter;
    }

    @Override
    public HomeView getView() {
        return this;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initializeUIComponents() {
        recyclerSchools = findViewById(R.id.home_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerSchools.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void initializePresenter() {
        presenter = new HomePresenterImpl();
        presenter.attachView(this);
        presenter.getSchools();
    }

    @Override
    public void setSchools(List<School> schools) {
        schoolAdapter = new SchoolAdapter(getContext(), this, schools);
        recyclerSchools.setAdapter(schoolAdapter);
    }

    @Override
    public void onSchoolSelected(School school) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("schoolCode", school.getDbn());
        launch(DetailsActivity.class, bundle);
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
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
