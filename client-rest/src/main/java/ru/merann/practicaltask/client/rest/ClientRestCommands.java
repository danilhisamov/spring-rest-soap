package ru.merann.practicaltask.client.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.merann.practicaltask.client.rest.service.impl.RestService;

import java.util.Arrays;

@ShellComponent
public class ClientRestCommands {
    public static final String NULL_VALUE = "_MPR_NULL_";

    private final RestService restService;

    @Autowired
    public ClientRestCommands(RestService restService) {
        this.restService = restService;
    }

    @ShellMethod("Get orders (filter is optional)")
    public void getOrders(@ShellOption(defaultValue = NULL_VALUE) String userId, @ShellOption(defaultValue = NULL_VALUE) String state) {
        Arrays.stream(restService.getOrders(userId, state)).forEach(System.out::println);
    }

    @ShellMethod("Get order by id")
    public void getOrderById(@ShellOption Long id) {
        System.out.println(restService.getOrder(id));
    }

    //    create-order 1 1,2,3,4 1
    @ShellMethod("Create order")
    public void createOrder(@ShellOption Long modelId, @ShellOption(help = "divided by a ','") String options, @ShellOption Long userId) {
        System.out.println(restService.createOrder(modelId, options, userId));
    }

    //update-order 5 1 1,2,3 processing
    @ShellMethod("Update order")
    public void updateOrder(@ShellOption Long orderId, @ShellOption Long modelId, @ShellOption String options, @ShellOption String status) {
        System.out.println(restService.updateOrder(orderId, modelId, options, status));
    }

    @ShellMethod("Delete order by id")
    public void deleteOrder(@ShellOption Long id) {
        restService.deleteOrder(id);
        System.out.println("Order successfully deleted");
    }
}
