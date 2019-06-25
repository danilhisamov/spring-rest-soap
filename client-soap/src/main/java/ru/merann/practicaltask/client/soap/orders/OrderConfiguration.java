package ru.merann.practicaltask.client.soap.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class OrderConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.merann.practicaltask.client.soap.orders.wsdl");
        return marshaller;
    }

    @Bean
    public OrdersClient orderClient(Jaxb2Marshaller marshaller) {
        OrdersClient client = new OrdersClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
