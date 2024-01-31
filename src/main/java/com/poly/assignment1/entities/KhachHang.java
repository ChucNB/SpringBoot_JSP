package com.poly.assignment1.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class KhachHang {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Ma", nullable = false, length = 255)
    private String ma;
    @Basic
    @Column(name = "Ten", nullable = false, length = 255)
    private String ten;
    @Basic
    @Column(name = "SDT", nullable = false, length = 255)
    private String sdt;
    @Basic
    @Column(name = "TrangThai", nullable = false)
    private Integer trangThai;
    @OneToMany(mappedBy = "khachHang")
    private Collection<HoaDon> hoaDonsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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
        KhachHang khachHang = (KhachHang) o;
        return Objects.equals(id, khachHang.id) && Objects.equals(ma, khachHang.ma) && Objects.equals(ten, khachHang.ten) && Objects.equals(sdt, khachHang.sdt) && Objects.equals(trangThai, khachHang.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ma, ten, sdt, trangThai);
    }

    public Collection<HoaDon> getHoaDonsById() {
        return hoaDonsById;
    }

    public void setHoaDonsById(Collection<HoaDon> hoaDonsById) {
        this.hoaDonsById = hoaDonsById;
    }
}
