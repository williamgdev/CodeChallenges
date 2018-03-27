package com.williamgdev.example.a20180208_wgt__nycschools.presenter;

import com.williamgdev.example.a20180208_wgt__nycschools.interactor.OpenDataInteractor;
import com.williamgdev.example.a20180208_wgt__nycschools.model.SchoolSAT;
import com.williamgdev.example.a20180208_wgt__nycschools.view.DetailsView;

import java.util.List;

public class DetailsPresenterImpl implements DetailsPresenter {
    private DetailsView view;
    private String schoolCode;
    private OpenDataInteractor openDataInteractor;
    private SchoolSAT schoolSat;


    @Override
    public void attachView(DetailsView detailsView) {
        view = detailsView;
        openDataInteractor = OpenDataInteractor.getInstance();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getData() {
        view.showProgress();
        schoolCode = view.getIntent().getStringExtra("schoolCode");
        openDataInteractor.getSchoolSAT(schoolCode, new OpenDataInteractor.ApiListener<List<SchoolSAT>>() {
            @Override
            public void onSuccess(List<SchoolSAT> result) {
                if (result.size() > 0) {
                    schoolSat = result.get(0);
                    view.setSchool(generateString(schoolSat));
                } else {
                    view.showError("Something wrong happens, seems like there is no data from the server to display");
                }
                view.hideProgress();
            }

            @Override
            public void onError(String error) {
                view.showError(error);
                view.hideProgress();
            }
        });
    }

    private String generateString(SchoolSAT result) {
        StringBuilder output = new StringBuilder();
        output.append("Name: ");
        output.append(result.getSchoolName());
        output.append("\nMath: ");
        output.append(result.getSatMathAvgScore());
        output.append("\nRead: ");
        output.append(result.getSatCriticalReadingAvgScore());
        output.append("\nWrite: ");
        output.append(result.getSatWritingAvgScore());
        return output.toString();
    }

    @Override
    public SchoolSAT getSchoolSAT() {
        return schoolSat;
    }
}
