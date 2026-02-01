package com.petro_spy_svc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petro_spy_svc.entity.LocationEntity;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

	@Query(value = "SELECT * FROM location WHERE id = :id", nativeQuery = true)
	LocationEntity getLocationId(@Param("id") Long locationId);

	 
	@Query(value = "SELECT * FROM location WHERE location = :location", nativeQuery = true)
	LocationEntity getLocationName(@Param("location") String location);


	@Query(value = "SELECT * FROM location WHERE state = :state", nativeQuery = true)
	List<LocationEntity> getLocationNameBasedOnState(@Param("state")String state);
	
}
