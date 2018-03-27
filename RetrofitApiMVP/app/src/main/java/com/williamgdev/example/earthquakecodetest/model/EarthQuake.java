package com.williamgdev.example.earthquakecodetest.model;


import com.williamgdev.example.earthquakecodetest.dto.Feature;
import com.williamgdev.example.earthquakecodetest.dto.LastDaysResponse;
import com.williamgdev.example.earthquakecodetest.util.TimeUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EarthQuake implements Serializable {
    private String time;
    private String place;
    private Coordinate coordinate;
    private Float magnitude;
    private boolean tsunami;
    private String id;

    public static List<EarthQuake> create(LastDaysResponse lastDaysResponse) {
        List<EarthQuake> earthQuakeList = new ArrayList<>();
        for (Feature item :
                lastDaysResponse.getFeatures()) {
            earthQuakeList.add(create(item));
        }
        return earthQuakeList;
    }

    private static EarthQuake create(Feature item) {
        EarthQuake earthQuake = new EarthQuake();
        earthQuake.setTime(TimeUtils.getLocalTimeString(item.getProperties().getTime()));
        earthQuake.setPlace(item.getProperties().getPlace());
        earthQuake.setMagnitude(item.getProperties().getMag());
        earthQuake.setCoordinate(Coordinate.create(item.getGeometry()));
        earthQuake.setTsunami(item.getProperties().getTsunami() == 1);
        earthQuake.setId(item.getId());
        return earthQuake;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setMagnitude(Float magnitude) {
        this.magnitude = magnitude;
    }

    public Float getMagnitude() {
        return magnitude;
    }

    public void setTsunami(boolean tsunami) {
        this.tsunami = tsunami;
    }

    public boolean isTsunami() {
        return tsunami;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
