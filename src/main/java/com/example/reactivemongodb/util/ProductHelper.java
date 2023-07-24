package com.example.reactivemongodb.util;

import com.example.reactivemongodb.domain.Product;
import com.example.reactivemongodb.dto.ProductRequestDto;
import com.example.reactivemongodb.dto.ProductResponseDto;
import org.springframework.beans.BeanUtils;

public class ProductHelper {

    public static Product buildProductFromProductRequestDto(ProductRequestDto productRequestDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequestDto, product);
        return product;
    }

    public static ProductResponseDto buildProductResponseDtoFromProduct(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        BeanUtils.copyProperties(product, productResponseDto);
        return productResponseDto;
    }
}
