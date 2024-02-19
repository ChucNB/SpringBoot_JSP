package com.poly.assignment1.repository;

import com.poly.assignment1.entities.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    int ACTIVE = 1;
    int INACTIVE = 0;


    Page<NhanVien> findByTrangThai(int trangThai, Pageable pageable);

    Page<NhanVien> findByTenAndTrangThai(String ten, int trangThai, Pageable pageable);

    Page<NhanVien> findByTen(String searchKey, Pageable pageable);


    Object getByMaAndTen(String ma, String ten);

    Object getByMa(String ma);

    Page<NhanVien> findByTenLike(String searchKey, Pageable pageable);

    Page<NhanVien> findByTenLikeAndTrangThai(String searchKey, int active, Pageable pageable);

    List<NhanVien> findAllByTrangThaiOrderByTenDesc(int i);
}
