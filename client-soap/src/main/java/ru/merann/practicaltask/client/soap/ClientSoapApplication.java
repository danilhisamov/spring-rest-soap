package ru.merann.practicaltask.client.soap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class ClientSoapApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ClientSoapApplication.class).web(WebApplicationType.NONE).run(args);
	}
}
