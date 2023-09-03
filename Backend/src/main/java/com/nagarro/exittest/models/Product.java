package com.nagarro.exittest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String brand;
    private double prodPrice;

    private String productCode;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prod_code_fk", referencedColumnName = "productId")
    private List<Review> prodReviews;
}
