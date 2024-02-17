package com.poly.assignment1.controller;

import com.poly.assignment1.repository.MauSacRepository;
import com.poly.assignment1.repository.dto.mauSac.MauSac;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("admin/quan-ly-mau-sac")
public class MauSacController {
    @Autowired
    MauSacRepository rpMS;

    @GetMapping("create")
    public String create(
            Model model
    ) {
        model.addAttribute("obj", new MauSac());
        model.addAttribute("admin/quan-ly-mau-sac/create");
        return "index";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute(name = "obj") MauSac obj,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("admin/quan-ly-mau-sac/create");
            return "index";
        } else {
            var ms = new com.poly.assignment1.entities.MauSac();
            ms.setMa(obj.getMa());
            ms.setTen(obj.getTen());
            ms.setTrangThai(obj.getTrangThai());
            // Check if exists
            var check = rpMS.getByMaAndTen(ms.getMa(), ms.getTen());
            if (check == null) {
                rpMS.save(ms);
                return "redirect:/admin/quan-ly-mau-sac/index";
            } else {
                model.addAttribute("admin/quan-ly-mau-sac/create");
                model.addAttribute("message", "Màu sắc đã tồn tại");
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
        Page<com.poly.assignment1.entities.MauSac> page;
        //All status
        if (searchKey == null || searchKey.equals("")) {
            if (active == -1) {
                page = rpMS.findAll(pageable);
            } else {
                page = rpMS.findByTrangThai(active, pageable);
            }
        } else {
            searchKey = "%" + searchKey + "%";
            if (active == -1) {
                page = rpMS.findByTenLike(searchKey, pageable);
            } else {
                page = rpMS.findByTenLikeAndTrangThai(searchKey, active, pageable);
            }
        }

        model.addAttribute("page", page);
        model.addAttribute("admin/quan-ly-mau-sac/index");
        return "index";
    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable(name = "id") Integer id) {
        rpMS.deleteById(id);
        return "redirect:/admin/quan-ly-mau-sac/index";
    }

    @GetMapping("edit/{id}")
    private String edit(
            Model model,
            @PathVariable("id") com.poly.assignment1.entities.MauSac ms
    ) {
        model.addAttribute("obj", ms);
        model.addAttribute("admin/quan-ly-mau-sac/edit");
        return "index";
    }

    @PostMapping("update/{id}")
    private String update(
            @Valid @ModelAttribute(name = "obj") MauSac obj,
            BindingResult result,
            @PathVariable Integer id,
            Model model
    ) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
            model.addAttribute("admin/quan-ly-mau-sac/edit");
            return "index";
        } else {

            var ms = new com.poly.assignment1.entities.MauSac();
            ms.setId(id);
            ms.setMa(obj.getMa());
            ms.setTen(obj.getTen());
            ms.setTrangThai(obj.getTrangThai());

            rpMS.save(ms);
            return "redirect:/admin/quan-ly-mau-sac/index";
        }
    }
}
