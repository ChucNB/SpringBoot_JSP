package com.poly.assignment1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("home")
    public String home(Model model){
        model.addAttribute("admin/quan-ly-khach-hang/index");
        return "index";
    }
}
