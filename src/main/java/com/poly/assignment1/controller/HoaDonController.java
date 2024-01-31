package com.poly.assignment1.controller;

import com.poly.assignment1.repository.dto.hoaDon.HoaDon;
import com.poly.assignment1.repository.dto.khachHang.KhachHang;
import com.poly.assignment1.repository.dto.kichThuoc.KichThuoc;
import com.poly.assignment1.repository.dto.nhanVien.NhanVien;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("hoa-don")
public class HoaDonController {
    @Autowired
    List<HoaDon> lst;
    @Autowired
    List<KhachHang> lstKH;
    @Autowired
    List<NhanVien> lstNV;

    @ModelAttribute("lstKH")
    List<KhachHang> getLSTKH() {
        return lstKH;
    }

    @ModelAttribute("lstNV")
    List<NhanVien> getLSTNV() {
        return lstNV;
    }

    @GetMapping("create")
    public String themHoaDon(Model model) {
        model.addAttribute("obj", new HoaDon());
        model.addAttribute("hoa-don/create");
        return "index";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute(name = "obj") HoaDon obj,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(System.out::println);
            model.addAttribute("hoa-don/create");
            return "index";
        } else {
            obj.setKhachHang(lstKH.stream().filter(kh -> kh.getId() == obj.getKhachHang().getId()).findAny().orElse(null));
            obj.setNhanVien(lstNV.stream().filter(nv -> nv.getId() == obj.getNhanVien().getId()).findAny().orElse(null));
            obj.setNgayMuaHang(new Date());
            lst.add(obj);
            model.addAttribute("lst", lst);
            model.addAttribute("hoa-don/index");
            return "index";
        }
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("lst", lst);
        model.addAttribute("hoa-don/index");
        return "index";
    }

}
