/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sal.flooringmastery.dao;

import com.sal.flooringmastery.dto.Product;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FlooringMasteryProductDaoImplTest {

    private FlooringMasteryProductDao dao = new FlooringMasteryProductDaoImpl();

    List<Product> productList = new ArrayList<>();

    private List<Product> generateProducts() {

        List<Product> productList = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductType("Carpet");
        productList.add(product1);

        Product product2 = new Product();
        product2.setProductType("Laminate");
        productList.add(product2);

        Product product3 = new Product();
        product3.setProductType("Tile");
        productList.add(product3);

        Product product4 = new Product();
        product4.setProductType("Wood");
        productList.add(product4);

        return productList;
    }

    @Test
    public void testLoadProductList() throws Exception {
        List<Product> products = generateProducts();
        assertEquals(productList.containsAll(products), dao.loadProductList().containsAll(products));

    }

}
