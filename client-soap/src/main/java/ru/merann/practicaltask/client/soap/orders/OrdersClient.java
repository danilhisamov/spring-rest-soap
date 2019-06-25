package ru.merann.practicaltask.client.soap.orders;

import org.springframework.util.StringUtils;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import ru.merann.practicaltask.client.soap.orders.wsdl.*;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.stream.Collectors;

public class OrdersClient extends WebServiceGatewaySupport {
    private final String uri = "http://localhost:8080/ws/orders";
    private final String sacTemplate = "http://practicaltask.merann.ru/soap/jaxb/%s";

    public GetOrderResponse getOrder(Long id) {
        GetOrderRequest request = new GetOrderRequest();
        request.setOrderId(id);

        return (GetOrderResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, request,
                        new SoapActionCallback(String.format(sacTemplate, "getOrderRequest")));
    }

    public GetOrdersResponse getOrders(Long userId, String state) {
        GetOrdersRequest request = new GetOrdersRequest();
        if (userId != null)
            request.setUserId(new JAXBElement<>(createQName("userId"), Long.class, userId));
        if (!StringUtils.isEmpty(state))
            request.setState(new JAXBElement<>(createQName("state"), State.class, State.fromValue(state.toUpperCase())));

        return (GetOrdersResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, request,
                        new SoapActionCallback(String.format(sacTemplate, "getOrdersRequest")));
    }

    public CreateOrderResponse createOrder(Long modelId, String options, Long userId) {
        CreateOrderRequest request = new CreateOrderRequest();
        Order newOrder = new Order();
        newOrder.setModel(createNamedById(modelId));
        newOrder.getSelectedOptions().addAll(Arrays.stream(options.split(",")).map(Long::parseLong).map(this::createNamedById).collect(Collectors.toList()));
        newOrder.setUser(createNamedById(userId));
        request.setOrder(newOrder);

        return (CreateOrderResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, request,
                        new SoapActionCallback(String.format(sacTemplate, "createOrderRequest")));
    }

    public UpdateOrderResponse updateOrder(Long orderId, Long modelId, String options, String status) {
        UpdateOrderRequest request = new UpdateOrderRequest();
        Order order = getOrder(orderId).getOrder();

        order.setModel(createNamedById(modelId));

        order.getSelectedOptions().clear();
        order.getSelectedOptions().addAll(Arrays.stream(options.split(",")).map(Long::parseLong).map(this::createNamedById).collect(Collectors.toList()));

        order.setState(State.fromValue(status.toUpperCase()));
        request.setOrder(order);

        return (UpdateOrderResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, request,
                        new SoapActionCallback(String.format(sacTemplate, "updateOrderRequest")));
    }

    public DeleteOrderResponse deleteOrder(Long id) {
        DeleteOrderRequest request = new DeleteOrderRequest();
        request.setOrderId(id);

        return (DeleteOrderResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, request,
                        new SoapActionCallback(String.format(sacTemplate, "deleteOrderRequest")));
    }

    private QName createQName(String localPart) {
        return new QName("http://practicaltask.merann.ru/soap/jaxb", localPart, "jaxb");
    }

    private Named createNamedById(long id) {
        Named named = new Named();
        named.setId(id);
        return named;
    }
}
