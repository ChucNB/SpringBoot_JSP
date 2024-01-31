package com.poly.assignment1.repository.dto.hoaDon;

import com.poly.assignment1.repository.dto.khachHang.KhachHang;
import com.poly.assignment1.repository.dto.nhanVien.NhanVien;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDon {
    private int id;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private Date ngayMuaHang;
    private boolean trangThai;
}
