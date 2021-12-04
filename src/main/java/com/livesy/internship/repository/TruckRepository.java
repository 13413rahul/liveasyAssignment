package com.livesy.internship.repository;

import com.livesy.internship.entity.Truck;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TruckRepository extends CrudRepository<Truck, Long> {
    @Modifying
    @Query(value = "insert into Truck(truck_type) values(:truckType)",nativeQuery = true)
    @Transactional
    void insertIntoTruckTable(@Param("truckType") String truckType);

    boolean existsByTruckType(String truckType);

    Truck findByTruckType(String truckType);

    Truck findByTruckId(Long truckId);
}
