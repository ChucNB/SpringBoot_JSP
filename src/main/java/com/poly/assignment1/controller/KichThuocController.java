package com.poly.assignment1.controller;

import com.poly.assignment1.repository.KichThuocRepository;
import com.poly.assignment1.repository.dto.kichThuoc.KichThuoc;
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
@RequestMapping("admin/quan-ly-kich-thuoc")
public class KichThuocController {
    @Autowired
    KichThuocRepository rpKT;

    @GetMapping("create")
    public String create(
            Model model
    ) {
        model.addAttribute("obj", new KichThuoc());
        model.addAttribute("admin/quan-ly-kich-thuoc/create");
        return "index";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute(name = "obj") KichThuoc obj,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("admin/quan-ly-kich-thuoc/create");
            return "index";
        } else {
            var ms = new com.poly.assignment1.entities.KichThuoc();
            ms.setMa(obj.getMa());
            ms.setTen(obj.getTen());
            ms.setTrangThai(obj.getTrangThai());
            // Check if exists
            var check = rpKT.getByMaAndTen(ms.getMa(), ms.getTen());
            if (check == null) {
                rpKT.save(ms);
                return "redirect:/admin/quan-ly-kich-thuoc/index";
            } else {
                model.addAttribute("admin/quan-ly-kich-thuoc/create");
                model.addAttribute("message", "Kích thước đã tồn tại");
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
        Page<com.poly.assignment1.entities.KichThuoc> page;
        //All status
        if (searchKey == null || searchKey.equals("")) {
            if (active == -1) {
                page = rpKT.findAll(pageable);
            } else {
                page = rpKT.findByTrangThai(active, pageable);
            }
        } else {
            if (active == -1) {
                page = rpKT.findByTen(searchKey, pageable);
            } else {
                page = rpKT.findByTenAndTrangThai(searchKey, active, pageable);
            }
        }

        model.addAttribute("page", page);
        model.addAttribute("admin/quan-ly-kich-thuoc/index");
        return "index";
    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable(name = "id") Integer id) {
        rpKT.deleteById(id);
        return "redirect:/admin/quan-ly-kich-thuoc/index";
    }

    @GetMapping("edit/{id}")
    private String edit(
            Model model,
            @PathVariable("id") com.poly.assignment1.entities.KichThuoc ms
    ) {
        model.addAttribute("obj", ms);
        model.addAttribute("admin/quan-ly-kich-thuoc/edit");
        return "index";
    }

    @PostMapping("update/{id}")
    private String update(
            @Valid @ModelAttribute(name = "obj") KichThuoc obj,
            BindingResult result,
            @PathVariable Integer id,
            Model model
    ) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
            model.addAttribute("admin/quan-ly-kich-thuoc/edit");
            return "index";
        } else {

            var ms = new com.poly.assignment1.entities.KichThuoc();
            ms.setId(id);
            ms.setMa(obj.getMa());
            ms.setTen(obj.getTen());
            ms.setTrangThai(obj.getTrangThai());

            rpKT.save(ms);
            return "redirect:/admin/quan-ly-kich-thuoc/index";
        }
    }
}
