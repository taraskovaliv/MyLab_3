package com.kovaliv.lab3.services;

import com.kovaliv.lab3.dtos.AddProductDto;
import com.kovaliv.lab3.dtos.AddQuantityOfProductDto;
import com.kovaliv.lab3.dtos.ProductDto;
import com.kovaliv.lab3.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    ProductDto updateProduct(AddQuantityOfProductDto addQuantityOfProductDto);

    ProductDto addProduct(AddProductDto addProductDto);

    void deleteProduct(Long id);

    Product findById(Long id);
}
