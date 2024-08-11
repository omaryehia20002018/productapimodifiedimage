package com.example.product_api.controller;

import com.example.product_api.entity.Product;
import com.example.product_api.repository.ProductRepo;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class ProductRestController {

  @Autowired
  ProductRepo productRepo ;
  //@Autowired


  @GetMapping("/products")
  public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
    Pageable pageable= PageRequest.of(page,size);

    return productRepo.findAll(pageable);
  }

  @GetMapping("/products/{id}")
  public Product getProduct(@PathVariable("id") int id){
    productRepo.findById(id).orElseThrow(()->new NoSuchElementException("no product with this id"));


    return productRepo.findById(id).get();
  }

  @GetMapping("/products/{id}/isavailable")
  public boolean isavailable(@PathVariable("id") int id){
    //productRepo.findById(id).orElseThrow(()->new NoSuchElementException("no product with this id"));
    if (!productRepo.findById(id).isPresent()){
      return false;
    }


    return true;
  }

  @PostMapping("/products")
  public Product createProduct(@RequestBody Product product){
    return productRepo.save(product);
  }


  @PutMapping("/products")
  public Product updateProduct(@RequestBody Product product){

    return productRepo.save(product);
  }

  @DeleteMapping("/products/{id}")
  public void deleteProduct(@PathVariable("id") int id){
    productRepo.deleteById(id);
  }


}
