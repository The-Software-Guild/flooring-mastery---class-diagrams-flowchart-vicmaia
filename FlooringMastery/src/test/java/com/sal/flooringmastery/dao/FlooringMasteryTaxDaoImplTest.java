package com.sal.flooringmastery.dao;


import com.sal.flooringmastery.dto.Tax;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class FlooringMasteryTaxDaoImplTest {

    private FlooringMasteryTaxDao dao = new FlooringMasteryTaxDaoImpl();

    List<Tax> taxList = new ArrayList<>();

    private List<Tax> generateTax() {

        List<Tax> taxList = new ArrayList<>();

        Tax tax1 = new Tax();
        tax1.setState("TX");
        taxList.add(tax1);

        Tax tax2 = new Tax();
        tax2.setState("WA");
        taxList.add(tax2);

        Tax tax3 = new Tax();
        tax3.setState("KY");
        taxList.add(tax3);

        Tax tax4 = new Tax();
        tax4.setState("CA");
        taxList.add(tax4);

        return taxList;
    }

    @Test
    public void testLoadTaxRates() throws Exception {
        List<Tax> taxes = generateTax();
        assertEquals(taxList.containsAll(taxes), dao.loadTaxRates().containsAll(taxes));
    }
}
