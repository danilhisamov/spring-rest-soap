package ru.merann.practicaltask.client.soap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.merann.practicaltask.client.soap.orders.OrdersClient;
import ru.merann.practicaltask.client.soap.orders.wsdl.*;
import ru.merann.practicaltask.client.soap.util.PrintUtil;

import java.util.Arrays;

@ShellComponent
public class ClientSoapCommands {

    public static final String NULL_VALUE = "_MPR_NULL_";

    private final OrdersClient ordersClient;

    @Autowired
    public ClientSoapCommands(OrdersClient ordersClient) {
        this.ordersClient = ordersClient;
    }

    @ShellMethod("Get orders (filter is optional)")
    public void getOrders(@ShellOption(defaultValue = NULL_VALUE) String userId, @ShellOption(defaultValue = NULL_VALUE) String state) {
        Long usrId = NULL_VALUE.equals(userId) ? null : Long.parseLong(userId);
        state = NULL_VALUE.equals(state) ? null : state;
        ordersClient.getOrders(usrId, state).getOrders().forEach(order -> System.out.println(PrintUtil.toString(order)));
    }

    @ShellMethod("Get order by id")
    public void getOrderById(@ShellOption Long id) {
        GetOrderResponse response = ordersClient.getOrder(id);
        System.out.println(PrintUtil.toString(response.getOrder()));
    }

    //    create-order 1 1,2,3,4 1
    @ShellMethod("Create order")
    public void createOrder(@ShellOption Long modelId, @ShellOption(help = "divided by a ','") String options, @ShellOption Long userId) {
        CreateOrderResponse response = ordersClient.createOrder(modelId, options, userId);
        System.out.println(PrintUtil.toString(response.getOrder()));
    }

    //update-order 5 1 1,2,3 processing
    @ShellMethod("Update order")
    public void updateOrder(@ShellOption Long orderId, @ShellOption Long modelId, @ShellOption String options, @ShellOption String status) {
        UpdateOrderResponse response = ordersClient.updateOrder(orderId, modelId, options, status);
        System.out.println(PrintUtil.toString(response.getOrder()));
    }

    @ShellMethod("Delete order by id")
    public void deleteOrder(@ShellOption Long id) {
        DeleteOrderResponse response = ordersClient.deleteOrder(id);
        System.out.println(response);
    }
}
