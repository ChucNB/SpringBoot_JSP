package com.poly.assignment1.repository;

import com.poly.assignment1.entities.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    int ACTIVE = 1;
    int INACTIVE = 0;


    Page<KhachHang> findByTrangThai(int trangThai, Pageable pageable);

    Page<KhachHang> findByTenLikeAndTrangThai(String ten, int trangThai, Pageable pageable);

    Page<KhachHang> findByTenLike(String searchKey, Pageable pageable);


    Object getByMaAndTen(String ma, String ten);
}
