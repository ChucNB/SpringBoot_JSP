package com.poly.assignment1.repository.dto.nhanVien;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NhanVien {
    @NotNull(message = "Không được để trống trường này")
    private Integer id;
    @NotBlank(message = "Không được để trống trường này")
    private String ten,maNV,tenDangNhap;
    @NotBlank(message = "Không được để trống trường này")
    @Pattern(message = "Mật khẩu trong khoảng từ 8-16 kí tự",regexp = "\\w{8,16}")
    private String matKhau;
    private Boolean trangThai;
}
