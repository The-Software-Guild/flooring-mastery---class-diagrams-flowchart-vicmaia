package com.sal.flooringmastery.service;

import com.sal.flooringmastery.dao.FlooringMasteryOrderDao;
import com.sal.flooringmastery.dao.FlooringMasteryProductDao;
import com.sal.flooringmastery.dao.FlooringMasteryTaxDao;
import com.sal.flooringmastery.dto.Order;
import com.sal.flooringmastery.dto.Product;
import com.sal.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author vicmaia
 */
public class FlooringMasteryService {

    private FlooringMasteryOrderDao orderDao;
    private FlooringMasteryProductDao productDao;
    private FlooringMasteryTaxDao taxDao;

    public FlooringMasteryService(FlooringMasteryOrderDao dao1, FlooringMasteryProductDao dao2,
                                  FlooringMasteryTaxDao dao3) {
        this.orderDao = dao1;
        this.productDao = dao2;
        this.taxDao = dao3;
    }

    //user must input a customer name to add order.
    //returns order that was added
    public Order addOrder(Order order) throws Exception {
        if (order.getCustomerName().equals("")) {
            throw new Exception("You must Enter a Customer Name!");
        } else {
            orderDao.addOrder(order);
        }
        return order;
    }

    //user inputs date and order number to remove order
    //returns the removed order

    public void removeOrder(LocalDate date, int orderNumber) throws Exception {
        orderDao.removeOrder(date, orderNumber);
    }

    //user inputs date to display orders
    //returns all order from the date user inputs
    public List<Order> displayOrders(LocalDate date) {
        return orderDao.displayOrders(date);
    }

    //returns the order that user wants through the order number
    public Order getOrder(List<Order> orderList, int orderNumber) {
        return orderDao.getOrder(orderList, orderNumber);
    }

    //to edit order, user must have date and order number
    public void editOrder(LocalDate date, Order order) throws Exception {
        if (order.getCustomerName().equals("")) {
            throw new Exception("You must enter a customer name");
        } else {
            orderDao.editOrder(date);
        }
    }

    /*
    To calculate cost, user must select the state to calculate taxes, and the area must be larger than zero
    to complete purchase.
     */
    public Order calculateCost(Order order) throws Exception {

        BigDecimal taxRate = new BigDecimal(0);
        BigDecimal area = order.getArea();
        List<Tax> taxes = taxDao.loadTaxRates();
        List<Product> products = productDao.loadProductList();

        if (area.compareTo(BigDecimal.ZERO) >= 0) {

            for (Tax t : taxes) {
                if (t.getState().equals(order.getState())) {
                    taxRate = t.getTaxRate();
                }
            }

            for (Product p : products) {

                if (p.getProductType().equals(order.getProductType())) {

                    BigDecimal costSqFt = (p.getCostPerSqFt());
                    BigDecimal laborCostSqFt = (p.getLaborCostPerSqFt());

                    BigDecimal material = area.multiply(costSqFt);
                    BigDecimal materialCost = material.setScale(2, HALF_UP);

                    BigDecimal labor = area.multiply(laborCostSqFt);
                    BigDecimal laborCost = labor.setScale(2, HALF_UP);

                    BigDecimal subTotal = materialCost.add(laborCost);

                    BigDecimal tax = subTotal.multiply(taxRate);
                    BigDecimal taxCost = tax.setScale(2, HALF_UP);

                    BigDecimal totalCost = subTotal.add(taxCost);

                    order.setTaxRate(taxRate);
                    order.setCostPerSqFt(costSqFt);
                    order.setLaborCostPerSqFt(laborCostSqFt);
                    order.setMaterialCost(materialCost);
                    order.setLaborCost(laborCost);
                    order.setTotalTax(taxCost);
                    order.setTotalCost(totalCost);
                }
            }
        } else {
            throw new Exception("Incorrect Area. It Must be Larger than Zero!");
        }

        return order;
    }
}
