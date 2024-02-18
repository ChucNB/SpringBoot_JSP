package com.poly.assignment1.repository.dto.hoaDonChiTiet;

import com.poly.assignment1.repository.dto.hoaDon.HoaDon;
import com.poly.assignment1.repository.dto.khachHang.KhachHang;
import com.poly.assignment1.repository.dto.nhanVien.NhanVien;
import com.poly.assignment1.repository.dto.sanPhamChiTiet.SanPhamChiTiet;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDonChiTiet {
    private int id;
    private HoaDon hoaDon;
    private SanPhamChiTiet sanPhamChiTiet;
    private int soLuong;
    private double donGia;
    private int trangThai;
}
