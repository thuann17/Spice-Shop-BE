package com.system.spice.repository;

import com.system.spice.entity.Spice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpiceRepository extends JpaRepository<Spice, Integer> {
}