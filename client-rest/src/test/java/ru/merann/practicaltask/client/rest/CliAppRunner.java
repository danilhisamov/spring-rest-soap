package ru.merann.practicaltask.client.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CliAppRunner implements ApplicationRunner {

    private static Logger log = LoggerFactory.getLogger(CliAppRunner.class);

    public CliAppRunner() {
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("CliAppRunner started");
    }
}
