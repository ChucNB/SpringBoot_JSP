package com.poly.assignment1.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class MauSac {


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
    @OneToMany(mappedBy = "mauSac")
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
        MauSac mauSac = (MauSac) o;
        return Objects.equals(id, mauSac.id) && Objects.equals(ma, mauSac.ma) && Objects.equals(ten, mauSac.ten) && Objects.equals(trangThai, mauSac.trangThai);
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
