package com.sal.flooringmastery.dao;

import com.sal.flooringmastery.dto.Product;

import java.util.List;

/**
 *
 * @author vicmaia
 */

public interface FlooringMasteryProductDao {

    List<Product> loadProductList() throws Exception;

}