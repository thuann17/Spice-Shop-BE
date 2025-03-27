package com.system.spice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.system.spice.entity.Spice;
import com.system.spice.entity.SpiceDetail;

@Repository
public interface SpiceDetailRepository extends JpaRepository<SpiceDetail, Long> {
    SpiceDetail findBySpice(Spice spice);
}
