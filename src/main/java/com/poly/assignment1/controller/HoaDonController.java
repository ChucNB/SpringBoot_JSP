package com.poly.assignment1.controller;

import com.poly.assignment1.repository.HoaDonRepository;
import com.poly.assignment1.repository.dto.hoaDon.HoaDon;
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

import java.util.Map;

@Controller
@RequestMapping("admin/quan-ly-hoa-don")
public class HoaDonController {
    @Autowired
    HoaDonRepository rpHD;

    @GetMapping("create")
    public String create(
            Model model
    ) {
        model.addAttribute("obj", new HoaDon());
        model.addAttribute("admin/quan-ly-hoa-don/create");
        return "index";
    }

//    @PostMapping("store")
//    public String store(
//            @Valid @ModelAttribute(name = "obj") HoaDon obj,
//            BindingResult result,
//            Model model) {
//
//        if (result.hasErrors()) {
//            model.addAttribute("admin/quan-ly-hoa-don/create");
//            return "index";
//        } else {
//            var ms = new com.poly.assignment1.entities.HoaDon();
//            ms.setMa(obj.getMa());
//            ms.setTen(obj.getTen());
//            ms.setTrangThai(obj.getTrangThai());
//            // Check if exists
//            var check = rpHD.getByMaAndTen(ms.getMa(), ms.getTen());
//            if (check == null) {
//                rpHD.save(ms);
//                return "redirect:/admin/quan-ly-hoa-don/index";
//            } else {
//                model.addAttribute("admin/quan-ly-hoa-don/create");
//                model.addAttribute("message", "Sản phẩm đã tồn tại");
//                return "index";
//            }
//
//        }
//    }

    @GetMapping("index")
    private String index(
            Model model,
            @RequestParam Map<String, String> mapParam
    ) {

        int pageIndex = Integer.parseInt(mapParam.getOrDefault("page", "0"));
        int active = Integer.parseInt(mapParam.getOrDefault("active", "-1"));
        String sortOder = mapParam.getOrDefault("sortOrder", "ASC");
        String sortBy = mapParam.get("sortBy");
        String searchKey = mapParam.get("searchKey");
        //Tạo sort
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.equals("tongHoaDon"))
            sort = Sort.by(sortOder.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(pageIndex, 5, sort);
        Page<com.poly.assignment1.entities.HoaDon> page = null;
        //All status
        if ("tongHoaDon".equals(sortBy)) {
            if (searchKey == null || searchKey.equals("")) {
                if (active == -1) {
                    Pageable pageableByTongHoaDon = PageRequest.of(pageIndex, 5);
                    if (sortOder.equals("ASC")) {
                        page = rpHD.findAllOrderByTongHoaDonASC(pageableByTongHoaDon);
                    } else {
                        page = rpHD.findAllOrderByTongHoaDonDESC(pageableByTongHoaDon);

                    }
                } else {
                    Pageable pageableByTongHoaDon = PageRequest.of(pageIndex, 5);
                    if (sortOder.equals("ASC")) {
                        page = rpHD.findAllOrderByTongHoaDonASCAndTrangThai(pageableByTongHoaDon, active);
                    } else {
                        page = rpHD.findAllOrderByTongHoaDonDESCAndTrangThai(pageableByTongHoaDon, active);

                    }
                }
            } else {
                searchKey = "%" + searchKey + "%";
                if (active == -1) {
                    page = rpHD.findByKhachHangTenLike(searchKey, pageable);
                } else {
                    page = rpHD.findByKhachHangTenLikeAndTrangThai(searchKey, active, pageable);
                }
            }
        }
        if (!"tongHoaDon".equals(sortBy)) {
            if (searchKey == null || searchKey.equals("")) {
                if (active == -1) {
                    page = rpHD.findAll(pageable);
                } else {
                    page = rpHD.findByTrangThai(active, pageable);
                }
            } else {
                searchKey = "%" + searchKey + "%";
                if (active == -1) {
                    page = rpHD.findByKhachHangTenLike(searchKey, pageable);
                } else {
                    page = rpHD.findByKhachHangTenLikeAndTrangThai(searchKey, active, pageable);
                }
            }
        }


        model.addAttribute("page", page);
        model.addAttribute("admin/quan-ly-hoa-don/index");
        return "index";
    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable(name = "id") Integer id) {
        rpHD.deleteById(id);
        return "redirect:/admin/quan-ly-hoa-don/index";
    }

    @GetMapping("edit/{id}")
    private String edit(
            Model model,
            @PathVariable("id") com.poly.assignment1.entities.HoaDon ms
    ) {
        model.addAttribute("obj", ms);
        model.addAttribute("admin/quan-ly-hoa-don/edit");
        return "index";
    }

//    @PostMapping("update/{id}")
//    private String update(
//            @Valid @ModelAttribute(name = "obj") HoaDon obj,
//            BindingResult result,
//            @PathVariable Integer id,
//            Model model
//    ) {
//        if (result.hasErrors()) {
//            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
//            model.addAttribute("admin/quan-ly-hoa-don/edit");
//            return "index";
//        } else {
//
//            var ms = new com.poly.assignment1.entities.HoaDon();
//            ms.setId(id);
//            ms.setMa(obj.getMa());
//            ms.setTen(obj.getTen());
//            ms.setTrangThai(obj.getTrangThai());
//
//            rpHD.save(ms);
//            return "redirect:/admin/quan-ly-hoa-don/index";
//        }
//    }
}
