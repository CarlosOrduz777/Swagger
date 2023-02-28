package org.adaschool.api.controller;

import org.adaschool.api.controller.product.ProductsController;
import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.service.product.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class ProductsControllerTest {
    final String BASE_URL = "/v1/products/";
    @MockBean
    private ProductsService productsService;
    @Autowired
    private ProductsController controller;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testFindByIdExistingProduct() throws Exception {
        Product Product = new Product("1", "Whole Milk", "Whole Milk 200ml", "Dairy", 15.488);
        when(productsService.findById("1")).thenReturn(Optional.of(Product));

        mockMvc.perform(get(BASE_URL + "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.name", is("Whole Milk")))
                .andExpect(jsonPath("$.description", is("Whole Milk 200ml")))
                .andExpect(jsonPath("$.category", is("Dairy")))
                .andExpect(jsonPath("$.price", is(15.488)));

        verify(productsService, times(1)).findById("1");
    }

    @Test
    public void testFindByIdNotExistingProduct() throws Exception {
        String id = "511";
        when(productsService.findById(id)).thenReturn(Optional.empty());


        mockMvc.perform(get(BASE_URL + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"product with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(productsService, times(1)).findById(id);

    }


    @Test
    public void testSaveNewProduct() throws Exception {
        Product Product = new Product("1", "Whole Milk", "Whole Milk 200ml", "Dairy", 15.488);

        when(productsService.save(any())).thenReturn(Product);

        String json = "{\"name\":\"Whole Milk\",\"description\":\"Whole Milk 200ml\",\"category\":\"Dairy\",\"price\":15.488}";

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(productsService, times(1)).save(any());
    }

    @Test
    public void testUpdateExistingProduct() throws Exception {
        Product Product = new Product("1", "Whole Milk", "Whole Milk 200ml", "Dairy", 15.488);
        when(productsService.findById("1")).thenReturn(Optional.of(Product));

        String json = "{\"name\":\"Whole Milk\",\"description\":\"Whole Milk 200ml\",\"category\":\"Dairy\",\"price\":15.488}";
        mockMvc.perform(put(BASE_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(productsService, times(1)).save(Product);
    }

    @Test
    public void testUpdateNotExistingProduct() throws Exception {
        String id = "1";
        when(productsService.findById(id)).thenReturn(Optional.empty());
        String json = "{\"name\":\"Whole Milk\",\"description\":\"Whole Milk 200ml\",\"category\":\"Dairy\",\"price\":15.488}";
        mockMvc.perform(put(BASE_URL + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"product with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(productsService, times(0)).save(any());
    }

    @Test
    public void testDeleteExistingProduct() throws Exception {
        Product Product = new Product("1", "Whole Milk", "Whole Milk 200ml", "Dairy", 15.488);
        when(productsService.findById("1")).thenReturn(Optional.of(Product));

        String json = "{\"name\":\"Whole Milk\",\"description\":\"Whole Milk 200ml\",\"category\":\"Dairy\",\"price\":15.488}";
        mockMvc.perform(delete(BASE_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(productsService, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteNotExistingProduct() throws Exception {
        String id = "1";
        when(productsService.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(delete(BASE_URL + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"product with ID: " + id + " not found\"", result.getResolvedException().getMessage()));

        verify(productsService, times(0)).deleteById(id);
    }

}
