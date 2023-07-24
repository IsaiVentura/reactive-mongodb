package com.example.reactivemongodb.service;

import com.example.reactivemongodb.dto.ProductRequestDto;
import com.example.reactivemongodb.dto.ProductResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductResponseDto> getAllProducts();

    Mono<ProductResponseDto> getProductById(String id);

    Mono<ProductResponseDto> saveProduct(Mono<ProductRequestDto> productRequestDto);

    Mono<ProductResponseDto> updateProduct(Mono<ProductRequestDto> productRequestDto, String id);

    Mono<String> deleteProduct(String id);
}
