package com.example.reactivemongodb.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductResponseDto {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
}
