package com.poly.assignment1.repository.dto.mauSac;

import jakarta.validation.constraints.NotBlank;
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
    private Integer trangThai;
}
