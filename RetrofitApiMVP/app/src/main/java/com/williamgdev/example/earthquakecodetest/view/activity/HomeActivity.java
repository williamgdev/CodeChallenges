package com.williamgdev.example.earthquakecodetest.view.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.williamgdev.example.earthquakecodetest.R;
import com.williamgdev.example.earthquakecodetest.adapter.EarthQuakeAdapter;
import com.williamgdev.example.earthquakecodetest.model.EarthQuake;
import com.williamgdev.example.earthquakecodetest.presenter.HomePresenter;
import com.williamgdev.example.earthquakecodetest.presenter.HomePresenterImpl;
import com.williamgdev.example.earthquakecodetest.view.HomeView;

import java.util.List;


public class HomeActivity extends BaseActivity implements HomeView {
    HomePresenter presenter;
    private RecyclerView recyclerEarthQuakes;
    private EarthQuakeAdapter earthQuakeAdapter;

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
        recyclerEarthQuakes = findViewById(R.id.home_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerEarthQuakes.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void initializePresenter() {
        presenter = new HomePresenterImpl();
        presenter.attachView(this);
        presenter.getLastDays();
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

    @Override
    public void setEarthQuake(List<EarthQuake> earthQuakeList) {
        earthQuakeAdapter = new EarthQuakeAdapter(getContext(), this, earthQuakeList);
        recyclerEarthQuakes.setAdapter(earthQuakeAdapter);
        recyclerEarthQuakes.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onEarthQuakeSelected(EarthQuake earthQuake) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("earthQuake", earthQuake);
        launch(DetailsActivity.class, bundle);
    }
}
