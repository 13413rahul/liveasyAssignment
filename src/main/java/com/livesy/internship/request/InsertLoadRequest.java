package com.livesy.internship.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InsertLoadRequest {

    private String loadingPoint;

    private String unloadingPoint;

    private String productType;

    private String truckType;

    private int noOfTrucks;

    private int weight;

    // optional
    private String comment;
}
