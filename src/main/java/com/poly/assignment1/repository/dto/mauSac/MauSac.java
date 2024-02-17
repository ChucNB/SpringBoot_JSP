package com.poly.assignment1.repository.dto.mauSac;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MauSac {

    private Integer id;
    @NotBlank(message = "Không được để trống trường này")
    private String ma, ten;
    @PositiveOrZero(message = "Vui lòng chọn trạng thái")
    private Integer trangThai;
}
