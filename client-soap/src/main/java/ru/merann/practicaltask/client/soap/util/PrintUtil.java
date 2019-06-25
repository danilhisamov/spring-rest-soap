package ru.merann.practicaltask.client.soap.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;
import ru.merann.practicaltask.client.soap.orders.wsdl.Named;
import ru.merann.practicaltask.client.soap.orders.wsdl.Order;

import java.util.Optional;
import java.util.stream.Collectors;

public class PrintUtil {
    public static String toString(Order order) {
        if (order == null) return null;
        return String.format("Order[id=%d, brand=%s, model=%s, selectedOptions=[%s], state=%s, orderDate=%s, user=%s]",
                order.getId(), toString(order.getBrand()),
                toString(order.getModel()),
                Optional.of(order).map(ord -> ord.getSelectedOptions().stream().map(PrintUtil::toString).collect(Collectors.joining(","))).orElse(null),
                order.getState().value(), order.getOrderDate(), toString(order.getUser()));
    }

    public static String toString(Named named) {
        if (named == null) return null;
        return String.format("Named[id=%d, name=%s]", named.getId(), named.getName());
    }
}
