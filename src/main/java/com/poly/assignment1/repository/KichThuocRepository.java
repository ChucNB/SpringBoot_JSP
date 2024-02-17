package com.poly.assignment1.repository;

import com.poly.assignment1.entities.KichThuoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KichThuocRepository extends JpaRepository<KichThuoc, Integer> {
    int ACTIVE = 1;
    int INACTIVE = 0;


    Page<KichThuoc> findByTrangThai(int trangThai, Pageable pageable);

    Page<KichThuoc> findByTenAndTrangThai(String ten, int trangThai, Pageable pageable);

    Page<KichThuoc> findByTen(String searchKey, Pageable pageable);


    Object getByMaAndTen(String ma, String ten);
}
