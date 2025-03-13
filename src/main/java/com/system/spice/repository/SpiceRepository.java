package com.system.spice.repository;

import com.system.spice.entity.Spice;
import com.system.spice.entity.SpiceDetail;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpiceRepository extends JpaRepository<Spice, Long> {
	
	//  Phuc
	@Query("SELECT DISTINCT  s FROM Spice s LEFT JOIN FETCH s.images")
	List<Spice> findAllWithImages();
	
	

}