package com.example.controller;

import com.example.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {

    @Value("${my.message}")
    private String message;

    @GetMapping("/message/{name}")
    public Message getMessage(@PathVariable String name) {

        log.debug("Sample DEBUG Message");
        log.info("Sample INFO Message");
        log.trace("Sample TRACE Message");
        log.warn("Sample WARN Message");
        log.error("Sample ERROR Message");

        return Message.builder().name(name).message(message).build();
    }
}
