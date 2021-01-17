package com.jg.rabbitdefinethroughconfig.service.impl;

import com.jg.rabbitdefinethroughconfig.api.controller.ExchangeType;
import com.jg.rabbitdefinethroughconfig.api.controller.MessageRequest;
import com.jg.rabbitdefinethroughconfig.channels.HeaderChannels;
import com.jg.rabbitdefinethroughconfig.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
@EnableBinding(HeaderChannels.class)
public class HeaderExchangeService implements ExchangeService {

    private final HeaderChannels headerChannels;

    @Override
    public boolean supportsExchangeType(final ExchangeType exchangeType) {
        return ExchangeType.HEADER.equals(exchangeType);
    }

    @Override
    public void sendMessage(final MessageRequest request) {
        log.info("{} - Sending message to exchange.", this.getClass().getSimpleName());
        headerChannels.myHeaderExchange().send(MessageBuilder
                .withPayload(request)
                .setHeader("header1", request.getHeader1())
                .setHeader("header2", request.getHeader2())
                .build());
    }

    @Override
    @StreamListener(HeaderChannels.MY_HEADER_QUEUE_1)
    public void listenToQueue1(final MessageRequest request) {
        log.info("{} received message: {}", HeaderChannels.MY_HEADER_QUEUE_1, request.getValue1());
    }

    @Override
    @StreamListener(HeaderChannels.MY_HEADER_QUEUE_2)
    public void listenToQueue2(final MessageRequest request) {
        log.info("{} received message: {}", HeaderChannels.MY_HEADER_QUEUE_2, request.getValue1());
    }

    @Override
    @StreamListener(HeaderChannels.MY_HEADER_QUEUE_3)
    public void listenToQueue3(final MessageRequest request) {
        log.info("{} received message: {}", HeaderChannels.MY_HEADER_QUEUE_3, request.getValue1());
    }

}
