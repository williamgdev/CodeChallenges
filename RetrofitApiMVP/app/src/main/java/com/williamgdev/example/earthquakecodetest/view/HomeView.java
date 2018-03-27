package com.williamgdev.example.earthquakecodetest.view;

import com.williamgdev.example.earthquakecodetest.model.EarthQuake;

import java.util.List;

public interface HomeView extends BaseView{
    void setEarthQuake(List<EarthQuake> earthQuake);

    void onEarthQuakeSelected(EarthQuake earthQuake);
}
