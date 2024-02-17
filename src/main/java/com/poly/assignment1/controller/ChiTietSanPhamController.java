package com.poly.assignment1.controller;

import com.poly.assignment1.entities.SanPhamChiTiet;
import com.poly.assignment1.repository.KichThuocRepository;
import com.poly.assignment1.repository.MauSacRepository;
import com.poly.assignment1.repository.SanPhamChiTietRepository;
import com.poly.assignment1.repository.SanPhamRepository;
import com.poly.assignment1.repository.dto.kichThuoc.KichThuoc;
import com.poly.assignment1.repository.dto.mauSac.MauSac;
import com.poly.assignment1.repository.dto.sanPham.SanPham;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/quan-ly-san-pham-chi-tiet/{idSP}")
public class ChiTietSanPhamController {
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
        return rpMS.findAll();
    }

    @ModelAttribute("lstKT")
    private List<com.poly.assignment1.entities.KichThuoc> getLSTKT() {
        return rpKT.findAll();
    }

//    @GetMapping("create")
//    public String create(
//            Model model,
//            @PathVariable(name = "idSP") Integer idSP
//    ) {
//        var ctsp = new SanPhamChiTiet();
//        model.addAttribute("obj", ctsp);
//        model.addAttribute("admin/quan-ly-san-pham-chi-tiet/create");
//        return "index";
//    }
//
//    @PostMapping("store")
//    public String store(
//            @Valid @ModelAttribute(name = "obj") SanPhamChiTiet obj,
//            BindingResult result,
//            @PathVariable(name = "idSP") Integer idSP,
//            Model model) {
//
//        if (result.hasErrors()) {
//            var sp = new SanPham();
//            sp = lstSP.stream().filter(p -> p.getId() == idSP).findAny().orElse(null);
//            obj.setSanPham(sp);
//            model.addAttribute("admin/quan-ly-san-pham-chi-tiet/create");
//            return "index";
//        } else {
//            var sp = new SanPham();
//            var ms = new MauSac();
//            var kt = new KichThuoc();
//
//            sp = lstSP.stream().filter(p -> p.getId() == idSP).findAny().orElse(null);
//            ms = lstMS.stream().filter(c -> c.getId() == obj.getMauSac().getId()).findAny().orElse(null);
//            kt = lstKT.stream().filter(s -> s.getId() == obj.getKichThuoc().getId()).findAny().orElse(null);
//
//
//            obj.setSanPham(sp);
//            obj.setMauSac(ms);
//            obj.setKichThuoc(kt);
//            lst.add(obj);
//            return "redirect:/admin/quan-ly-san-pham-chi-tiet/" + idSP + "/index";
//        }
//    }

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

    //
//    @GetMapping("delete/{id}")
//    private String delete(
//            @PathVariable(name = "id") Integer id,
//            @PathVariable(name = "idSP") Integer idSP
//    ) {
//        lst.removeIf(item -> item.getId() == id);
//        return "redirect:/admin/quan-ly-san-pham-chi-tiet/" + idSP + "/index";
//    }
//
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
//    @PostMapping("update/{id}")
//    private String update(
//            @Valid @ModelAttribute(name = "obj") SanPhamChiTiet obj,
//            BindingResult result,
//            @PathVariable(name = "id") Integer id,
//            @PathVariable(name = "idSP") Integer idSP,
//            Model model
//    ) {
//        if (result.hasErrors()) {
//            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
//            model.addAttribute("admin/quan-ly-san-pham-chi-tiet/edit");
//            return "index";
//        } else {
//            lst = lst.stream().map(o -> {
//                if (o.getId() == id) {
//                    var sp = new SanPham();
//                    var ms = new MauSac();
//                    var kt = new KichThuoc();
//
//                    sp = lstSP.stream().filter(p -> p.getId() == idSP).findAny().orElse(null);
//                    ms = lstMS.stream().filter(c -> c.getId() == obj.getMauSac().getId()).findAny().orElse(null);
//                    kt = lstKT.stream().filter(s -> s.getId() == obj.getKichThuoc().getId()).findAny().orElse(null);
//
//
//                    obj.setSanPham(sp);
//                    obj.setMauSac(ms);
//                    obj.setKichThuoc(kt);
//                    return obj;
//
//                } else
//                    return o;
//            }).collect(Collectors.toList());
//            return "redirect:/admin/quan-ly-san-pham-chi-tiet/" + idSP + "/index";
//        }
//    }
}
