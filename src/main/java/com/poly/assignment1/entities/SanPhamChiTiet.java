package com.poly.assignment1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SanPhamChiTiet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "MaSPCT", nullable = false, length = 255)
    private String maSPCT;
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

}
