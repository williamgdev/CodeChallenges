package com.williamgdev.example.a20180208_wgt__nycschools.view;

import com.williamgdev.example.a20180208_wgt__nycschools.model.School;

import java.util.List;

public interface HomeView extends BaseView{
    void setSchools(List<School> schools);

    void onSchoolSelected(School school);
}
