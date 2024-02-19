package com.poly.assignment1.controller;

import com.poly.assignment1.entities.*;
import com.poly.assignment1.repository.*;
import com.poly.assignment1.repository.dto.report.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("thong-ke")
public class ThongKeController {

    @Autowired
    SanPhamRepository rpSP;
    @Autowired
    SanPhamChiTietRepository rpSPCT;
    @Autowired
    HoaDonRepository rpHD;
    @Autowired
    HoaDonChiTietRepository rpHDCT;
    @Autowired
    KhachHangRepository rpKH;
    @Autowired
    NhanVienRepository rpNV;


    @GetMapping(value = {"doanh-thu"})
    public String banHang(Model model
    ) {

        model.addAttribute("thong-ke/index");
        Calendar namNay = Calendar.getInstance();
        Calendar namTruoc = Calendar.getInstance();
        namTruoc.add(Calendar.YEAR, -1);

        Report reportNamTruoc = new Report();
        reportNamTruoc.setName("");

        Report reportNamNay = new Report();


        return "index";

    }


}
