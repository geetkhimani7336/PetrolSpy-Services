package com.petro_spy_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petro_spy_svc.entity.LocationEntity;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

	
}
