package com.springproject.simpleWebApp.Service;

import java.util.*;
import com.springproject.simpleWebApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springproject.simpleWebApp.repository.*;

@Service
public class ProductService {

    //List<product> products = new ArrayList<>( Arrays.asList(new product(100 , "iphone",500), new product(101,"Camera",1000)));
    @Autowired
    productrepo repo;

    public List<Product> getProducts() {
        return repo.findAll();
    }


    public Product getProductbyid(int Prodid) {
        return repo.findById(Prodid).orElse(new Product()); // these are jpa methods
    }

    public void addproduct(Product prod) {
        repo.save(prod);


    }


    public void update(Product prod) {
        repo.save(prod);

    }

    public void deleteid(int prodid) {
        repo.deleteById(prodid);


    }
}
