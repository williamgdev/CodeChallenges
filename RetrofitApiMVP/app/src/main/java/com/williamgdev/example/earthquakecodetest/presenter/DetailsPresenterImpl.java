package com.williamgdev.example.earthquakecodetest.presenter;

import com.williamgdev.example.earthquakecodetest.R;
import com.williamgdev.example.earthquakecodetest.model.EarthQuake;
import com.williamgdev.example.earthquakecodetest.view.DetailsView;

import java.io.Serializable;

public class DetailsPresenterImpl implements DetailsPresenter {
    private DetailsView view;
    private EarthQuake earthQuake;


    @Override
    public void attachView(DetailsView detailsView) {
        view = detailsView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getData(Serializable item) {
        view.showProgress();
        if (item instanceof EarthQuake) {
            this.earthQuake = (EarthQuake) item;
            view.setItem(
                    this.earthQuake.getTime(),
                    this.earthQuake.getPlace(),
                    this.earthQuake.getMagnitude(),
                    this.earthQuake.getCoordinate().toString(),
                    this.earthQuake.isTsunami() ? view.getContext().getString(R.string.possible_tsunami): null
            );
        } else {
            view.showError("Error trying to retrieve the data");
        }
        view.hideProgress();
    }
}
