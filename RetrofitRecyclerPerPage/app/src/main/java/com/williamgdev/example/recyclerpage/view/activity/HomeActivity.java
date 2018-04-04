package com.williamgdev.example.recyclerpage.view.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.williamgdev.example.recyclerpage.R;
import com.williamgdev.example.recyclerpage.adapter.CatsAdapter;
import com.williamgdev.example.recyclerpage.presenter.HomePresenterImpl;
import com.williamgdev.example.recyclerpage.view.HomeView;
import com.williamgdev.example.recyclerpage.dto.CatResponse;
import com.williamgdev.example.recyclerpage.presenter.HomePresenter;

import java.util.List;

public class HomeActivity extends BaseActivity implements HomeView {
    HomePresenter presenter;
    private RecyclerView catRecyclerView;
    private CatsAdapter catsAdapter;

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
        catRecyclerView = findViewById(R.id.home_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        catRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void initializePresenter() {
        presenter = new HomePresenterImpl();
        presenter.attachView(this);
        presenter.getCatsFirstPage();
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
    public void setCats(List<CatResponse> catsResponseList) {
        if (catsAdapter == null) {
            catsAdapter = new CatsAdapter(getContext(), catsResponseList);
            catsAdapter.setCatsAdapterListener(catsAdapterListener);
            catRecyclerView.setAdapter(catsAdapter);
            catRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL));
        } else {
            catsAdapter.addCatList(catsResponseList);
            catsAdapter.notifyDataSetChanged();
        }
    }

    private CatsAdapter.CatsAdapterListener catsAdapterListener = new CatsAdapter.CatsAdapterListener() {
        @Override
        public void loadMoreData() {
            presenter.getCatsNextPage();
        }
    };

}
