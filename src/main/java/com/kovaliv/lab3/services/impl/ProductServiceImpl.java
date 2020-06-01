package com.kovaliv.lab3.services.impl;

import com.kovaliv.lab3.constants.ErrorConstants;
import com.kovaliv.lab3.dtos.AddProductDto;
import com.kovaliv.lab3.dtos.AddQuantityOfProductDto;
import com.kovaliv.lab3.dtos.ProductDto;
import com.kovaliv.lab3.entities.Product;
import com.kovaliv.lab3.repos.ProductRepo;
import com.kovaliv.lab3.services.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDto> getAll() {
        return productRepo.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(AddQuantityOfProductDto addQuantityOfProductDto) {
        Product product = findById(addQuantityOfProductDto.getProductId());

        product.setQuantity(product.getQuantity() + addQuantityOfProductDto.getQuantity());

        return modelMapper.map(productRepo.save(product), ProductDto.class);
    }

    @Override
    public ProductDto addProduct(AddProductDto addProductDto) {
        return modelMapper.map(productRepo.save(modelMapper.map(addProductDto, Product.class)), ProductDto.class);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = findById(id);

        productRepo.delete(product);
    }

    private Product findById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(ErrorConstants.PRODUCT_NOT_FOUND + id));
    }
}
