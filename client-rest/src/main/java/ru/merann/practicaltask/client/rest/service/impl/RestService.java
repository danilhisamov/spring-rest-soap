package ru.merann.practicaltask.client.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import ru.merann.practicaltask.client.rest.ClientRestCommands;
import ru.merann.practicaltask.client.rest.model.NamedDTO;
import ru.merann.practicaltask.client.rest.model.OrderDTO;
import ru.merann.practicaltask.client.rest.service.ClientService;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class RestService implements ClientService {
    private final Logger logger = LoggerFactory.getLogger(RestService.class);
    private final WebClient webClient;

    @Autowired
    public RestService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public OrderDTO[] getOrders(String userId, String state) {
        OrderDTO[] orders = webClient
                .get()
                .uri(uriBuilder -> {
                    uriBuilder = uriBuilder.path("/orders");
                    if (!ClientRestCommands.NULL_VALUE.equals(userId)) uriBuilder = uriBuilder.queryParam("userId", Long.parseLong(userId));
                    if (!ClientRestCommands.NULL_VALUE.equals(state)) uriBuilder = uriBuilder.queryParam("state", state);
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(OrderDTO[].class)
                .block();
        if (orders == null) orders = new OrderDTO[0];
        return orders;
    }

    @Override
    public OrderDTO getOrder(Long id) {
        return webClient
                .get()
                .uri(String.format("/orders/%d", id))
                .retrieve()
                .bodyToMono(OrderDTO.class)
                .block();
    }

    @Override
    public OrderDTO createOrder(Long modelId, String options, Long userId) {
        return webClient.post().uri("/orders").body(BodyInserters.fromObject(new OrderDTO(modelId, options, userId))).retrieve().bodyToMono(OrderDTO.class).block();
    }

    @Override
    public OrderDTO updateOrder(Long orderId, Long modelId, String options, String status) {
        logger.debug("Getting order with id={}", orderId);
        OrderDTO dto = getOrder(orderId);
        logger.debug("Order received: {}", dto.toString());
        dto.setModel(new NamedDTO(modelId));
        dto.setSelectedOptions(Arrays.stream(options.split(",")).map(Long::parseLong).map(NamedDTO::new).collect(Collectors.toList()));
        dto.setStatus(status);

        OrderDTO savedDto = webClient.put().uri(String.format("/orders/%d", orderId)).body(BodyInserters.fromObject(dto)).retrieve().bodyToMono(OrderDTO.class).block();

        logger.info("Updated order: {}", savedDto);
        return savedDto;
    }

    @Override
    public void deleteOrder(Long id) {
        boolean b = webClient
                .delete()
                .uri(String.format("/orders/%d", id))
                .exchange().blockOptional().map(ClientResponse::statusCode).map(HttpStatus::is2xxSuccessful).orElse(false);
        if (b) {
            logger.info("Order with id={} was successfully deleted", id);
        } else {
            logger.info("There is a problem during deleting order with id={}", id);
        }
    }
}
