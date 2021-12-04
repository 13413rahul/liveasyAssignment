package com.livesy.internship.response;

import lombok.Data;

@Data
public class LoadResponse {

    private long loadId;

    private String loadingPoint;

    private String unloadingPoint;

    private String productType;

    private String truckType;

    private int noOfTrucks;

    private int weight;

    // optional
    private String comment;

    private String date;
}
