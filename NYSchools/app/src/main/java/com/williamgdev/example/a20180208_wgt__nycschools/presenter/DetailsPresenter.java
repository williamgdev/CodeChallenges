package com.williamgdev.example.a20180208_wgt__nycschools.presenter;

import com.williamgdev.example.a20180208_wgt__nycschools.model.SchoolSAT;
import com.williamgdev.example.a20180208_wgt__nycschools.view.DetailsView;

public interface DetailsPresenter extends BasePresenter<DetailsView> {
    void getData();

    SchoolSAT getSchoolSAT();
}
