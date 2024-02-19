package com.poly.assignment1.repository;

import com.poly.assignment1.entities.SanPhamChiTiet;
import com.poly.assignment1.repository.dto.sanPham.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Integer> {
    int ACTIVE = 1;
    int INACTIVE = 0;


    Page<SanPhamChiTiet> findBySanPhamIdAndTrangThai(int spID, int trangThai, Pageable pageable);

    Page<SanPhamChiTiet> findBySanPhamId(Integer idSP, Pageable page);

    @Query("SELECT sp FROM SanPhamChiTiet sp WHERE sp.trangThai=1")
    List<SanPhamChiTiet> findAllActive();

    @Query("SELECT sp FROM SanPhamChiTiet sp WHERE sp.trangThai=1 and (sp.sanPham.ten like ?1 or sp.maSPCT like ?1)")
    List<SanPhamChiTiet> findByTenAndMa(String keyWord);


//    Object getByIdMauSacAndIdKhachHangAndIdSanPham(int idMauSac, int idKhachHang, int idSanPham);
}
