package com.example.onlineshopping.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @JsonProperty("id")
    private UUID id;

    private String title;
    private String description;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    @JsonIgnoreProperties("product")
    private Photo photo;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        // Hash code based on the product's ID
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        // Include the hash code of the associated entities (Cart and Photo) if they are not null
        if (cart != null) {
            result = prime * result + cart.hashCode();
        }

        if (photo != null) {
            result = prime * result + photo.hashCode();
        }

        return result;
    }
}
