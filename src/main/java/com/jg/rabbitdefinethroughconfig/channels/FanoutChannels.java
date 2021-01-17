package com.jg.rabbitdefinethroughconfig.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface FanoutChannels {

    String MY_FANOUT_EXCHANGE = "my-fanout-exchange";
    String MY_FANOUT_QUEUE_1 = "my-fanout-queue-1";
    String MY_FANOUT_QUEUE_2 = "my-fanout-queue-2";
    String MY_FANOUT_QUEUE_3 = "my-fanout-queue-3";

    @Output(MY_FANOUT_EXCHANGE)
    MessageChannel myFanoutExchange();

    @Input(MY_FANOUT_QUEUE_1)
    SubscribableChannel myFanoutQueue1();

    @Input(MY_FANOUT_QUEUE_2)
    SubscribableChannel myFanoutQueue2();

    @Input(MY_FANOUT_QUEUE_3)
    SubscribableChannel myFanoutQueue3();

}
