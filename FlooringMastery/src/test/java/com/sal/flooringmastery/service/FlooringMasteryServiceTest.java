package com.sal.flooringmastery.service;

import com.sal.flooringmastery.dao.FlooringMasteryOrderDao;
import com.sal.flooringmastery.dao.FlooringMasteryOrderDaoStub;
import com.sal.flooringmastery.dao.FlooringMasteryProductDao;
import com.sal.flooringmastery.dao.FlooringMasteryProductDaoImpl;
import com.sal.flooringmastery.dao.FlooringMasteryTaxDao;
import com.sal.flooringmastery.dao.FlooringMasteryTaxDaoImpl;
import com.sal.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author vicmaia
 */
public class FlooringMasteryServiceTest {

    FlooringMasteryOrderDao dao1 = new FlooringMasteryOrderDaoStub();
    FlooringMasteryProductDao dao2 = new FlooringMasteryProductDaoImpl();
    FlooringMasteryTaxDao dao3 = new FlooringMasteryTaxDaoImpl();
    FlooringMasteryService service = new FlooringMasteryService(dao1, dao2, dao3);
    LocalDate date = LocalDate.of(2018, Month.APRIL, 3);

    @Before
    public void setUp() throws Exception {

        List<Order> orders = dao1.displayOrders(date);

        for (Order order : orders) {
            dao1.removeOrder(date, order.getOrderNumber());
        }
    }

    @Test
    public void testAreaLessThanZero() throws Exception {

        List<Order> itemList = dao1.displayOrders(date);
        Order stubOrder = new Order();
        BigDecimal area = new BigDecimal(-25);

        stubOrder.setCustomerName("Victoria Maia");
        stubOrder.setState("CA");
        stubOrder.setProductType("Wood");
        stubOrder.setArea(area);

        try {
            service.calculateCost(stubOrder);
            fail("Expected Exception was not thrown.");
        } catch (Exception ex) {

        }
    }

    @Test
    public void testCustomerNameNull() throws Exception {

        List<Order> itemList = dao1.displayOrders(date);
        Order stubOrder = new Order();
        BigDecimal area = new BigDecimal(25);

        stubOrder.setCustomerName("");
        stubOrder.setState("ca");
        stubOrder.setProductType("Wood");
        stubOrder.setArea(area);

        try {
            service.addOrder(stubOrder);
            fail("Expected Exception was not thrown.");
        } catch (Exception ex) {

        }

    }
}
