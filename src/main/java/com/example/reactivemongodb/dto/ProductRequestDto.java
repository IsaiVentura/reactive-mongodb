package com.example.reactivemongodb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequestDto {
    private String name;
    private Integer quantity;
    private Double price;
}
