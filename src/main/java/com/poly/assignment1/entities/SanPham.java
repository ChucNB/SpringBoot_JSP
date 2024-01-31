package com.poly.assignment1.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class SanPham {
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
    @Column(name = "TrangThai", nullable = false)
    private Integer trangThai;
    @OneToMany(mappedBy = "sanPham")
    private Collection<SanPhamChiTiet> sanPhamChiTietsById;

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
        SanPham sanPham = (SanPham) o;
        return Objects.equals(id, sanPham.id) && Objects.equals(ma, sanPham.ma) && Objects.equals(ten, sanPham.ten) && Objects.equals(trangThai, sanPham.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ma, ten, trangThai);
    }

    public Collection<SanPhamChiTiet> getSanPhamChiTietsById() {
        return sanPhamChiTietsById;
    }

    public void setSanPhamChiTietsById(Collection<SanPhamChiTiet> sanPhamChiTietsById) {
        this.sanPhamChiTietsById = sanPhamChiTietsById;
    }
}
