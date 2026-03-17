package com.springproject.simpleWebApp.Controller;

import com.springproject.simpleWebApp.Service.ProductService;
import com.springproject.simpleWebApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
public class productcontroller {
    @Autowired
    ProductService service;
    @GetMapping("/get products") // for giving json data to the client
    //can use @GET MAPPING
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/get products/{prodid}")
    //can use @GET MAPPING
    public Product getProduct(@PathVariable int prodid){
        return service.getProductbyid(prodid);

    }

    @PostMapping("/get products") // what is client wants to send data to the server , here we use post mapping.
    public void addProduct(@RequestBody Product prod){
        service.addproduct(prod);


    }

    @PutMapping ("/update products")
    public void update(@RequestBody Product prod){

        service.update(prod);

    }

    @DeleteMapping("/get products/delete/{prodid}")
    public void delete(@PathVariable int prodid){
        service.deleteid(prodid);

    }




}
