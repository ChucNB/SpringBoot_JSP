package com.poly.assignment1.controller;

import com.poly.assignment1.entities.SanPham;
import com.poly.assignment1.entities.SanPhamChiTiet;
import com.poly.assignment1.repository.KichThuocRepository;
import com.poly.assignment1.repository.MauSacRepository;
import com.poly.assignment1.repository.SanPhamChiTietRepository;
import com.poly.assignment1.repository.SanPhamRepository;
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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/quan-ly-san-pham-chi-tiet/{idSP}")
public class SanPhamChiTietController {
    @Autowired
    private SanPhamChiTietRepository rpSPCT;
    @Autowired
    private SanPhamRepository rpSP;
    @Autowired
    private MauSacRepository rpMS;
    @Autowired
    private KichThuocRepository rpKT;


    @ModelAttribute("lstMS")
    private List<com.poly.assignment1.entities.MauSac> getLSTMS() {
        return rpMS.findByTrangThai(1);
    }

    @ModelAttribute("lstKT")
    private List<com.poly.assignment1.entities.KichThuoc> getLSTKT() {
        return rpKT.findByTrangThai(1);
    }

    @GetMapping("create")
    public String create(
            Model model,
            @PathVariable(name = "idSP") SanPham idSP

    ) {
        var ctsp = new SanPhamChiTiet();
        ctsp.setSanPham(idSP);
        model.addAttribute("obj", ctsp);
        model.addAttribute("admin/quan-ly-san-pham-chi-tiet/create");
        return "index";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute(name = "obj") com.poly.assignment1.repository.dto.sanPhamChiTiet.SanPhamChiTiet obj,
            BindingResult result,
            @PathVariable(name = "idSP") Integer idSP,
            Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
            com.poly.assignment1.repository.dto.sanPham.SanPham sp = new com.poly.assignment1.repository.dto.sanPham.SanPham();
            sp.setId(idSP);
            obj.setSanPham(sp);
            model.addAttribute("admin/quan-ly-san-pham-chi-tiet/create");
            return "index";
        } else {
            var spctEntity = new SanPhamChiTiet();
            spctEntity.setMaSPCT(obj.getMaSPCT());
            spctEntity.setSanPham(rpSP.findById(idSP).orElse(null));
            spctEntity.setSoLuong(obj.getSoLuong());
            spctEntity.setMauSac(rpMS.findById(obj.getMauSac().getId()).orElse(null));
            spctEntity.setTrangThai(obj.getTrangThai());
            spctEntity.setKichThuoc(rpKT.findById(obj.getKichThuoc().getId()).orElse(null));
            spctEntity.setDonGia(obj.getDonGia());


            rpSPCT.save(spctEntity);
            return "redirect:/admin/quan-ly-san-pham-chi-tiet/" + idSP + "/index";
        }
    }

    @GetMapping("index")
    private String index(Model model,
                         @PathVariable(name = "idSP") Integer idSP,
                         @RequestParam Map<String, String> mapParam) {


        int pageIndex = Integer.parseInt(mapParam.getOrDefault("page", "0"));
        int active = Integer.parseInt(mapParam.getOrDefault("active", "-1"));
        String sortOder = mapParam.getOrDefault("sortOrder", "ASC");
        String sortBy = mapParam.get("sortBy");
        //Tạo sort
        Sort sort = Sort.unsorted();
        if (sortBy != null)
            sort = Sort.by(sortOder.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(pageIndex, 5, sort);
        Page<com.poly.assignment1.entities.SanPhamChiTiet> page;
        //All status
        if (active == -1) {
            page = rpSPCT.findBySanPhamId(idSP, pageable);
        } else {
            page = rpSPCT.findBySanPhamIdAndTrangThai(idSP, active, pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("sp", rpSP.findById(idSP).orElse(null));
        model.addAttribute("admin/quan-ly-san-pham-chi-tiet/index");
        return "index";
    }


    @GetMapping("delete/{id}")
    private String delete(
            @PathVariable(name = "id") SanPhamChiTiet spct,
            @PathVariable(name = "idSP") Integer idSP
    ) {
        rpSPCT.delete(spct);
        return "redirect:/admin/quan-ly-san-pham-chi-tiet/" + idSP + "/index";
    }

    @GetMapping("edit/{id}")
    public String edit(
            Model model,
            @PathVariable(name = "id") Integer id,
            @PathVariable(name = "idSP") Integer idSP
    ) {
        com.poly.assignment1.entities.SanPhamChiTiet spct;
        spct = rpSPCT.findById(id).orElse(new SanPhamChiTiet());
        model.addAttribute("obj", spct);
        model.addAttribute("admin/quan-ly-san-pham-chi-tiet/edit");
        return "index";
    }

    //
    @PostMapping("update/{id}")
    private String update(
            @Valid @ModelAttribute(name = "obj") com.poly.assignment1.repository.dto.sanPhamChiTiet.SanPhamChiTiet obj,
            BindingResult result,
            @PathVariable(name = "id") Integer id,
            @PathVariable(name = "idSP") Integer idSP,
            Model model
    ) {
        if (result.hasErrors()) {
            com.poly.assignment1.repository.dto.sanPham.SanPham sp = new com.poly.assignment1.repository.dto.sanPham.SanPham();
            sp.setId(idSP);
            obj.setSanPham(sp);
            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
            model.addAttribute("admin/quan-ly-san-pham-chi-tiet/edit");
            return "index";
        } else {
//            convert dto to entity

            var spctEntity = new SanPhamChiTiet();
            spctEntity.setMaSPCT(obj.getMaSPCT());
            spctEntity.setSanPham(rpSP.findById(idSP).orElse(null));
            spctEntity.setId(obj.getId());
            spctEntity.setSoLuong(obj.getSoLuong());
            spctEntity.setMauSac(rpMS.findById(obj.getMauSac().getId()).orElse(null));
            spctEntity.setTrangThai(obj.getTrangThai());
            spctEntity.setKichThuoc(rpKT.findById(obj.getKichThuoc().getId()).orElse(null));
            spctEntity.setDonGia(obj.getDonGia());


            rpSPCT.save(spctEntity);
            return "redirect:/admin/quan-ly-san-pham-chi-tiet/" + idSP + "/index";
        }
    }
}
