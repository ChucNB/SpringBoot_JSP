package com.poly.assignment1.controller;

import com.poly.assignment1.repository.dto.kichThuoc.KichThuoc;
import com.poly.assignment1.repository.dto.mauSac.MauSac;
import com.poly.assignment1.repository.dto.sanPham.SanPham;
import com.poly.assignment1.repository.dto.sanPhamChiTiet.SanPhamChiTiet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/quan-ly-san-pham-chi-tiet/{idSP}")
public class ChiTietSanPhamController {
    @Autowired
    private List<SanPhamChiTiet> lst;
    @Autowired
    private List<MauSac> lstMS;
    @Autowired
    private List<KichThuoc> lstKT;
    @Autowired
    private List<SanPham> lstSP;

    @ModelAttribute("lstMS")
    private List<MauSac> getLSTMS() {
        return lstMS;
    }

    @ModelAttribute("lstKT")
    private List<KichThuoc> getLSTKT() {
        return lstKT;
    }

    @GetMapping("create")
    public String create(
            Model model,
            @PathVariable(name = "idSP") Integer idSP
    ) {
        var ctsp = new SanPhamChiTiet();
        ctsp.setSanPham(lstSP.stream().filter(sp -> sp.getId() == idSP).findAny().orElse(null));
        model.addAttribute("obj", ctsp);
        model.addAttribute("admin/quan-ly-san-pham-chi-tiet/create");
        return "index";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute(name = "obj") SanPhamChiTiet obj,
            BindingResult result,
            @PathVariable(name = "idSP") Integer idSP,
            Model model) {

        if (result.hasErrors()) {
            var sp = new SanPham();
            sp = lstSP.stream().filter(p -> p.getId() == idSP).findAny().orElse(null);
            obj.setSanPham(sp);
            model.addAttribute("admin/quan-ly-san-pham-chi-tiet/create");
            return "index";
        } else {
            var sp = new SanPham();
            var ms = new MauSac();
            var kt = new KichThuoc();

            sp = lstSP.stream().filter(p -> p.getId() == idSP).findAny().orElse(null);
            ms = lstMS.stream().filter(c -> c.getId() == obj.getMauSac().getId()).findAny().orElse(null);
            kt = lstKT.stream().filter(s -> s.getId() == obj.getKichThuoc().getId()).findAny().orElse(null);


            obj.setSanPham(sp);
            obj.setMauSac(ms);
            obj.setKichThuoc(kt);
            lst.add(obj);
            return "redirect:/admin/quan-ly-san-pham-chi-tiet/" + idSP + "/index";
        }
    }

    @GetMapping("index")
    private String index(Model model,
                         @PathVariable(name = "idSP") Integer idSP) {
        model.addAttribute("sp",
                lstSP.stream().filter(sp -> sp.getId() == idSP).findAny().orElse(null));
        model.addAttribute("lst",
                lst.stream().filter(ctsp -> ctsp.getSanPham().getId().intValue() == idSP.intValue()).collect(Collectors.toList()));
        model.addAttribute("admin/quan-ly-san-pham-chi-tiet/index");
        return "index";
    }

    @GetMapping("delete/{id}")
    private String delete(
            @PathVariable(name = "id") Integer id,
            @PathVariable(name = "idSP") Integer idSP
    ) {
        lst.removeIf(item -> item.getId() == id);
        return "redirect:/admin/quan-ly-san-pham-chi-tiet/" + idSP + "/index";
    }

    @GetMapping("edit/{id}")
    public String edit(
            Model model,
            @PathVariable(name = "id") Integer id,
            @PathVariable(name = "idSP") Integer idSP
    ) {
        SanPhamChiTiet ctsp = new SanPhamChiTiet();
        ctsp = lst.stream().filter(pd -> pd.getId().intValue() == id.intValue()).findAny().orElse(null);
        model.addAttribute("obj", ctsp);
        model.addAttribute("admin/quan-ly-san-pham-chi-tiet/edit");
        return "index";
    }

    @PostMapping("update/{id}")
    private String update(
            @Valid @ModelAttribute(name = "obj") SanPhamChiTiet obj,
            BindingResult result,
            @PathVariable(name = "id") Integer id,
            @PathVariable(name = "idSP") Integer idSP,
            Model model
    ) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(c -> System.out.println(c.getDefaultMessage()));
            model.addAttribute("admin/quan-ly-san-pham-chi-tiet/edit");
            return "index";
        } else {
            lst = lst.stream().map(o -> {
                if (o.getId() == id) {
                    var sp = new SanPham();
                    var ms = new MauSac();
                    var kt = new KichThuoc();

                    sp = lstSP.stream().filter(p -> p.getId() == idSP).findAny().orElse(null);
                    ms = lstMS.stream().filter(c -> c.getId() == obj.getMauSac().getId()).findAny().orElse(null);
                    kt = lstKT.stream().filter(s -> s.getId() == obj.getKichThuoc().getId()).findAny().orElse(null);


                    obj.setSanPham(sp);
                    obj.setMauSac(ms);
                    obj.setKichThuoc(kt);
                    return obj;
                   
                } else
                    return o;
            }).collect(Collectors.toList());
            return "redirect:/admin/quan-ly-san-pham-chi-tiet/" + idSP + "/index";
        }
    }
}
