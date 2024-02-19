package com.poly.assignment1.controller;

import com.poly.assignment1.entities.HoaDon;
import com.poly.assignment1.entities.HoaDonChiTiet;
import com.poly.assignment1.entities.SanPham;
import com.poly.assignment1.entities.SanPhamChiTiet;
import com.poly.assignment1.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/quan-ly-hoa-don-chi-tiet/{idHD}")
public class HoaDonChiTietController {
    @Autowired
    HoaDonChiTietRepository rpHDCT;

//    @GetMapping("create")
//    public String create(
//            Model model,
//            @PathVariable(name = "idHD") SanPham idHD
//
//    ) {
//        var ctsp = new SanPhamChiTiet();
//        ctsp.setSanPham(idHD);
//        model.addAttribute("obj", ctsp);
//        model.addAttribute("admin/quan-ly-hoa-don-chi-tiet/create");
//        return "index";
//    }

//    @PostMapping("store")
//    public String store(
//            @Valid @ModelAttribute(name = "obj") com.poly.assignment1.repository.dto.sanPhamChiTiet.SanPhamChiTiet obj,
//            BindingResult result,
//            @PathVariable(name = "idHD") Integer idHD,
//            Model model) {
//
//        if (result.hasErrors()) {
//            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
//            com.poly.assignment1.repository.dto.sanPham.SanPham sp = new com.poly.assignment1.repository.dto.sanPham.SanPham();
//            sp.setId(idHD);
//            obj.setSanPham(sp);
//            model.addAttribute("admin/quan-ly-hoa-don-chi-tiet/create");
//            return "index";
//        } else {
//            var spctEntity = new SanPhamChiTiet();
//            spctEntity.setMaSPCT(obj.getMaSPCT());
//            spctEntity.setSanPham(rpSP.findById(idHD).orElse(null));
//            spctEntity.setSoLuong(obj.getSoLuong());
//            spctEntity.setMauSac(rpMS.findById(obj.getMauSac().getId()).orElse(null));
//            spctEntity.setTrangThai(obj.getTrangThai());
//            spctEntity.setKichThuoc(rpKT.findById(obj.getKichThuoc().getId()).orElse(null));
//            spctEntity.setDonGia(obj.getDonGia());
//
//
//            rpHDCT.save(spctEntity);
//            return "redirect:/admin/quan-ly-hoa-don-chi-tiet/" + idHD + "/index";
//        }
//    }

    @GetMapping("index")
    private String index(Model model,
                         @PathVariable(name = "idHD") HoaDon hd) {
        model.addAttribute("hd", hd);

        Collection<HoaDonChiTiet> lstHDCT = rpHDCT.findByHoaDonId(hd.getId());

        model.addAttribute("lstHDCT", lstHDCT);
        model.addAttribute("admin/quan-ly-hoa-don-chi-tiet/index");
        return "index";
    }


//    @GetMapping("delete/{id}")
//    private String delete(
//            @PathVariable(name = "id") SanPhamChiTiet spct,
//            @PathVariable(name = "idHD") Integer idHD
//    ) {
//        rpHDCT.delete(spct);
//        return "redirect:/admin/quan-ly-hoa-don-chi-tiet/" + idHD + "/index";
//    }
//
//    @GetMapping("edit/{id}")
//    public String edit(
//            Model model,
//            @PathVariable(name = "id") Integer id,
//            @PathVariable(name = "idHD") Integer idHD
//    ) {
//        SanPhamChiTiet spct;
//        spct = rpHDCT.findById(id).orElse(new SanPhamChiTiet());
//        model.addAttribute("obj", spct);
//        model.addAttribute("admin/quan-ly-hoa-don-chi-tiet/edit");
//        return "index";
//    }

    //
//    @PostMapping("update/{id}")
//    private String update(
//            @Valid @ModelAttribute(name = "obj") com.poly.assignment1.repository.dto.sanPhamChiTiet.SanPhamChiTiet obj,
//            BindingResult result,
//            @PathVariable(name = "id") Integer id,
//            @PathVariable(name = "idHD") Integer idHD,
//            Model model
//    ) {
//        if (result.hasErrors()) {
//            com.poly.assignment1.repository.dto.sanPham.SanPham sp = new com.poly.assignment1.repository.dto.sanPham.SanPham();
//            sp.setId(idHD);
//            obj.setSanPham(sp);
//            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
//            model.addAttribute("admin/quan-ly-hoa-don-chi-tiet/edit");
//            return "index";
//        } else {
////            convert dto to entity
//
//            var spctEntity = new SanPhamChiTiet();
//            spctEntity.setMaSPCT(obj.getMaSPCT());
//            spctEntity.setSanPham(rpSP.findById(idHD).orElse(null));
//            spctEntity.setId(obj.getId());
//            spctEntity.setSoLuong(obj.getSoLuong());
//            spctEntity.setMauSac(rpMS.findById(obj.getMauSac().getId()).orElse(null));
//            spctEntity.setTrangThai(obj.getTrangThai());
//            spctEntity.setKichThuoc(rpKT.findById(obj.getKichThuoc().getId()).orElse(null));
//            spctEntity.setDonGia(obj.getDonGia());
//
//
//            rpHDCT.save(spctEntity);
//            return "redirect:/admin/quan-ly-hoa-don-chi-tiet/" + idHD + "/index";
//        }
//    }
}
