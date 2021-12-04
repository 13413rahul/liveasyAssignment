package com.livesy.internship.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="location_id")
    private Long locationId;

    @Column(name="location_name", nullable=false)
    @Basic(optional = false)
    private String locationName;
}
