package com.poly.assignment1.controller;

import com.poly.assignment1.repository.MauSacRepository;
import com.poly.assignment1.repository.dto.mauSac.MauSac;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            rpMS.save(ms);
            return "redirect:/admin/quan-ly-mau-sac/index";
        }
    }

    @GetMapping("index")
    private String index(
            Model model,
            @RequestParam(name = "page") Optional<Integer> pageParam,
            @RequestParam(name = "active") Optional<Integer> activeParam
    ) {
        int page = pageParam.orElse(0);
        int active = activeParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 10);
        Page<com.poly.assignment1.entities.MauSac> pageData;
        //All status
        if (active == 0) {
            pageData = rpMS.findAll(pageable);
        } else {
            pageData = rpMS.findByTrangThai(active, pageable);
        }

        model.addAttribute("pageData", pageData);
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
            @PathVariable com.poly.assignment1.entities.MauSac ms
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
