package ru.merann.practicaltask.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.merann.practicaltask.core.entity.Order;
import ru.merann.practicaltask.core.entity.enums.StatusEnum;
import ru.merann.practicaltask.core.repository.*;
import ru.merann.practicaltask.rest.model.NamedDTO;
import ru.merann.practicaltask.rest.model.OrderDTO;
import ru.merann.practicaltask.rest.model.OrderFilter;
import ru.merann.practicaltask.rest.service.OrderRestService;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderRestServiceImpl implements OrderRestService {
    private final OrderRepository orderRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final OptionRepository optionRepository;

    @Autowired
    public OrderRestServiceImpl(OrderRepository orderRepository, BrandRepository brandRepository, ModelRepository modelRepository, UserRepository userRepository, OptionRepository optionRepository) {
        this.orderRepository = orderRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public List<OrderDTO> getOrders(Long userId, String state) {
        return orderRepository.findAll(toOrderSpecification(userId, state)).stream().map(Order::toRestDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrder(Long orderId) {
        return orderRepository.getOne(orderId).toRestDTO();
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setModel(modelRepository.getOne(orderDTO.getModel().getId()));
        order.setOrderDate(new Date());
        order.setStatus(StatusEnum.RECEIVED);
        order.setUser(userRepository.getOne(orderDTO.getUser().getId()));
        optionRepository.findAllByIdIn(orderDTO.getSelectedOptions().stream().map(NamedDTO::getId).collect(Collectors.toSet()))
                .forEach(order.getSelectedOptions()::add);
        return orderRepository.save(order).toRestDTO();
    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.getOne(orderId);
        order.setModel(modelRepository.getOne(orderDTO.getModel().getId()));
        order.setStatus(StatusEnum.valueOf(orderDTO.getStatus().toUpperCase()));
        order.setUser(userRepository.getOne(orderDTO.getUser().getId()));
        Set<Long> optionIds = orderDTO.getSelectedOptions().stream().map(NamedDTO::getId).collect(Collectors.toSet());
        order.getSelectedOptions().removeIf(sOption -> !optionIds.contains(sOption.getId()));
        optionRepository.findAllByIdIn(optionIds).forEach(order.getSelectedOptions()::add);
        return orderRepository.save(order).toRestDTO();
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    private Specification<Order> toOrderSpecification(Long userId, String state) {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (userId != null)
                predicates.add(cb.equal(root.get("user").get("id"), userId));
            if (!StringUtils.isEmpty(state))
                predicates.add(cb.equal(root.get("status").as(String.class), state.toUpperCase()));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
