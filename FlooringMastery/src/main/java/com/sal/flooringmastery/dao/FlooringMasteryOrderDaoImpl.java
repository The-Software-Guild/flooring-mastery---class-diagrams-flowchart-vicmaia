package com.sal.flooringmastery.dao;

import com.sal.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author vicmaia
 */
public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    public static final String DELIMITER = "::";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final String header = ("OrderNumber,CustomerName,State,TaxRate,"
            + "ProductType,Area,CostPerSquareFoot,LaborCost,PerSquareFoot,MaterialCost,LaborCost,Tax,Total");

    private List<Order> orders = new ArrayList<>();

    @Override
    public Order addOrder(Order order) {
        int num = (order.getOrderNumber() + 1); // So initial Order starts at 1 and not 0
        order.setOrderNumber(num);
        try {
            for (Order id : orders) {
                if (id.getOrderNumber() == order.getOrderNumber()) {
                    order.setOrderNumber(id.getOrderNumber() + 1);
                }
            }
            LocalDate date = order.getTimeStamp();
            orders.add(order);
            writeOrder(date, orders);
        } catch (Exception ex) {
            //if for some reason, the order is not added to the file, we catch the exception
            System.out.println("Unable to write order " + ex);
        }
        return order;
    }

    @Override
    public Order getOrder(List<Order> orderList, int orderNumber) {
        Order updatedOrder = new Order();

        //look for the current order, then updated it
        for (Order order : orderList) {
            if (orderNumber == order.getOrderNumber()) {
                updatedOrder = order;
            }
        }
        return updatedOrder;
    }

    @Override
    public List<Order> displayOrders(LocalDate date) {
        try {
            //look for the order
            readOrder(date);
        } catch (Exception ex) {
            //if we cannot find it, display this message
            System.out.println("Could Not Find Orders");
        }
        //otherwise, we display the list of all order of that specific date.
        return new ArrayList<>(orders);
    }

    @Override
    public void removeOrder(LocalDate date, int orderNumber) {

        try {
            List<Order> currentOrder = readOrder(date);
            //go through the list, and look for the order
            for (Order order : currentOrder) {
                if (orderNumber == order.getOrderNumber()) {
                    //if we find it, we remove it
                    currentOrder.remove(order);
                    writeOrder(date, currentOrder);
                    break;
                }
            }
        } catch (Exception ex) {
            //otherwise, this message will be displayed.
            System.out.println("Order Not Removed");
            ex.printStackTrace();
        }
    }

    @Override
    public void editOrder(LocalDate date) {

        try {
            //try to update order
            writeOrder(date, orders);
        } catch (Exception ex) {
            //if it is not possible...
            System.out.println("Order has not been updated!");
        }
    }

    //get order from list
    private List<Order> readOrder(LocalDate date) throws Exception {

        String ITEM_FILE = "order_" + date;

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new Exception(
                    "-_- Could not Load Orders.", e);
        }
        String currentLine;

        String[] currentTokens;

        orders.clear();

        if (orders.isEmpty()) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();

                currentTokens = currentLine.split(DELIMITER);

                if (currentTokens.length == 13) {

                    Order currentOrder = new Order();

                    int id = Integer.parseInt(currentTokens[0]);
                    BigDecimal rate = new BigDecimal(currentTokens[3]);
                    BigDecimal area = new BigDecimal(currentTokens[5]);
                    BigDecimal costSQ = new BigDecimal(currentTokens[6]);
                    BigDecimal costLabSQ = new BigDecimal(currentTokens[7]);
                    BigDecimal material = new BigDecimal(currentTokens[8]);
                    BigDecimal labor = new BigDecimal(currentTokens[9]);
                    BigDecimal tax = new BigDecimal(currentTokens[10]);
                    BigDecimal total = new BigDecimal(currentTokens[11]);
                    date = LocalDate.parse(currentTokens[12], formatter);

                    currentOrder.setOrderNumber(id);
                    currentOrder.setCustomerName(currentTokens[1]);
                    currentOrder.setState(currentTokens[2]);
                    currentOrder.setTaxRate(rate);
                    currentOrder.setProductType(currentTokens[4]);
                    currentOrder.setArea(area);
                    currentOrder.setCostPerSqFt(costSQ);
                    currentOrder.setLaborCostPerSqFt(costLabSQ);
                    currentOrder.setMaterialCost(material);
                    currentOrder.setLaborCost(labor);
                    currentOrder.setTotalTax(tax);
                    currentOrder.setTotalCost(total);
                    currentOrder.setTimeStamp(date);

                    orders.add(currentOrder);
                }
            }
        }
        scanner.close();
        return orders;
    }

    //add order to the list
    private void writeOrder(LocalDate date, List<Order> completedOrders) throws Exception {

        String ITEM_FILE = "order_" + date;
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            throw new Exception(
                    "Could not save inventory data.", e);
        }

        out.print(header + "\n");

        for (Order currentOrder : completedOrders) {

            out.println(+currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getState() + DELIMITER
                    + currentOrder.getTaxRate() + DELIMITER
                    + currentOrder.getProductType() + DELIMITER
                    + currentOrder.getArea() + DELIMITER
                    + currentOrder.getCostPerSqFt() + DELIMITER
                    + currentOrder.getLaborCostPerSqFt() + DELIMITER
                    + currentOrder.getMaterialCost() + DELIMITER
                    + currentOrder.getLaborCost() + DELIMITER
                    + currentOrder.getTotalTax() + DELIMITER
                    + currentOrder.getTotalCost() + DELIMITER
                    + currentOrder.getTimeStamp().format(formatter));

            out.flush();
        }

        out.close();
    }

}
