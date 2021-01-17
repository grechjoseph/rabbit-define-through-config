package com.jg.rabbitdefinethroughconfig.service;

import com.jg.rabbitdefinethroughconfig.api.controller.ExchangeType;
import com.jg.rabbitdefinethroughconfig.api.controller.MessageRequest;

public interface ExchangeService {

    boolean supportsExchangeType(final ExchangeType exchangeType);
    void sendMessage(final MessageRequest request);
    void listenToQueue1(final MessageRequest request);
    void listenToQueue2(final MessageRequest request);
    void listenToQueue3(final MessageRequest request);

}
