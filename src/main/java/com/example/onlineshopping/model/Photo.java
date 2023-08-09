package com.example.onlineshopping.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String originalFilename;
    private String contentType;

    @OneToOne(mappedBy = "photo")
    @JsonIgnoreProperties("photo")
    private Product product;

    @Lob
    @Column(length = 20971520)
    private byte[] content;
}
