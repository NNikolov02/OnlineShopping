package com.example.onlineshopping.web;

import com.example.onlineshopping.dto.PhotoDto;
import com.example.onlineshopping.dto.product.ProductApiPage;
import com.example.onlineshopping.dto.product.ProductCreateRequest;
import com.example.onlineshopping.dto.product.ProductResponse;
import com.example.onlineshopping.dto.product.ProductUpdateRequest;
import com.example.onlineshopping.error.InvalidObjectException;
import com.example.onlineshopping.mapping.ProductMapper;
import com.example.onlineshopping.model.Product;
import com.example.onlineshopping.service.ProductService;
import com.example.onlineshopping.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/onlineStore/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ObjectValidator validator;

    private final Integer Page_Size = 10;


    @GetMapping(name = "", produces = "application/json")
    public ProductApiPage<ProductResponse> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") Integer currPage) {


        Page<ProductResponse> productsPage =productService.fetchAll(currPage, Page_Size)
                .map(productMapper::responseFromModel);

        return new ProductApiPage<>(productsPage);
    }

    @GetMapping(value ="/{productId}")
    public ResponseEntity<ProductResponse>findById(@PathVariable String productId){

        Product product = productService.findById(productId);


        return ResponseEntity.ok(productMapper.responseFromModel(product));
    }


    @GetMapping(value = "/title/{title}")
    public ResponseEntity<ProductResponse>findByTitle(@PathVariable String title){

        Product product =productService.findByTitle(title);

        return ResponseEntity.ok(productMapper.responseFromModel(product));
    }


    @GetMapping(value = "/description/{description}")
    public ResponseEntity<ProductResponse>findByDescription(@PathVariable String description){

        Product product = productService.findByDescription(description);

        return ResponseEntity.ok(productMapper.responseFromModel(product));
    }

    @DeleteMapping(value ="/{productId}")
    public void deleteById(@PathVariable String productId){
        productService.deleteById(productId);
    }

    @PostMapping("/with-photo")
    public ResponseEntity<ProductResponse>createProduct(@RequestBody ProductCreateRequest productDto,@RequestBody MultipartFile photoFile){


        if (photoFile != null && !photoFile.isEmpty()) {

            productDto.getPhoto().setOriginalFilename(photoFile.getOriginalFilename());
            productDto.getPhoto().setContentType(photoFile.getContentType());
            try {
                productDto.getPhoto().setContent(photoFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error processing photo upload", e);
            }
            productDto.setPhoto(productDto.getPhoto());
        }
        Map<String, String> validationErrors = validator.validate(productDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Product Create", validationErrors);
        }

        Product createProduct = productMapper.modelFromCreateRequest(productDto);

        Product savedProduct = productService.save(createProduct);

        ProductResponse productResponse = productMapper.responseFromModel(savedProduct);


        return  ResponseEntity.status(201).body(productResponse);
    }

    @PatchMapping(value ="/{productId}")
    public ResponseEntity<ProductResponse>updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest productDto){
        Map<String, String> validationErrors = validator.validate(productDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Product Update", validationErrors);
        }

        Product findProduct = productService.findById(productId);
        productMapper.updateModelFromDto(productDto, findProduct);

        Product saveProduct= productService.save(findProduct);

        ProductResponse productResponse = productMapper.responseFromModel(saveProduct);


        return ResponseEntity.status(202).body(productResponse);




    }
}
