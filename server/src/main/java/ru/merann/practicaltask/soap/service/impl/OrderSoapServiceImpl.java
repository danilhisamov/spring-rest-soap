package ru.merann.practicaltask.soap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.merann.practicaltask.core.entity.Order;
import ru.merann.practicaltask.core.entity.enums.StatusEnum;
import ru.merann.practicaltask.soap.jaxb.*;
import ru.merann.practicaltask.core.repository.*;
import ru.merann.practicaltask.soap.service.OrderSoapService;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderSoapServiceImpl implements OrderSoapService {
    private final OrderRepository orderRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final OptionRepository optionRepository;

    @Autowired
    public OrderSoapServiceImpl(OrderRepository orderRepository, ModelRepository modelRepository, UserRepository userRepository, OptionRepository optionRepository) {
        this.orderRepository = orderRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public GetOrdersResponse getOrders(GetOrdersRequest request) {
        GetOrdersResponse response = new GetOrdersResponse();
        response.getOrders().addAll(orderRepository.findAll(toOrderSpecification(request)).stream().map(Order::toSoapDTO).collect(Collectors.toList()));
        return response;
    }

    @Override
    public GetOrderResponse getOrder(GetOrderRequest request) {
        GetOrderResponse response = new GetOrderResponse();
        response.setOrder(orderRepository.getOne(request.getOrderId()).toSoapDTO());
        return response;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        CreateOrderResponse response = new CreateOrderResponse();

        ru.merann.practicaltask.soap.jaxb.Order requestOrder = request.getOrder();
        Order order = new Order();
        order.setModel(modelRepository.getOne(requestOrder.getModel().getId()));
        order.setOrderDate(new Date());
        order.setStatus(StatusEnum.RECEIVED);
        order.setUser(userRepository.getOne(requestOrder.getUser().getId()));
        optionRepository.findAllByIdIn(requestOrder.getSelectedOptions().stream().map(Named::getId).collect(Collectors.toSet()))
                .forEach(order.getSelectedOptions()::add);

        response.setOrder(orderRepository.save(order).toSoapDTO());
        return response;
    }

    @Override
    public UpdateOrderResponse updateOrder(UpdateOrderRequest request) {
        ru.merann.practicaltask.soap.jaxb.Order requestOrder = request.getOrder();

        Order order = orderRepository.getOne(requestOrder.getId());
        order.setModel(modelRepository.getOne(requestOrder.getModel().getId()));
        order.setStatus(StatusEnum.valueOf(requestOrder.getState().name()));
        order.setUser(userRepository.getOne(requestOrder.getUser().getId()));
        Set<Long> optionIds = requestOrder.getSelectedOptions().stream().map(Named::getId).collect(Collectors.toSet());
        order.getSelectedOptions().removeIf(sOption -> !optionIds.contains(sOption.getId()));
        optionRepository.findAllByIdIn(optionIds).forEach(order.getSelectedOptions()::add);

        UpdateOrderResponse response = new UpdateOrderResponse();
        response.setOrder(orderRepository.save(order).toSoapDTO());
        return response;
    }

    @Override
    public DeleteOrderResponse deleteOrder(DeleteOrderRequest request) {
        orderRepository.deleteById(request.getOrderId());
        return new DeleteOrderResponse();
    }

    private Specification<Order> toOrderSpecification(GetOrdersRequest request) {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getUserId() != null)
                predicates.add(cb.equal(root.get("user").get("id"), request.getUserId().getValue()));
            if (!StringUtils.isEmpty(request.getState()))
                predicates.add(cb.equal(root.get("status").as(String.class), request.getState().getValue().name().toUpperCase()));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
