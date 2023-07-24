package com.example.reactivemongodb.service;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import com.example.reactivemongodb.domain.Product;
import com.example.reactivemongodb.dto.ProductRequestDto;
import com.example.reactivemongodb.dto.ProductResponseDto;
import com.example.reactivemongodb.repository.ProductRepository;
import com.example.reactivemongodb.util.ProductHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Flux<ProductResponseDto> getAllProducts() {
        return this.productRepository.findAll()
                .map(ProductHelper::buildProductResponseDtoFromProduct);
    }

    @Override
    public Mono<ProductResponseDto> getProductById(String id) {
        return this.productRepository.findById(id)
                .map(ProductHelper::buildProductResponseDtoFromProduct);
    }

    @Override
    public Mono<ProductResponseDto> saveProduct(Mono<ProductRequestDto> productRequestDto) {
        return productRequestDto.map(ProductHelper::buildProductFromProductRequestDto)
                .flatMap(this.productRepository::insert)
                .map(ProductHelper::buildProductResponseDtoFromProduct);
    }


    public Mono<ProductResponseDto> updateProductv(Mono<ProductRequestDto> productRequestDto, String id) {
        return this.productRepository.findById(id)

                .flatMap(p -> productRequestDto.map(ProductHelper::buildProductFromProductRequestDto))
                .flatMap(this.productRepository::save)

                .map(ProductHelper::buildProductResponseDtoFromProduct);

    }

    @Override
    public Mono<ProductResponseDto> updateProduct(Mono<ProductRequestDto> productRequestDto, String id) {
        Mono<Product> map = this.productRepository.findById(id)
            .map(new Function<Product, Product>() {
                public Product apply(Product product) {
                    log.debug("appl1");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //String name = productRequestDto.block().getName();
                    product.setName("sds");
                    log.debug("apply2");
                    return product;
                }
            });
        log.debug("Llego al repository");
        Mono<String> block = map.map(p -> p.getName());
        log.debug("Llego al repository 2");
        //this.productRepository.save(map.block());
        return null;
    }

    @Override
    public Mono<String> deleteProduct(String id) {
        return this.productRepository.deleteById(id).thenReturn("Producto eliminado exitosamente!");
    }
}
