package com.poly.assignment1.repository.dto.sanPham;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SanPham {
    @NotNull(message = "Không được để trống trường này")
    private Integer id;
    @NotBlank(message = "Không được để trống trường này")
    private String ma,ten;
    private Boolean trangThai;
}
