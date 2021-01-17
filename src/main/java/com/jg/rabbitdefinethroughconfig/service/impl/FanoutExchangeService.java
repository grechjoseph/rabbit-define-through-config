package com.jg.rabbitdefinethroughconfig.service.impl;

import com.jg.rabbitdefinethroughconfig.api.controller.ExchangeType;
import com.jg.rabbitdefinethroughconfig.api.controller.MessageRequest;
import com.jg.rabbitdefinethroughconfig.channels.FanoutChannels;
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
@EnableBinding(FanoutChannels.class)
public class FanoutExchangeService implements ExchangeService {

    private final FanoutChannels fanoutChannels;

    @Override
    public boolean supportsExchangeType(final ExchangeType exchangeType) {
        return ExchangeType.FANOUT.equals(exchangeType);
    }

    @Override
    public void sendMessage(final MessageRequest request) {
        log.info("{} - Sending message to exchange.", this.getClass().getSimpleName());
        fanoutChannels.myFanoutExchange().send(MessageBuilder
                .withPayload(request)
                .setHeader("header1", request.getHeader1())
                .setHeader("header2", request.getHeader2())
                .build());
    }

    @Override
    @StreamListener(FanoutChannels.MY_FANOUT_QUEUE_1)
    public void listenToQueue1(final MessageRequest request) {
        log.info("{} received message: {}", FanoutChannels.MY_FANOUT_QUEUE_1, request.getValue1());
    }

    @Override
    @StreamListener(FanoutChannels.MY_FANOUT_QUEUE_2)
    public void listenToQueue2(final MessageRequest request) {
        log.info("{} received message: {}", FanoutChannels.MY_FANOUT_QUEUE_2, request.getValue1());
    }

    @Override
    @StreamListener(FanoutChannels.MY_FANOUT_QUEUE_3)
    public void listenToQueue3(final MessageRequest request) {
        log.info("{} received message: {}", FanoutChannels.MY_FANOUT_QUEUE_3, request.getValue1());
    }

}
