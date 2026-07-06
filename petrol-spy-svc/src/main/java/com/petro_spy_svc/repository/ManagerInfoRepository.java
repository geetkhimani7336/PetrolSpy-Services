package com.petro_spy_svc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petro_spy_svc.entity.MangerEntity;

public interface ManagerInfoRepository extends JpaRepository<MangerEntity, Long> {

	@Query(value = """
	        SELECT *
	        FROM "manager_info"
	        WHERE address -> 'workInfo' ->> 'state' = :state
	        """, nativeQuery = true)
	List<MangerEntity> findManager(@Param("state") String state);

}
