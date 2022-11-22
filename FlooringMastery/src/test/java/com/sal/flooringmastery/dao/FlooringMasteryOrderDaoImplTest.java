package com.sal.flooringmastery.dao;

import com.sal.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class FlooringMasteryOrderDaoImplTest {

    FlooringMasteryOrderDao dao = new FlooringMasteryOrderDaoImpl();
    private Order firstOrder;
    private Order secondOrder;
    private List<Order> orderList = new ArrayList<>();
    LocalDate date = LocalDate.of(2018, Month.APRIL, 3);

    @Before
    public void setUp() throws Exception {

        List<Order> orders = dao.displayOrders(date);

        for (Order order : orders) {
            dao.removeOrder(date, order.getOrderNumber());
        }
    }

    @Test
    public void testAddOrder() throws Exception {
        firstOrder = order1();
        secondOrder = order2();

        assertEquals(firstOrder, dao.getOrder(orderList, 1));

        assertEquals(secondOrder, dao.getOrder(orderList, 2));

    }

    @Test
    public void testSearchOrders() throws Exception {
        order1();
        order2();

        List<Order> orders = dao.displayOrders(date);
        assertEquals(2, orders.size());
    }

    @Test
    public void testRemoveOrder() throws Exception {

        order1();
        order2();

        dao.removeOrder(date, 1);
        assertEquals(1, dao.displayOrders(date).size());

        dao.removeOrder(date, 2);
        assertEquals(0, dao.displayOrders(date).size());


    }

    @Test
    public void testEditOrder() throws Exception {

        Order order = order1();
        order.setCustomerName("TestNameSuccess");

        dao.editOrder(date);
        assertEquals("TestNameSuccess", order.getCustomerName());

    }

    public Order order1() {

        Order currentOrder = new Order();

        BigDecimal rate = new BigDecimal(0.06);
        BigDecimal area = new BigDecimal(25);
        BigDecimal costSQ = new BigDecimal(5.15);
        BigDecimal costLabSQ = new BigDecimal(4.75);
        BigDecimal material = new BigDecimal(128.75);
        BigDecimal labor = new BigDecimal(118.75);
        BigDecimal tax = new BigDecimal(14.85);
        BigDecimal total = new BigDecimal(262.35);

        currentOrder.setOrderNumber(0);
        currentOrder.setCustomerName("Victori Maia");
        currentOrder.setState("CA");
        currentOrder.setTaxRate(rate);
        currentOrder.setProductType("Wood");
        currentOrder.setArea(area);
        currentOrder.setCostPerSqFt(costSQ);
        currentOrder.setLaborCostPerSqFt(costLabSQ);
        currentOrder.setMaterialCost(material);
        currentOrder.setLaborCost(labor);
        currentOrder.setTotalTax(tax);
        currentOrder.setTotalCost(total);
        currentOrder.setTimeStamp(date);

        dao.addOrder(currentOrder);
        orderList.add(currentOrder);
        return currentOrder;
    }

    public Order order2() {

        Order otherOrder = new Order();

        BigDecimal rate = new BigDecimal(0.0675);
        BigDecimal area = new BigDecimal(25);
        BigDecimal costSQ = new BigDecimal(2.25);
        BigDecimal costLabSQ = new BigDecimal(2.10);
        BigDecimal material = new BigDecimal(56.25);
        BigDecimal labor = new BigDecimal(52.50);
        BigDecimal tax = new BigDecimal(7.34);
        BigDecimal total = new BigDecimal(126.09);

        otherOrder.setOrderNumber(1);
        otherOrder.setCustomerName("Kelly S.");
        otherOrder.setState("TX");
        otherOrder.setTaxRate(rate);
        otherOrder.setProductType("Carpet");
        otherOrder.setArea(area);
        otherOrder.setCostPerSqFt(costSQ);
        otherOrder.setLaborCostPerSqFt(costLabSQ);
        otherOrder.setMaterialCost(material);
        otherOrder.setLaborCost(labor);
        otherOrder.setTotalTax(tax);
        otherOrder.setTotalCost(total);
        otherOrder.setTimeStamp(date);

        dao.addOrder(otherOrder);
        orderList.add(otherOrder);
        return otherOrder;
    }
}
