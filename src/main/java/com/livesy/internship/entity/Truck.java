package com.livesy.internship.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "truck")
public class Truck implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="truck_id")
    private Long truckId;

    @Column(name="truck_type", nullable=false)
    @Basic(optional = false)
    private String truckType;
}
