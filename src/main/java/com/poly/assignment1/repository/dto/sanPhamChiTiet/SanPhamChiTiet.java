package com.poly.assignment1.repository.dto.sanPhamChiTiet;

import com.poly.assignment1.repository.dto.kichThuoc.KichThuoc;
import com.poly.assignment1.repository.dto.mauSac.MauSac;
import com.poly.assignment1.repository.dto.sanPham.SanPham;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamChiTiet {
    private Integer id;
    @NotBlank(message = "Vui lòng không để trống trường này !")
    private String maSPCT;
    private KichThuoc kichThuoc;
    private MauSac mauSac;
    private SanPham sanPham;
    @NotNull(message = "Vui lòng không để trống trường này !")
    @Positive(message = "Vui lòng chỉ nhập số dương !")
    private Integer soLuong;
    @NotNull(message = "Vui lòng không để trống trường này !")
    @Positive(message = "Vui lòng chỉ nhập số dương !")
    private Double donGia;
    @PositiveOrZero(message = "Vui lòng chọn trạng thái")
    private Integer trangThai;

}
