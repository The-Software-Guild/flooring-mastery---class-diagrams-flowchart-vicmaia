package com.sal.flooringmastery.ui;

import com.sal.flooringmastery.dto.Order;
import com.sal.flooringmastery.service.FlooringMasteryService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author vicmaia
 */
public class FlooringMasteryView {

    private UserIO io;
    private FlooringMasteryService service;
    private final String stateNames = "TX, WA, KY, CA";
    private final String types = "Carpet, Laminate, Tile, Wood";

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    //display main menu
    public int getSelection() {
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    //once user selects number from main menu, the program will ask to enter the date
    public LocalDate getDate() {
        LocalDate dateIn = io.readLocalDate("Please Enter Order Date (MM/DD/YYYY) Format only");
        return dateIn;
    }

    //once user enters date, the program will ask to enter order number
    public int getOrderNum() {
        System.out.println("\n");
        int orderNumber = io.readInt("Please enter your order Number");
        System.out.println("\n");
        return orderNumber;
    }

    //select Y to confirm order or N to not confirm it.
    public String acceptOrder() {
        String acceptOrder = io.readString("Confirm Order, Y/N ??");
        return acceptOrder;
    }

    //show order details
    public void orderSummary(Order order) {
        System.out.println("\n");
        System.out.println("Material Cost Per Square Foot: " + order.getCostPerSqFt());
        System.out.println("Labor Cost Per Sqaure Foot " + order.getLaborCostPerSqFt());
        System.out.println("Total Material Cost " + order.getMaterialCost());
        System.out.println("Total Labor Cost " + order.getLaborCost());
        System.out.println("Total Taxes " + order.getTotalTax());
        System.out.println("Total Cost " + order.getTotalCost());
        System.out.println("Order To Be Received at " + order.getTimeStamp());
        System.out.println("\n");
    }

    //if the information is the same just leave it in blank
    //if the information is new, input new information
    public Order editOrder(Order order) {

        BigDecimal zero = BigDecimal.ZERO;

        System.out.println("Leave blank if no changes are needed");
        System.out.println("\n");
        String customerName = io.readString("New Customer name: ");
        String state = io.readOptionalState("New Location: " + stateNames);
        String productType = io.readOptionalProduct("New Product Name: " + types);
        BigDecimal area = io.readOptionalBigDecimal("New Area: ");

        if (customerName.equals("")) {
            customerName = order.getCustomerName();
        } else {
            order.setCustomerName(customerName);
        }
        if (state.equals("")) {
            state = order.getState();
        } else {
            order.setState(state);
        }
        if (productType.equals("")) {
            productType = order.getCustomerName();
        } else {
            order.setProductType(productType);
        }
        if (area.compareTo(zero) == 0) {
            area = order.getArea();
        } else {
            order.setArea(area);
        }

        return order;
    }
    
    //user inputs customer name, state, product type, and area in square ft. 
    public Order getOrderData() {

        String customerName = io.readString("Please enter customer name: ");
        String state = io.readState("Please enter the state: " + stateNames);
        String productType = io.readProduct("Please enter the product type: " + types);
        BigDecimal area = io.readBigDecimal("Please enter the area in square feet: ");
        LocalDate time = LocalDate.now();

        Order currentOrder = new Order();

        currentOrder.setOrderNumber(currentOrder.getOrderNumber());
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);
        currentOrder.setTimeStamp(time);
        return currentOrder;
    }
    
    //example:
    //1: Victoria Maia PA Carpet 147.90 2022-11-21
    public void displayByDate(LocalDate date, List<Order> orders) {
        System.out.println("\n");
        for (Order order : orders) {
            if (date.equals(order.getTimeStamp())) {
                io.print(order.getOrderNumber() + ": "
                        + order.getCustomerName() + " "
                        + order.getState() + " "
                        + order.getProductType() + " "
                        + order.getTotalCost() + " "
                        + order.getTimeStamp());
            } else {
                //if order is not in the system
                System.out.println("No Order Found");
            }
        }
        System.out.println("\n");
    }
    
    //once order is added to th system
    public void orderSuccesfullBanner() {
        System.out.println("Your Order Has Been Successfully Stored In The System.");
        System.out.println("\n");
    }

    public void SearchBanner() {
        System.out.println("===SEARCH BY DATE===");
    }

    public void editBanner() {
        System.out.println("===ORDER EDIT===");
    }

    public void createOrderBanner() {
        System.out.println("===CREATE AN ORDER===");
    }

    public void removeBanner() {
        System.out.println("===REMOVE ORDER===");
    }
    
    //message that appears when order is removed
    public void removeSucessBanner() {
        System.out.println("Your Order Has Been Successfully Removed.");
    }
    //message that appears when order is updated
    public void editSuccessBanner() {
        System.out.println("Your Order Has Been Successfully Updated.");
    }
    
    ////message that appears when order is not saved
    public void orderNotSavedBanner() {
        System.out.println("Order not Saved, Returning To Main Menu!");
        System.out.println("\n");
    }


}
