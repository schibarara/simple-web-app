package com.springproject.simple.web.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getProductsReturnsSeededProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Keyboard"));
    }

    @Test
    void createProductReturnsCreated() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 2001,
                                  "name": "USB Hub",
                                  "price": 35
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2001))
                .andExpect(jsonPath("$.name").value("USB Hub"));
    }

    @Test
    void getMissingProductReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/products/9999"))
                .andExpect(status().isNotFound());
    }
}
