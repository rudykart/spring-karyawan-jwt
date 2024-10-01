package com.rudykart.karyawan.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rudykart.karyawan.entities.Karyawan;

@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, Long> {
    @Query(value = "SELECT * FROM karyawan ORDER BY name ASC", nativeQuery = true)
    Page<Karyawan> findAllKaryawanWithPage(Pageable pageable);
}
