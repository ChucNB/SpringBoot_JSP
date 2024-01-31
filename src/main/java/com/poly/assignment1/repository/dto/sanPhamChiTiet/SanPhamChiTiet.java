package com.poly.assignment1.repository.dto.sanPhamChiTiet;

import com.poly.assignment1.repository.dto.kichThuoc.KichThuoc;
import com.poly.assignment1.repository.dto.mauSac.MauSac;
import com.poly.assignment1.repository.dto.sanPham.SanPham;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamChiTiet {
    @NotNull(message = "Vui lòng không để trống trường này !")
    private Integer id;
    @NotBlank(message = "Vui lòng không để trống trường này !")
    private String maSPCT;
    private KichThuoc kichThuoc;
    private MauSac mauSac;
    private SanPham sanPham;
    @NotNull(message = "Vui lòng không để trống trường này !")
    @Positive (message = "Vui lòng chỉ nhập số dương !")
    private Integer soLuong,donGia;
    private Boolean trangThai;

}
