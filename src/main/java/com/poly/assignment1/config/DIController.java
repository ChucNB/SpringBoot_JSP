package com.poly.assignment1.config;

import com.poly.assignment1.repository.dto.hoaDon.HoaDon;
import com.poly.assignment1.repository.dto.hoaDonChiTiet.HoaDonChiTiet;
import com.poly.assignment1.repository.dto.khachHang.KhachHang;
import com.poly.assignment1.repository.dto.kichThuoc.KichThuoc;
import com.poly.assignment1.repository.dto.mauSac.MauSac;
import com.poly.assignment1.repository.dto.nhanVien.NhanVien;
import com.poly.assignment1.repository.dto.sanPham.SanPham;
import com.poly.assignment1.repository.dto.sanPhamChiTiet.SanPhamChiTiet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class DIController {
    @Bean()
    public List<KhachHang> getLSTKH() {
        List<KhachHang> lst = new ArrayList<>();
        lst.add(new KhachHang(1, "Nguyễn Văn A", "KH01", "0123456789", 1));
        lst.add(new KhachHang(2, "Nguyễn Văn B", "KH02", "0123456789", 1));
        lst.add(new KhachHang(3, "Nguyễn Văn C", "KH03", "0123456789", 1));
        return lst;
    }

    @Bean()
    public List<KichThuoc> getLSTKT() {
        List<KichThuoc> lst = new ArrayList<>();
        lst.add(new KichThuoc(1, "KT1", "Kích thước 01", 1));
        lst.add(new KichThuoc(2, "KT2", "Kích thước 02", 1));
        lst.add(new KichThuoc(3, "KT3", "Kích thước 03", 1));
        return lst;
    }

    @Bean()
    public List<MauSac> getLSTMS() {
        List<MauSac> lst = new ArrayList<>();
        lst.add(new MauSac(1, "MS1", "Màu sắc 01", 1));
        lst.add(new MauSac(2, "MS2", "Màu sắc 02", 1));
        lst.add(new MauSac(3, "MS3", "Màu sắc 03", 1));
        return lst;
    }

    @Bean()
    public List<SanPham> getLSTSP() {
        List<SanPham> lst = new ArrayList<>();
        lst.add(new SanPham(1, "SP1", "Sản phẩm 01", 1));
        lst.add(new SanPham(2, "SP2", "Sản phẩm 02", 1));
        lst.add(new SanPham(3, "SP3", "Sản phẩm 03", 1));
        return lst;
    }

    @Bean()
    public List<NhanVien> getLSTNV() {
        List<NhanVien> lst = new ArrayList<>();
        lst.add(new NhanVien(1, "Nguyễn Văn A", "NVA", "NVA", "12341234", 1));
        lst.add(new NhanVien(2, "Nguyễn Văn B", "NVB", "NVB", "12341234", 1));
        lst.add(new NhanVien(3, "Nguyễn Văn C", "NVC", "NVC", "12341234", 1));
        return lst;
    }

    @Bean()
    public List<SanPhamChiTiet> getLSTSPCT() {
        List<SanPhamChiTiet> lst = new ArrayList<>();


        lst.add(new SanPhamChiTiet(1, "SPCT1", getLSTKT().get(0), getLSTMS().get(0), getLSTSP().get(0), 100, 100000, true));
        lst.add(new SanPhamChiTiet(2, "SPCT2", getLSTKT().get(1), getLSTMS().get(1), getLSTSP().get(1), 200, 200000, true));
        lst.add(new SanPhamChiTiet(3, "SPCT3", getLSTKT().get(2), getLSTMS().get(2), getLSTSP().get(2), 300, 300000, true));
        return lst;
    }

    @Bean()
    public List<HoaDon> getLSTHD() {
        List<HoaDon> lst = new ArrayList<>();
        lst.add(new HoaDon(1, getLSTNV().get(0), getLSTKH().get(0), new Date(), false));
        lst.add(new HoaDon(2, getLSTNV().get(1), getLSTKH().get(1), new Date(), false));
        lst.add(new HoaDon(3, getLSTNV().get(2), getLSTKH().get(2), new Date(), false));

        return lst;
    }

    @Bean()
    public List<HoaDonChiTiet> getLSTHDCT() {
        List<HoaDonChiTiet> lst = new ArrayList<>();
        lst.add(new HoaDonChiTiet(1, getLSTHD().get(0), getLSTSPCT().get(0), 1, getLSTSPCT().get(0).getDonGia(), true));
        lst.add(new HoaDonChiTiet(1, getLSTHD().get(1), getLSTSPCT().get(1), 2, getLSTSPCT().get(1).getDonGia(), true));
        lst.add(new HoaDonChiTiet(1, getLSTHD().get(2), getLSTSPCT().get(2), 3, getLSTSPCT().get(2).getDonGia(), true));
        return lst;
    }


}
