package com.poly.assignment1.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class NhanVien {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Ten", nullable = false, length = 255)
    private String ten;
    @Basic
    @Column(name = "Ma", nullable = false, length = 255)
    private String ma;
    @Basic
    @Column(name = "TenDangNhap", nullable = false, length = 255)
    private String tenDangNhap;
    @Basic
    @Column(name = "MatKhau", nullable = false, length = 255)
    private String matKhau;
    @Basic
    @Column(name = "TrangThai", nullable = true)
    private Integer trangThai;
    @OneToMany(mappedBy = "nhanVien")
    private Collection<HoaDon> hoaDonsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
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
        NhanVien nhanVien = (NhanVien) o;
        return Objects.equals(id, nhanVien.id) && Objects.equals(ten, nhanVien.ten) && Objects.equals(ma, nhanVien.ma) && Objects.equals(tenDangNhap, nhanVien.tenDangNhap) && Objects.equals(matKhau, nhanVien.matKhau) && Objects.equals(trangThai, nhanVien.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ten, ma, tenDangNhap, matKhau, trangThai);
    }

    public Collection<HoaDon> getHoaDonsById() {
        return hoaDonsById;
    }

    public void setHoaDonsById(Collection<HoaDon> hoaDonsById) {
        this.hoaDonsById = hoaDonsById;
    }
}
