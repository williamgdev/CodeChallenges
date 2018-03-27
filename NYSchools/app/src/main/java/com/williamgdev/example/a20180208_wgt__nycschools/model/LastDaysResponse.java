package com.williamgdev.example.a20180208_wgt__nycschools.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by willimail on 2/27/18.
 */

public class LastDaysResponse {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("features")
    @Expose
    private List<Feature> features = null;
    @SerializedName("bbox")
    @Expose
    private List<Float> bbox = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Float> getBbox() {
        return bbox;
    }

    public void setBbox(List<Float> bbox) {
        this.bbox = bbox;
    }
}
