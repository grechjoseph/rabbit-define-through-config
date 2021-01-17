package com.jg.rabbitdefinethroughconfig.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TopicChannels {

    String MY_TOPIC_EXCHANGE = "my-topic-exchange";
    String MY_TOPIC_QUEUE_1 = "my-topic-queue-1";
    String MY_TOPIC_QUEUE_2 = "my-topic-queue-2";
    String MY_TOPIC_QUEUE_3 = "my-topic-queue-3";

    @Output(MY_TOPIC_EXCHANGE)
    MessageChannel myTopicExchange();

    @Input(MY_TOPIC_QUEUE_1)
    SubscribableChannel myTopicQueue1();

    @Input(MY_TOPIC_QUEUE_2)
    SubscribableChannel myTopicQueue2();

    @Input(MY_TOPIC_QUEUE_3)
    SubscribableChannel myTopicQueue3();

}
