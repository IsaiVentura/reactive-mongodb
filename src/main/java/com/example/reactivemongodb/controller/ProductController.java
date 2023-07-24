package com.example.reactivemongodb.controller;

import com.example.reactivemongodb.dto.ProductRequestDto;
import com.example.reactivemongodb.dto.ProductResponseDto;
import com.example.reactivemongodb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<ProductResponseDto> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductResponseDto> getProductById(@PathVariable("id") String id) {
        return this.productService.getProductById(id);
    }

    @PostMapping
    public Mono<ProductResponseDto> saveProduct(@RequestBody Mono<ProductRequestDto> productRequestDto) {
        return this.productService.saveProduct(productRequestDto);
    }

    @PutMapping
    public Mono<ProductResponseDto> updateProduct(@RequestBody Mono<ProductRequestDto> productRequestDtoMono,
                                                  @RequestParam("id") String id) {
        return this.productService.updateProduct(productRequestDtoMono, id);
    }

    @DeleteMapping("/{id}")
    public Mono<String> deleteProduct(@PathVariable("id") String id) {
        return this.productService.deleteProduct(id);
    }
}
