package com.sal.flooringmastery.controller;

import com.sal.flooringmastery.dto.Order;
import com.sal.flooringmastery.service.FlooringMasteryService;
import com.sal.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author vicmaia
 */
public class FlooringMasteryController {

    private FlooringMasteryView view;
    private FlooringMasteryService service;

    //contructor
    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        /* This is how the main menu will be displayed when running the program.
        * 1. Display Orders
        * 2. Add an Order
        * 3. Edit an Order
        * 4. Remove an Order
        * 5. Quit
         */

        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            System.out.println("\n");
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }

            exitMessage();
        } catch (Exception e) {
            //if user inputs wrong number, an error message will be displayed
            System.out.println("ERROR" + e);
        }
    }

    //returns user selection
    private int getMenuSelection() throws Exception {
        return view.getSelection();
    }

    //returns menu for option 1
    private void displayOrders() {
        view.SearchBanner();
        LocalDate date = view.getDate();
        List<Order> orderedDates = service.displayOrders(date);
        view.displayByDate(date, orderedDates);

    }

    //returns menu for option 2
    private void addOrder() {
        try {
            view.createOrderBanner();
            Order order = view.getOrderData();
            Order completedOrder = service.calculateCost(order);
            view.orderSummary(completedOrder);
            String accepted = view.acceptOrder();
            if (accepted.equals("Y")) {
                service.addOrder(completedOrder);
                view.orderSuccesfullBanner();
            } else {
                view.orderNotSavedBanner();
            }

        } catch (Exception ex) {
            System.out.println("\n");
            System.out.println(ex);
        }
    }

    //returns menu for option 3

    private void editOrder() {
        try {
            view.editBanner();
            LocalDate date = view.getDate();
            int orderNumber = view.getOrderNum();
            List<Order> orderedDates = service.displayOrders(date);
            Order updated = service.getOrder(orderedDates, orderNumber);
            Order updatedOrder = view.editOrder(updated);
            Order finalOrder = service.calculateCost(updatedOrder);
            view.orderSummary(finalOrder);
            service.editOrder(date, finalOrder);
            view.editSuccessBanner();
        } catch (Exception ex) {
            System.out.println("\n");
            System.out.println(ex);

        }
    }

    //returns menu for option 4
    private void removeOrder() {
        view.removeBanner();
        LocalDate date = view.getDate();
        int orderNumber = view.getOrderNum();
        try {
            service.removeOrder(date, orderNumber);
            view.removeSucessBanner();
        } catch (Exception ex) {
            System.out.println("Order has not been removed!");
        }
    }

    //if user inputs wrong number
    private void unknownCommand() {
        System.out.println("Unknown Command! Please Enter a Valid Selection");
    }

    //returns menu for option 5
    private void exitMessage() {
        System.out.println("Thanks for Visiting! Have a Great Day!");
    }
}
