package com.livesy.internship.request;

import lombok.Data;

@Data
public class UpdateLoadRequest {
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
