package com.example.Ecommerce.exception;

import com.example.Ecommerce.entity.Product;

public class ProductOutOfStock extends RuntimeException{
    public ProductOutOfStock(String message){
        super(message);
    }
}
