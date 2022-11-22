package com.sal.flooringmastery.dao;

import com.sal.flooringmastery.dto.Order;
import java.util.List;
import java.time.LocalDate;

/**
 *
 * @author vicmaia
 */

public interface FlooringMasteryOrderDao {

    Order addOrder(Order order);

    List<Order> displayOrders(LocalDate date);

    void removeOrder(LocalDate date, int orderNumber);

    void editOrder (LocalDate date);

    Order getOrder(List<Order> orderList, int orderNumber);

}
