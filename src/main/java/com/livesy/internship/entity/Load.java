package com.livesy.internship.entity;

import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "load")
@EntityListeners(value = AuditingEntityListener.class)
public class Load implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="load_id")
    private Long loadId;

    @Column(name="loading_point_id")
    private Long loadingPointId;

    @Column(name="unloading_point_id")
    private Long unloadingPointId;

    @Column(name="product_type_id")
    private Long productTypeId;

    @Column(name="truck_type_id")
    private Long truckTypeId;

    @Column(name="no_of_trucks", nullable=false)
    @Basic(optional = false)
    private int noOfTrucks;

    @Column(name="weight", nullable=false)
    @Basic(optional = false)
    private int weight;

    // optional
    @Column(name="comment")
    private String comment;

    @Column(name="shipper_id")
    private String shipperId;

    //  “Date” : “dd-mm-yyyy”
    @Column(name="date")
    private String date;

}
