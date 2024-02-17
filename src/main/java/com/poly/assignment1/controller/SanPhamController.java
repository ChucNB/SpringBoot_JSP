package com.poly.assignment1.controller;

import com.poly.assignment1.repository.SanPhamRepository;
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

import java.util.Map;

@Controller
@RequestMapping("admin/quan-ly-san-pham")
public class SanPhamController {
    @Autowired
    SanPhamRepository rpSP;

    @GetMapping("create")
    public String create(
            Model model
    ) {
        model.addAttribute("obj", new SanPham());
        model.addAttribute("admin/quan-ly-san-pham/create");
        return "index";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute(name = "obj") SanPham obj,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("admin/quan-ly-san-pham/create");
            return "index";
        } else {
            var ms = new com.poly.assignment1.entities.SanPham();
            ms.setMa(obj.getMa());
            ms.setTen(obj.getTen());
            ms.setTrangThai(obj.getTrangThai());
            // Check if exists
            var check = rpSP.getByMaAndTen(ms.getMa(), ms.getTen());
            if (check == null) {
                rpSP.save(ms);
                return "redirect:/admin/quan-ly-san-pham/index";
            } else {
                model.addAttribute("admin/quan-ly-san-pham/create");
                model.addAttribute("message", "Sản phẩm đã tồn tại");
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
        Page<com.poly.assignment1.entities.SanPham> page;
        //All status
        if (searchKey == null || searchKey.equals("")) {
            if (active == -1) {
                page = rpSP.findAll(pageable);
            } else {
                page = rpSP.findByTrangThai(active, pageable);
            }
        } else {
            searchKey = "%" + searchKey + "%";
            if (active == -1) {
                page = rpSP.findByTenLike(searchKey, pageable);
            } else {
                page = rpSP.findByTenLikeAndTrangThai(searchKey, active, pageable);
            }
        }

        model.addAttribute("page", page);
        model.addAttribute("admin/quan-ly-san-pham/index");
        return "index";
    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable(name = "id") Integer id) {
        rpSP.deleteById(id);
        return "redirect:/admin/quan-ly-san-pham/index";
    }

    @GetMapping("edit/{id}")
    private String edit(
            Model model,
            @PathVariable("id") com.poly.assignment1.entities.SanPham ms
    ) {
        model.addAttribute("obj", ms);
        model.addAttribute("admin/quan-ly-san-pham/edit");
        return "index";
    }

    @PostMapping("update/{id}")
    private String update(
            @Valid @ModelAttribute(name = "obj") SanPham obj,
            BindingResult result,
            @PathVariable Integer id,
            Model model
    ) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
            model.addAttribute("admin/quan-ly-san-pham/edit");
            return "index";
        } else {

            var ms = new com.poly.assignment1.entities.SanPham();
            ms.setId(id);
            ms.setMa(obj.getMa());
            ms.setTen(obj.getTen());
            ms.setTrangThai(obj.getTrangThai());

            rpSP.save(ms);
            return "redirect:/admin/quan-ly-san-pham/index";
        }
    }
}
