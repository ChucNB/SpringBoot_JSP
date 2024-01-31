package com.poly.assignment1.repository.dto.khachHang;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KhachHang {
    @NotNull(message = "Không được để trống trường này")
    private Integer id;
    @NotBlank(message = "Không được để trống trường này")
    private String ten, maKH;
    @NotBlank(message = "Không được để trống trường này")
    @Pattern(message = "Vui lòng nhập đúng số điện thoại", regexp = "0\\d{9}")
    private String sdt;
    private Boolean trangThai;
}
