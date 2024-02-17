package com.poly.assignment1.controller;

import com.poly.assignment1.repository.KhachHangRepository;
import com.poly.assignment1.repository.dto.khachHang.KhachHang;
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
@RequestMapping("admin/quan-ly-khach-hang")
public class KhachHangController {
    @Autowired
    KhachHangRepository rpKH;

    @GetMapping("create")
    public String create(
            Model model
    ) {
        model.addAttribute("obj", new KhachHang());
        model.addAttribute("admin/quan-ly-khach-hang/create");
        return "index";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute(name = "obj") KhachHang obj,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("admin/quan-ly-khach-hang/create");
            return "index";
        } else {
            var ms = new com.poly.assignment1.entities.KhachHang();
            ms.setMa(obj.getMa());
            ms.setTen(obj.getTen());
            ms.setTrangThai(obj.getTrangThai());
            // Check if exists
            var check = rpKH.getByMaAndTen(ms.getMa(), ms.getTen());
            if (check == null) {
                rpKH.save(ms);
                return "redirect:/admin/quan-ly-khach-hang/index";
            } else {
                model.addAttribute("admin/quan-ly-khach-hang/create");
                model.addAttribute("message", "Khách hàng đã tồn tại");
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
        Page<com.poly.assignment1.entities.KhachHang> page;
        //All status
        if (searchKey == null || searchKey.equals("")) {
            if (active == -1) {
                page = rpKH.findAll(pageable);
            } else {
                page = rpKH.findByTrangThai(active, pageable);
            }
        } else {
            searchKey = "%" + searchKey + "%";
            if (active == -1) {
                page = rpKH.findByTenLike(searchKey, pageable);
            } else {
                page = rpKH.findByTenLikeAndTrangThai(searchKey, active, pageable);
            }
        }

        model.addAttribute("page", page);
        model.addAttribute("admin/quan-ly-khach-hang/index");
        return "index";
    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable(name = "id") Integer id) {
        rpKH.deleteById(id);
        return "redirect:/admin/quan-ly-khach-hang/index";
    }

    @GetMapping("edit/{id}")
    private String edit(
            Model model,
            @PathVariable("id") com.poly.assignment1.entities.KhachHang ms
    ) {
        model.addAttribute("obj", ms);
        model.addAttribute("admin/quan-ly-khach-hang/edit");
        return "index";
    }

    @PostMapping("update/{id}")
    private String update(
            @Valid @ModelAttribute(name = "obj") KhachHang obj,
            BindingResult result,
            @PathVariable Integer id,
            Model model
    ) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
            model.addAttribute("admin/quan-ly-khach-hang/edit");
            return "index";
        } else {

            var ms = new com.poly.assignment1.entities.KhachHang();
            ms.setId(id);
            ms.setMa(obj.getMa());
            ms.setTen(obj.getTen());
            ms.setTrangThai(obj.getTrangThai());

            rpKH.save(ms);
            return "redirect:/admin/quan-ly-khach-hang/index";
        }
    }
}
