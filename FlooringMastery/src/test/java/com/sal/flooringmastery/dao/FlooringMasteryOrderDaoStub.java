package com.sal.flooringmastery.dao;

import com.sal.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class FlooringMasteryOrderDaoStub implements FlooringMasteryOrderDao {

    LocalDate date = LocalDate.of(2018, Month.APRIL, 3);
    private Order currentOrder;

    List<Order> orderList = new ArrayList<>();

    public Order stubOrder() {

        List<Order> orderList = new ArrayList<>();

        Order currentOrder = new Order();

        BigDecimal rate = new BigDecimal(0.06);
        BigDecimal area = new BigDecimal(25);
        BigDecimal costSQ = new BigDecimal(5.15);
        BigDecimal costLabSQ = new BigDecimal(4.75);
        BigDecimal material = new BigDecimal(128.75);
        BigDecimal labor = new BigDecimal(118.75);
        BigDecimal tax = new BigDecimal(14.85);
        BigDecimal total = new BigDecimal(262.35);

        currentOrder.setOrderNumber(1);
        currentOrder.setCustomerName("SoftwareGuild");
        currentOrder.setState("KY");
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

        orderList.add(currentOrder);
        return currentOrder;
    }

    @Override
    public Order addOrder(Order order) {

        return order;
    }

    @Override
    public Order getOrder(List<Order> orderList, int orderNumber) {
        Order updatedOrder = new Order();
        for (Order order : orderList) {
            if (orderNumber == order.getOrderNumber()) {
                updatedOrder = order;
            }
        }
        return updatedOrder;
    }

    @Override
    public List<Order> displayOrders(LocalDate date) {

        return orderList;
    }

    @Override
    public void removeOrder(LocalDate date, int orderNumber) {

    }

    @Override
    public void editOrder(LocalDate date) {

    }
}
