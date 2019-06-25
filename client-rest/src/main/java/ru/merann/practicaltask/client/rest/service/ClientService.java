package ru.merann.practicaltask.client.rest.service;

import ru.merann.practicaltask.client.rest.model.OrderDTO;

public interface ClientService {
    OrderDTO[] getOrders(String userId, String state);

    OrderDTO getOrder(Long id);

    OrderDTO createOrder(Long modelId, String options, Long userId);

    OrderDTO updateOrder(Long orderId, Long modelId, String options, String status);

    void deleteOrder(Long id);
}
