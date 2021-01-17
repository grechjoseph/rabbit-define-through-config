package com.jg.rabbitdefinethroughconfig.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface DirectChannels {

    String MY_DIRECT_EXCHANGE = "my-direct-exchange";
    String MY_DIRECT_QUEUE_1 = "my-direct-queue-1";
    String MY_DIRECT_QUEUE_2 = "my-direct-queue-2";
    String MY_DIRECT_QUEUE_3 = "my-direct-queue-3";

    @Output(MY_DIRECT_EXCHANGE)
    MessageChannel myDirectExchange();

    @Input(MY_DIRECT_QUEUE_1)
    SubscribableChannel myDirectQueue1();

    @Input(MY_DIRECT_QUEUE_2)
    SubscribableChannel myDirectQueue2();

    @Input(MY_DIRECT_QUEUE_3)
    SubscribableChannel myDirectQueue3();

}
