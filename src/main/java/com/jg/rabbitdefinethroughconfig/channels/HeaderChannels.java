package com.jg.rabbitdefinethroughconfig.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface HeaderChannels {

    String MY_HEADER_EXCHANGE = "my-header-exchange";
    String MY_HEADER_QUEUE_1 = "my-header-queue-1";
    String MY_HEADER_QUEUE_2 = "my-header-queue-2";
    String MY_HEADER_QUEUE_3 = "my-header-queue-3";

    @Output(MY_HEADER_EXCHANGE)
    MessageChannel myHeaderExchange();

    @Input(MY_HEADER_QUEUE_1)
    SubscribableChannel myHeaderQueue1();

    @Input(MY_HEADER_QUEUE_2)
    SubscribableChannel myHeaderQueue2();

    @Input(MY_HEADER_QUEUE_3)
    SubscribableChannel myHeaderQueue3();

}
