package com.example.onlineshopping.dto.product;

import com.example.onlineshopping.dto.PhotoDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ProductWithPhotoRequest {

    private MultipartFile photoFile;
    private PhotoDto photoDto;
}