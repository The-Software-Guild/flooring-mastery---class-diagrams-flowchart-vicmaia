package com.sal.flooringmastery.dao;


import com.sal.flooringmastery.dto.Tax;
import java.util.List;
/**
 *
 * @author vicmaia
 */
public interface FlooringMasteryTaxDao {

    List<Tax> loadTaxRates() throws Exception;
}
