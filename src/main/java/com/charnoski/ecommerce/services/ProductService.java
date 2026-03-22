package com.charnoski.ecommerce.services;

import com.charnoski.ecommerce.dto.ProductDTO;
import com.charnoski.ecommerce.entities.Product;
import com.charnoski.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).get();
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> productList = productRepository.findAll(pageable);
        return productList.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();
        copyDtoToEntity(productDTO, product);
        productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productRepository.getReferenceById(id);
        copyDtoToEntity(productDTO, product);
        productRepository.save(product);
        return new ProductDTO(product);
    }

    private void copyDtoToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
    }

}
