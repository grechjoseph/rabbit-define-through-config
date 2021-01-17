package com.jg.rabbitdefinethroughconfig.api.controller;

import com.jg.rabbitdefinethroughconfig.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessagingController {

    private final List<ExchangeService> exchangeServices;

    @PostMapping
    public void sendMessage(@RequestBody final MessageRequest request) {
        log.info("Identifying service to service messageRequest: {}", request);
        exchangeServices.stream()
                .filter(service -> service.supportsExchangeType(request.getExchangeType()))
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new)
                .sendMessage(request);
    }

}
