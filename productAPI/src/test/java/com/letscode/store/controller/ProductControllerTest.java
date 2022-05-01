package com.letscode.store.controller;

import com.letscode.store.dto.ProductDTO;
import com.letscode.store.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void saveProductTest() throws Exception {
        Mockito.when(productService.saveProduct(Mockito.any())).thenReturn(ProductDTO.builder().build());

        mockMvc.perform(post("/product").with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                        "    \"productCode\": \"600\",\n" +
                        "    \"quantity\": 40,\n" +
                        "    \"price\": 30.0\n" +
                        "}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void listProductTest() throws Exception {
        Mockito.when(productService.listProduct(Mockito.any(), Mockito.any())).thenReturn(Page.empty());

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateProductTest() throws Exception{
        Mockito.when(productService.updateProduct(Mockito.any())).thenReturn(ProductDTO.builder().build());

        mockMvc.perform(patch("/product/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"productCode\": \"200\",\n" +
                            "    \"quantity\": 50,\n" +
                            "    \"price\": 30\n" +
                            "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteProductTest() throws Exception{
        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isOk());
    }

}
