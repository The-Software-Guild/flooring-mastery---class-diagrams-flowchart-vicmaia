package com.sal.flooringmastery.dao;

import com.sal.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vicmaia
 */

public class FlooringMasteryProductDaoImpl implements FlooringMasteryProductDao {

    private final String ITEM_FILE = "Products.txt";
    private final String DELIMITER = "::";


    //go through the file to look for the specific product available

    public List<Product> loadProductList() throws Exception {
        List<Product> products = new ArrayList<>();
        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new Exception(
                    "-_- Could not Find Products.", e);
        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            if (currentTokens.length == 3) {
                Product currentProduct = new Product();

                BigDecimal cost = new BigDecimal(currentTokens[1]);
                BigDecimal labor = new BigDecimal(currentTokens[2]);

                currentProduct.setProductType(currentTokens[0]);
                currentProduct.setCostPerSqFt(cost);
                currentProduct.setLaborCostPerSqFt(labor);

                products.add(currentProduct);
            }

        }

        scanner.close();
        return products;
    }

}
