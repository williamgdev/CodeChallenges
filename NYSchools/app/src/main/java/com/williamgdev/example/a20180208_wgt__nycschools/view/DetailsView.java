package com.williamgdev.example.a20180208_wgt__nycschools.view;

import android.content.Intent;

public interface DetailsView extends BaseView{
    Intent getIntent();

    void setSchool(String details);
}
