package com.example.onlineshopping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoDto {

    private String originalFilename;
    private String contentType;
    private byte[] content;
}
