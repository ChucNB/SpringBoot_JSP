package com.poly.assignment1.repository;

import com.poly.assignment1.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    int ACTIVE = 1;
    int INACTIVE = 0;


    Page<HoaDon> findByTrangThai(int trangThai, Pageable pageable);

    @Query("SELECT hd FROM HoaDon hd where hd.trangThai=0 order by hd.ngayMuaHang DESC")
    List<HoaDon> findByTrangThaiChuaThanhToan();

    Page<HoaDon> findByKhachHangTenLikeAndTrangThai(String searchKey, int active, Pageable pageable);

    Page<HoaDon> findByKhachHangTenLike(String searchKey, Pageable pageable);

    @Query("SELECT hoaDon FROM HoaDon hoaDon LEFT JOIN HoaDonChiTiet hoaDonChiTiet ON hoaDon.id=hoaDonChiTiet.hoaDon.id GROUP BY hoaDon ORDER BY SUM(hoaDonChiTiet.soLuong*hoaDonChiTiet.donGia) DESC")
    Page<HoaDon> findAllOrderByTongHoaDonDESC(Pageable pageable);

    @Query("SELECT hoaDon FROM HoaDon hoaDon LEFT JOIN HoaDonChiTiet hoaDonChiTiet ON hoaDon.id=hoaDonChiTiet.hoaDon.id GROUP BY hoaDon ORDER BY SUM(hoaDonChiTiet.soLuong*hoaDonChiTiet.donGia) ASC")
    Page<HoaDon> findAllOrderByTongHoaDonASC(Pageable pageable);

    @Query("SELECT hoaDon FROM HoaDon hoaDon LEFT JOIN HoaDonChiTiet hoaDonChiTiet ON hoaDon.id=hoaDonChiTiet.hoaDon.id WHERE hoaDonChiTiet.hoaDon.trangThai=:active GROUP BY hoaDon ORDER BY SUM(hoaDonChiTiet.soLuong*hoaDonChiTiet.donGia) DESC")
    Page<HoaDon> findAllOrderByTongHoaDonDESCAndTrangThai(Pageable pageable, @Param("active") int active);

    @Query("SELECT hoaDon FROM HoaDon hoaDon LEFT JOIN HoaDonChiTiet hoaDonChiTiet ON hoaDon.id=hoaDonChiTiet.hoaDon.id WHERE hoaDonChiTiet.hoaDon.trangThai=:active GROUP BY hoaDon ORDER BY SUM(hoaDonChiTiet.soLuong*hoaDonChiTiet.donGia) ASC")
    Page<HoaDon> findAllOrderByTongHoaDonASCAndTrangThai(Pageable pageable, @Param("active") int active);

}
