package com.poly.assignment1.repository;

import com.poly.assignment1.entities.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Integer> {
    int ACTIVE = 1;
    int INACTIVE = -1;

    Page<MauSac> findByTrangThai(int trangThai, Pageable pageable);
}
