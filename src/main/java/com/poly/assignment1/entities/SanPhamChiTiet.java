package com.poly.assignment1.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class SanPhamChiTiet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "MaSPCT", nullable = false, length = 255)
    private String maSpct;
    @Basic
    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;
    @Basic
    @Column(name = "DonGia", nullable = false, precision = 0)
    private Double donGia;
    @Basic
    @Column(name = "TrangThai", nullable = false)
    private Integer trangThai;
    @OneToMany(mappedBy = "sanPhamChiTiet")
    private Collection<HoaDonChiTiet> hoaDonChiTietsById;
    @ManyToOne
    @JoinColumn(name = "IdMauSac", referencedColumnName = "ID", nullable = false)
    private MauSac mauSac;
    @ManyToOne
    @JoinColumn(name = "IdKichThuoc", referencedColumnName = "ID", nullable = false)
    private KichThuoc kichThuoc;
    @ManyToOne
    @JoinColumn(name = "IdSanPham", referencedColumnName = "ID", nullable = false)
    private SanPham sanPham;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaSpct() {
        return maSpct;
    }

    public void setMaSpct(String maSpct) {
        this.maSpct = maSpct;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
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
        SanPhamChiTiet that = (SanPhamChiTiet) o;
        return Objects.equals(id, that.id) && Objects.equals(maSpct, that.maSpct) && Objects.equals(soLuong, that.soLuong) && Objects.equals(donGia, that.donGia) && Objects.equals(trangThai, that.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maSpct, soLuong, donGia, trangThai);
    }

    public Collection<HoaDonChiTiet> getHoaDonChiTietsById() {
        return hoaDonChiTietsById;
    }

    public void setHoaDonChiTietsById(Collection<HoaDonChiTiet> hoaDonChiTietsById) {
        this.hoaDonChiTietsById = hoaDonChiTietsById;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public KichThuoc getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(KichThuoc kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
}
