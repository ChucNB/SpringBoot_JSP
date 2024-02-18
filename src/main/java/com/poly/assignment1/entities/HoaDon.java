package com.poly.assignment1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.NumberFormat;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {
    public HoaDon(HoaDon hoaDon, Long tongThanhToan) {
        this.id = hoaDon.id;
        this.ngayMuaHang = hoaDon.ngayMuaHang;
        this.trangThai = hoaDon.trangThai;
        this.khachHang = hoaDon.khachHang;
        this.tongHoaDon = hoaDon.tongHoaDon;
        this.nhanVien = hoaDon.nhanVien;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "NgayMuaHang", nullable = false)
    private Date ngayMuaHang;
    @Basic
    @Column(name = "TrangThai", nullable = false)
    private Integer trangThai;
    @ManyToOne
    @JoinColumn(name = "IdKH", referencedColumnName = "ID", nullable = false)
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "IdNV", referencedColumnName = "ID", nullable = false)
    private NhanVien nhanVien;
    @OneToMany(mappedBy = "hoaDon")
    private Collection<HoaDonChiTiet> hoaDonChiTietsById;

    @Transient
    @NumberFormat(pattern = "###,###,###")
    private Double tongHoaDon;

    @PostLoad
    public void tinhTongHoaDon() {
        this.tongHoaDon = hoaDonChiTietsById.stream().map(hoaDonChiTiet -> hoaDonChiTiet.getDonGia() * hoaDonChiTiet.getSoLuong()).reduce((tong1, tong2) -> tong1 + tong2).orElse(0d);
    }

}
