package com.livesy.internship.repository;

import com.livesy.internship.entity.Load;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface LoadRepository extends CrudRepository<Load, Long> {

    @Modifying
    @Query(value = "insert into Load(loading_point_id,unloading_point_id,truck_type_id,product_type_id,shipper_id,comment,no_of_trucks,date,weight) values(:loadingPointId,:unloadingPointId,:truckTypeId,:productTypeId,:shipperId,:comment,:noOfTrucks,:date,:weight)",nativeQuery = true)
    @Transactional
    void insertIntoLoadTable(@Param("loadingPointId") Long loadingPointId, @Param("unloadingPointId") Long unloadingPointId, @Param("truckTypeId") Long truckTypeId, @Param("productTypeId") Long productTypeId, @Param("shipperId") String shipperId, @Param("comment") String comment, @Param("noOfTrucks") int noOfTrucks, @Param("date") String date, @Param("weight") int weight);

    List<Load> findByShipperId(String shipperId);

    Load findByLoadId(Long loadId);

    @Modifying
    @Query(value = "UPDATE Load SET  loading_point_id=:loadingPointId, unloading_point_id=:unloadingPointId, product_type_id=:productTypeId, truck_type_id=:truckTypeId , no_of_trucks=:noOfTrucks, weight=:weight, comment=:comment, date=:date WHERE load_id=:loadId",nativeQuery = true)
    @Transactional
    void updateWithLoadId(@Param("loadingPointId") Long loadingPointId, @Param("unloadingPointId") Long unloadingPointId,
                            @Param("productTypeId") Long productTypeId, @Param("truckTypeId") Long truckTypeId, @Param("noOfTrucks") int noOfTrucks,
                            @Param("weight") int weight, @Param("comment") String comment,  @Param("date") String date, @Param("loadId") Long loadId);

    @Transactional
    void deleteByLoadId(Long loadId);
}
