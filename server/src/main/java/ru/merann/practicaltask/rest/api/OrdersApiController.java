package ru.merann.practicaltask.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.merann.practicaltask.rest.model.OrderDTO;
import ru.merann.practicaltask.rest.service.OrderRestService;

import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-06-21T13:42:46.222+03:00[Europe/Moscow]")

@Controller
@RequestMapping("${openapi.swaggerCarShowroom.base-path:/api/v1}")
public class OrdersApiController implements OrdersApi {

    private final NativeWebRequest request;

    private OrderRestService orderService;

    @org.springframework.beans.factory.annotation.Autowired
    public OrdersApiController(NativeWebRequest request, OrderRestService orderService) {
        this.request = request;
        this.orderService = orderService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO) {
        return new ResponseEntity<OrderDTO>(orderService.createOrder(orderDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<OrderDTO> getOrderById(Long orderId) {
        return new ResponseEntity<OrderDTO>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getOrders(Long userId, String state) {
        return new ResponseEntity<List<OrderDTO>>(orderService.getOrders(userId, state), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDTO> updateOrder(Long orderId, OrderDTO orderDTO) {
        return new ResponseEntity<OrderDTO>(orderService.updateOrder(orderId, orderDTO), HttpStatus.OK);
    }

}
