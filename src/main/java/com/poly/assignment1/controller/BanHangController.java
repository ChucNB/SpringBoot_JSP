package com.poly.assignment1.controller;

import com.poly.assignment1.repository.dto.hoaDon.HoaDon;
import com.poly.assignment1.repository.dto.hoaDonChiTiet.HoaDonChiTiet;
import com.poly.assignment1.repository.dto.khachHang.KhachHang;
import com.poly.assignment1.repository.dto.nhanVien.NhanVien;
import com.poly.assignment1.repository.dto.sanPham.SanPham;
import com.poly.assignment1.repository.dto.sanPhamChiTiet.SanPhamChiTiet;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BanHangController {
    @Autowired
    private List<HoaDonChiTiet> lstHDCT;
    @Autowired
    private List<HoaDon> lstHD;
    @Autowired
    private List<KhachHang> lstKH;
    @Autowired
    private List<NhanVien> lstNV;
    @Autowired
    private List<NhanVien> lstCTSP;
    @Autowired
    private List<SanPham> lstSP;

    @GetMapping("/ban-hang/{idHD}")
    public String banHang(Model model,
                          @PathVariable(name = "idHD") int idHD
    ) {
        model.addAttribute("lstHD", lstHD);
        model.addAttribute("hoaDon", lstHD.stream().filter(hd -> hd.getId() == idHD).findAny().orElse(null));
        System.out.println(lstHD.size());
        model.addAttribute("lstHDCT", lstHDCT.stream().
                filter(hdct -> hdct.getHoaDon().getId() == idHD)
                .collect(Collectors.toList())
        );
        model.addAttribute("lstSP", lstSP);
        model.addAttribute("ban-hang/ban-hang");
        return "index";
    }

    @GetMapping("/ban-hang")
    public String banHang(Model model
    ) {
        int idHD = -1;
        model.addAttribute("lstHD", lstHD);
        model.addAttribute("hoaDon", lstHD.stream().filter(hd -> hd.getId() == idHD).findAny().orElse(null));
        System.out.println(lstHD.size());
        model.addAttribute("lstHDCT", lstHDCT.stream().
                filter(hdct -> hdct.getHoaDon().getId() == idHD)
                .collect(Collectors.toList())
        );
        model.addAttribute("lstSP", lstSP);
        model.addAttribute("ban-hang/ban-hang");
        return "index";
    }

    @GetMapping("/ban-hang/them-hoa-don")
    public String themHoaDon(Model model,
                             @Valid @ModelAttribute(name = "obj") HoaDon hd
    ) {
        model.addAttribute("ban-hang/them-hoa-don");
        return "index";
    }


    @PostMapping("/ban-hang/{idHD}/add")
    public String add(
            @ModelAttribute("HDCT") HoaDonChiTiet hdct,
            @PathVariable(name = "idHD") int idHD
    ) {
        lstHDCT.add(hdct);
        return "redirect:/ban-hang/" + idHD;
    }
}
