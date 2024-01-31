package com.poly.assignment1.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class HoaDon {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgayMuaHang() {
        return ngayMuaHang;
    }

    public void setNgayMuaHang(Date ngayMuaHang) {
        this.ngayMuaHang = ngayMuaHang;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoaDon hoaDon = (HoaDon) o;
        return Objects.equals(id, hoaDon.id) && Objects.equals(ngayMuaHang, hoaDon.ngayMuaHang) && Objects.equals(trangThai, hoaDon.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ngayMuaHang, trangThai);
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Collection<HoaDonChiTiet> getHoaDonChiTietsById() {
        return hoaDonChiTietsById;
    }

    public void setHoaDonChiTietsById(Collection<HoaDonChiTiet> hoaDonChiTietsById) {
        this.hoaDonChiTietsById = hoaDonChiTietsById;
    }
}
