package com.sal.flooringmastery.dao;

import com.sal.flooringmastery.dto.Tax;
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
public class FlooringMasteryTaxDaoImpl implements FlooringMasteryTaxDao {

    private final String ITEM_FILE = "Taxes.txt";
    private final String DELIMITER = "::";

    List<Tax> taxes = new ArrayList<>();

    //go through the file to look for the state to get taxes
    @Override
    public List<Tax> loadTaxRates() throws Exception {
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

            if (currentTokens.length
                    == 2) {
                Tax currentTax = new Tax();

                BigDecimal cost = new BigDecimal(currentTokens[1]);

                currentTax.setState(currentTokens[0]);
                currentTax.setTaxRate(cost);

                taxes.add(currentTax);
            }

        }

        scanner.close();
        return taxes;
    }

}
