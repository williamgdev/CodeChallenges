package com.williamgdev.example.earthquakecodetest.model;


import com.williamgdev.example.earthquakecodetest.dto.Geometry;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private float longitude, latitude, depth;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public static Coordinate create(Geometry geometry) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLongitude(geometry.getCoordinates().get(0));
        coordinate.setLatitude(geometry.getCoordinates().get(1));
        coordinate.setDepth(geometry.getCoordinates().get(2));
        return coordinate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Latitude: ");
        builder.append(getLatitude());
        builder.append(" / Longitude: ");
        builder.append(getLongitude());
        return builder.toString();
    }
}
