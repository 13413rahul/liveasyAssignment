package com.livesy.internship.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productId;

    @Column(name="product_type", nullable=false)
    @Basic(optional = false)
    private String productType;
}
