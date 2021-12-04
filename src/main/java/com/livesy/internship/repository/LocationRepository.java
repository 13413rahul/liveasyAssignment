package com.livesy.internship.repository;

import com.livesy.internship.entity.Location;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    @Modifying
    @Query(value = "insert into Location(location_name) values(:locationName)",nativeQuery = true)
    @Transactional
    void insertIntoLocationTable(@Param("locationName") String locationName);


    boolean existsByLocationName(String locationName);

    Location findByLocationName(String locationName);

    Location findByLocationId(Long locationId);
}
