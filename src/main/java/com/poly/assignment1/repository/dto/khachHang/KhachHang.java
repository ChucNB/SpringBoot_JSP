package com.poly.assignment1.repository.dto.khachHang;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KhachHang {

    private Integer id;
    @NotBlank(message = "Không được để trống trường này")
    private String ma, ten;
    @Pattern(regexp = "^0\\d{8}$", message = "Vui lòng nhập chính xác số điện thoại")
    private String sdt;
    @PositiveOrZero(message = "Vui lòng chọn trạng thái")
    private Integer trangThai;
}
