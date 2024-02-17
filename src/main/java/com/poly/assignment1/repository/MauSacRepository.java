package com.poly.assignment1.repository;

import com.poly.assignment1.entities.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Integer> {
    int ACTIVE = 1;
    int INACTIVE = 0;


    Page<MauSac> findByTrangThai(int trangThai, Pageable pageable);

    Page<MauSac> findByTenAndTrangThai(String ten, int trangThai, Pageable pageable);

    Page<MauSac> findByTen(String searchKey, Pageable pageable);


    Object getByMaAndTen(String ma, String ten);

    Page<MauSac> findByTenLike(String searchKey, Pageable pageable);

    Page<MauSac> findByTenLikeAndTrangThai(String searchKey, int active, Pageable pageable);
}
