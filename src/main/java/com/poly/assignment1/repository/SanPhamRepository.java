package com.poly.assignment1.repository;

import com.poly.assignment1.entities.SanPham;
import com.poly.assignment1.entities.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    int ACTIVE = 1;
    int INACTIVE = 0;


    Page<SanPham> findByTrangThai(int trangThai, Pageable pageable);


    Object getByMaAndTen(String ma, String ten);

    Page<SanPham> findByTenLikeAndTrangThai(String searchKey, int active, Pageable pageable);

    Page<SanPham> findByTenLike(String searchKey, Pageable pageable);
}
