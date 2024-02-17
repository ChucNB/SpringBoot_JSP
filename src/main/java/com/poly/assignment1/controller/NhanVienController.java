package com.poly.assignment1.controller;

import com.poly.assignment1.repository.NhanVienRepository;
import com.poly.assignment1.repository.dto.nhanVien.NhanVien;
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
@RequestMapping("admin/quan-ly-nhan-vien")
public class NhanVienController {
    @Autowired
    NhanVienRepository rpNV;

    @GetMapping("create")
    public String create(
            Model model
    ) {
        model.addAttribute("obj", new NhanVien());
        model.addAttribute("admin/quan-ly-nhan-vien/create");
        return "index";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute(name = "obj") NhanVien obj,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("admin/quan-ly-nhan-vien/create");
            return "index";
        } else {
            var nv = new com.poly.assignment1.entities.NhanVien();
            nv.setMa(obj.getMa());
            nv.setTen(obj.getTen());
            nv.setTenDangNhap(obj.getTenDangNhap());
            nv.setMatKhau(obj.getMatKhau());
            nv.setTrangThai(obj.getTrangThai());
            // Check if exists
            var check = rpNV.getByMa(nv.getMa());
            if (check == null) {
                rpNV.save(nv);
                return "redirect:/admin/quan-ly-nhan-vien/index";
            } else {
                model.addAttribute("admin/quan-ly-nhan-vien/create");
                model.addAttribute("message", "Mã nhân viên đã tồn tại");
                return "index";
            }

        }
    }

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
        if (sortBy != null)
            sort = Sort.by(sortOder.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(pageIndex, 5, sort);
        Page<com.poly.assignment1.entities.NhanVien> page;
        //All status
        if (searchKey == null || searchKey.equals("")) {
            if (active == -1) {
                page = rpNV.findAll(pageable);
            } else {
                page = rpNV.findByTrangThai(active, pageable);
            }
        } else {
            searchKey = "%" + searchKey + "%";

            if (active == -1) {
                page = rpNV.findByTenLike(searchKey, pageable);
            } else {
                page = rpNV.findByTenLikeAndTrangThai(searchKey, active, pageable);
            }
        }

        model.addAttribute("page", page);
        model.addAttribute("admin/quan-ly-nhan-vien/index");
        return "index";
    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable(name = "id") Integer id) {
        rpNV.deleteById(id);
        return "redirect:/admin/quan-ly-nhan-vien/index";
    }

    @GetMapping("edit/{id}")
    private String edit(
            Model model,
            @PathVariable("id") com.poly.assignment1.entities.NhanVien nv
    ) {
        model.addAttribute("obj", nv);
        model.addAttribute("admin/quan-ly-nhan-vien/edit");
        return "index";
    }

    @PostMapping("update/{id}")
    private String update(
            @Valid @ModelAttribute(name = "obj") NhanVien obj,
            BindingResult result,
            @PathVariable Integer id,
            Model model
    ) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
            model.addAttribute("admin/quan-ly-nhan-vien/edit");
            return "index";
        } else {

            var nv = new com.poly.assignment1.entities.NhanVien();
            nv.setId(id);
            nv.setMa(obj.getMa());
            nv.setTen(obj.getTen());
            nv.setTenDangNhap(obj.getTenDangNhap());
            nv.setMatKhau(obj.getMatKhau());
            nv.setTrangThai(obj.getTrangThai());

            rpNV.save(nv);
            return "redirect:/admin/quan-ly-nhan-vien/index";
        }
    }
}
