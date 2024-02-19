package com.poly.assignment1.repository.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Report {
    Map<String, Long> data;
    Integer year;
    String name;
}
