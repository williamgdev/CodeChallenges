package com.williamgdev.example.earthquakecodetest.view.activity;

import android.widget.TextView;
import android.widget.Toast;

import com.williamgdev.example.earthquakecodetest.R;
import com.williamgdev.example.earthquakecodetest.presenter.DetailsPresenter;
import com.williamgdev.example.earthquakecodetest.presenter.DetailsPresenterImpl;
import com.williamgdev.example.earthquakecodetest.view.DetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsActivity extends BaseActivity implements DetailsView {

    private DetailsPresenter presenter;
    @BindView(R.id.detail_item_coordinate)
    TextView txtCoordinate;
    @BindView(R.id.detail_item_date)
    TextView txtDate;
    @BindView(R.id.detail_item_mag)
    TextView txtMagnitude;
    @BindView(R.id.detail_item_place)
    TextView txtPlace;
    @BindView(R.id.detail_item_tsunami)
    TextView txtTsunami;

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
        ButterKnife.bind(this);
    }

    @Override
    protected void initializePresenter() {
        presenter = new DetailsPresenterImpl();
        presenter.attachView(this);
        presenter.getData(getIntent().getSerializableExtra("earthQuake"));
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
    public void setItem(String time, String place, Float magnitude, String coordinate, String tsunami) {
        txtDate.setText(time);
        txtPlace.setText(place);
        txtMagnitude.setText(magnitude + "");
        txtCoordinate.setText(coordinate);
        txtTsunami.setText(tsunami);
    }
}
