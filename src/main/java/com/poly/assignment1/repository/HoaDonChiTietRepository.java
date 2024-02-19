package com.poly.assignment1.repository;

import com.poly.assignment1.entities.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {
    int ACTIVE = 1;
    int INACTIVE = 0;


//    Collection<HoaDonChiTiet> findBySanPhamIdAndTrangThai(int spID, int trangThai, Pageable pageable);

    Collection<HoaDonChiTiet> findByHoaDonId(Integer idHD);

    HoaDonChiTiet getBySanPhamChiTietIdAndHoaDonId(Integer idSPCT, Integer idHD);


//    Object getByIdMauSacAndIdKhachHangAndIdSanPham(int idMauSac, int idKhachHang, int idSanPham);
}
