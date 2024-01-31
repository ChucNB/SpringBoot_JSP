package com.poly.assignment1.controller;

import com.poly.assignment1.repository.dto.nhanVien.NhanVien;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/quan-ly-nhan-vien")
public class NhanVienController {
    @Autowired
    private List<NhanVien> lst;

    @GetMapping("create")
    public String create(
            Model model
            ){
        model.addAttribute("obj",new NhanVien());
        model.addAttribute("admin/quan-ly-nhan-vien/create");
        return "index";
    }
    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute(name = "obj") NhanVien obj,
            BindingResult result,
            Model model){
        if (result.hasErrors()){
            model.addAttribute("admin/quan-ly-nhan-vien/create");
            return "index";
        }else{
            lst.add(obj);
            model.addAttribute("lst",lst);
            model.addAttribute("admin/quan-ly-nhan-vien/index");
            return "index";
        }
    }
    @GetMapping("index")
    private String index(Model model){
        model.addAttribute("lst",lst);
        model.addAttribute("admin/quan-ly-nhan-vien/index");
        return "index";
    }
    @GetMapping("delete/{id}")
    private String delete(@PathVariable(name = "id") Integer id){
            lst.removeIf(item->item.getId()==id);
            return "redirect:/admin/quan-ly-nhan-vien/index";
    }
    @GetMapping("edit/{id}")
    private String edit(
            Model model,
            @PathVariable int id
    ){
        model.addAttribute("obj", lst.stream().filter(obj->obj.getId()==id).findAny().orElse(null));
        model.addAttribute("admin/quan-ly-nhan-vien/edit");
        return "index";
    }
    @PostMapping ("update/{id}")
    private String update(
            @Valid @ModelAttribute(name="obj") NhanVien obj,
            BindingResult result,
            @PathVariable Integer id,
            Model model
    ){
        if(result.hasErrors()){
            result.getAllErrors().forEach(c-> System.out.println(c.getDefaultMessage()));
            model.addAttribute("admin/quan-ly-nhan-vien/edit");
            return "index";
        }else{
            lst=lst.stream().map(o->{
                if(o.getId()==id){
                    o=obj;
                    return o;
                }else
                    return o;
            }).collect(Collectors.toList());
            return "redirect:/admin/quan-ly-nhan-vien/index";
        }
    }
}
