package com.poly.assignment1.controller;

import com.poly.assignment1.entities.*;
import com.poly.assignment1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("ban-hang")
public class BanHangController {

    @Autowired
    SanPhamRepository rpSP;
    @Autowired
    SanPhamChiTietRepository rpSPCT;
    @Autowired
    HoaDonRepository rpHD;
    @Autowired
    HoaDonChiTietRepository rpHDCT;
    @Autowired
    KhachHangRepository rpKH;
    @Autowired
    NhanVienRepository rpNV;

    @ModelAttribute("lstSP")
    private List<SanPham> getSanPhamList() {
        return rpSP.findAllActive();
    }

    @ModelAttribute("lstSPCT")
    private List<SanPhamChiTiet> getSanPhamChiTiets() {
        return rpSPCT.findAllActive();
    }

    @ModelAttribute("lstKH")
    private List<KhachHang> getLstKhachHang() {
        return rpKH.findAllByTrangThaiOrderByTenDesc(1);
    }

    @ModelAttribute("lstNV")
    private List<NhanVien> getLstNhanVien() {
        return rpNV.findAllByTrangThaiOrderByTenDesc(1);
    }

    @ModelAttribute("lstHDC")
    private List<HoaDon> getLstHoaDonCho() {
        return rpHD.findByTrangThaiChuaThanhToan();
    }

    @GetMapping(value = {"index/{idHD}", "index"})
    public String banHang(Model model,
                          @PathVariable(name = "idHD", required = false) HoaDon hd,
                          @RequestParam(name = "findByTenLike", required = false, defaultValue = "") String keyWord
    ) {
        if (!keyWord.trim().equals("")) {
            model.addAttribute("lstSPCTF", rpSPCT.findByTenAndMa("%" + keyWord + "%"));
        } else {
            model.addAttribute("lstSPCTF", rpSPCT.findAllActive());
        }
        if (hd == null) {
            var lstHD = rpHD.findByTrangThaiChuaThanhToan();
            if (lstHD.size() == 0) {
                HoaDon newHD = new HoaDon();
                newHD.setNgayMuaHang(new Date());
                newHD.setTrangThai(0);
                rpHD.save(newHD);
                hd = rpHD.findByTrangThaiChuaThanhToan().get(0);
            } else
                return "redirect:/ban-hang/index/" + rpHD.findByTrangThaiChuaThanhToan().get(0).getId();
        }

        model.addAttribute("hd", hd);

        model.addAttribute("collapse", "class=\"sb-sidenav-toggled\"");
        model.addAttribute("spct", new SanPhamChiTiet());
        model.addAttribute("ban-hang/index");

        System.out.println(hd.getId());
        return "index";

    }


    @GetMapping(value = {"index/{idHD}/thanh-toan"})
    public String thanhToan(
            @PathVariable("idHD") HoaDon hd
    ) {
        hd.setTrangThai(1);
        rpHD.save(hd);

        return "redirect:/ban-hang/index";

    }


    @GetMapping(value = {"index/{idHD}/them-san-pham"})
    public String themSanPham(
            @PathVariable("idHD") HoaDon hd,
            @RequestParam("idSPCT") SanPhamChiTiet spct,
            @RequestParam("soLuong") int soLuong

    ) {
        var hdct = rpHDCT.getBySanPhamChiTietIdAndHoaDonId(spct.getId(), hd.getId());
        if (hdct == null) {
            hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hd);
            hdct.setTrangThai(1);
            hdct.setDonGia(spct.getDonGia());
            hdct.setSoLuong(soLuong);
            hdct.setThoiGian(new Date());
            hdct.setSanPhamChiTiet(spct);
        } else {
            hdct.setSoLuong(hdct.getSoLuong() + soLuong);
        }
        rpHDCT.save(hdct);
        System.out.println("GETOK");

        return "redirect:/ban-hang/index/" + hd.getId();

    }

    @GetMapping("delete-hoa-don-chi-tiet/{id}")
    private String deleteHoaDonChiTiet(
            @PathVariable(name = "id") HoaDonChiTiet hdct
    ) {
        rpHDCT.delete(hdct);
        return "redirect:/ban-hang/index/" + hdct.getHoaDon().getId();
    }

    @GetMapping("delete-hoa-don/{id}")
    private String deleteHoaDon(
            @PathVariable(name = "id") HoaDon hd
    ) {
        rpHD.delete(hd);
        return "redirect:/ban-hang/index";
    }

    @PostMapping("them-hoa-don/store")
    private String taoHoaDon(
            @RequestParam(name = "idKH") int idKH,
            @RequestParam(name = "idNV") int idNV
    ) {
        HoaDon hd = new HoaDon();
        hd.setKhachHang(rpKH.findById(idKH).orElse(null));
        hd.setNhanVien(rpNV.findById(idNV).orElse(null));
        hd.setNgayMuaHang(new Date());
        hd.setTrangThai(0);
        rpHD.save(hd);
        return "redirect:/ban-hang/index";
    }


//    @PostMapping("add")
//    public String addChiTietHoaDon(
//            Model model,
//            @PathVariable(name = "idHD") HoaDon idHD,
//            @ModelAttribute(name = "spct") SanPhamChiTiet spct
//    ) {
//        model.addAttribute("collapse", "class=\"sb-sidenav-toggled\"");
//        model.addAttribute("ban-hang/index");
//        System.out.println(spct);
//        return "index";
//    }

}
