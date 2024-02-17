package com.poly.assignment1.repository.dto.sanPham;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SanPham {

    private Integer id;
    @NotBlank(message = "Không được để trống trường này")
    private String ma, ten;
    @PositiveOrZero(message = "Vui lòng chọn trạng thái")
    private Integer trangThai;
}
