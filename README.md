<h1>Defining RabbitMQ Bindings through Config</h1>
In this project, each exchange and queue properties are defined in the application.yml file.

<h2>Getting Started</h2>
1. Add dependency: <b>spring-cloud-stream-binder-rabbit</b>
2. Create the channel class (eg: DirectChannels.class) in your code.
3. Add @EnableBinding(DirectChannels.class) for each interface class with channels defined.
4. Publishing to channel: directChannels.myChannel().send(MessageBuilder.withPayload(x).build()). (replace directChannel with the channel class being used).
4. Consumer: Annotate method with @StreamListener(<CHANNEL-NAME>), where CHANNEL-NAME is a String name of the channel being listened to in this listener.

<h2>Configuration</h2>
<h3>Example</h3>
Considering wanting to create a <b>fanout</b> exchange with name <b>my-fanout-exchange</b> with one queue bound to it, called <b>my-queue</b>.

We define the producer (sender) side configuration as follows in order to have our channel <b>my-channel-name</b> within our code pointing to the fanout exchange:

<b>spring.cloud.stream.bindings.<my-channel-name>.destination = my-fanout-exchange</b>



<hr/>
Likewise, we have to define this configuration on the consumer side, together with defining which queue to have the consumer subscribe to in order to subscribe to the exchange:

<b>spring.cloud.stream.bindings.<my-channel-name>.destination = my-fanout-exchange</b>

<b>spring.cloud.stream.bindings.<my-channel-name>.group = my-queue</b>



<hr/>
To have the application create the exchange on Rabbit on startup of the producer, we add the following properties:

<b>spring.cloud.stream.rabbit.<my-channel-name>.producer.exchangeType = fanout</b>



<hr/>
Likewise, this can be set on the consumer application with:

<b>spring.cloud.stream.rabbit.<my-channel-name>.consumer.exchangeType = fanout</b>







<h3>Direct Exchange Properties</h3>
<b>spring.cloud.stream.bindings.my-direct-exchange.destination = myDirectExchange</b>

Binds channel <b>my-direct-exchange</b> to exchange <b>myDirectExchange</b>.



<hr/>
<b>spring.cloud.stream.bindings.my-direct-queue-1.destination = myDirectExchange</b>

<b>spring.cloud.stream.bindings.my-direct-queue-1.group = myDirectQueue1</b>

Binds queue <b>myDirectQueue1</b> to exchange <b>myDirectExchange</b>, binding this to channel <b>my-direct-queue-1</b>.

(The same applies for my-direct-queue-2 and my-direct-queue-3).



<hr/>
<b>spring.cloud.stream.rabbit.my-direct-exchange.producer.exchangeType = direct</b>

<b>spring.cloud.stream.rabbit.my-direct-exchange.producer.routingKeyExpression: 'payload.value1</b>

On startup of producer, creates exchange <b>myDirectExchange</b> with type <b>direct</b>, setting it to pass the value of 'payload.value1'as the routingKey.



<hr/>
<b>spring.cloud.stream.rabbit.my-direct-queue-1.consumer.exchangeType = direct</b>

<b>spring.cloud.stream.rabbit.my-direct-queue-1.consumer.bindingRoutingKey = 'abc'</b>

On startup of producer, creates exchange <b>myDirectExchange</b> with type <b>direct</b>, setting it to pass the value of 'payload.value1' as the routingKey.
Also, creates queue <b>myDirectQueue1</b> and binds it to exchange <b>myDirectExchange</b> with <b>routingKey='abc'</b>.








<h3>Fanout Exchange Properties</h3>
<b>spring.cloud.stream.bindings.my-fanout-exchange.destination = myFanoutExchange</b>

Binds channel <b>my-fanout-exchange</b> to exchange <b>myFanoutExchange</b>.



<hr/>
<b>spring.cloud.stream.bindings.my-fanout-queue-1.destination = myFanoutExchange</b>

<b>spring.cloud.stream.bindings.my-fanout-queue-1.group = myFanoutQueue1</b>

Binds queue <b>myFanoutQueue1</b> to exchange <b>myFanoutExchange</b>, binding this to channel <b>my-fanout-queue-1</b>.

(The same applies for my-fanout-queue-2 and my-fanout-queue-3).



<hr/>
<b>spring.cloud.stream.rabbit.my-fanout-exchange.producer.exchangeType = fanout</b>

On startup of producer, creates exchange <b>myFanoutExchange</b> with type <b>fanout</b>.



<hr/>
<b>spring.cloud.stream.rabbit.my-fanout-queue-1.consumer.exchangeType = fanout</b>

On startup of producer, creates exchange <b>myFanoutExchange</b> with type <b>fanout</b>.
Also, creates queue <b>myFanoutQueue1</b> and binds it to exchange <b>myDirectExchange</b>.







<h3>Header Exchange Properties</h3>
<b>spring.cloud.stream.bindings.my-header-exchange.destination = myDirectExchange</b>

Binds channel <b>my-header-exchange</b> to exchange <b>myHeaderExchange</b>.



<hr/>
<b>spring.cloud.stream.bindings.my-header-queue-1.destination = myHeaderExchange</b>

<b>spring.cloud.stream.bindings.my-header-queue-1.group = myHeaderQueue1</b>

Binds queue <b>myHeaderQueue1</b> to exchange <b>myHeaderExchange</b>, binding this to channel <b>my-header-queue-1</b>.

(The same applies for my-header-queue-2 and my-header-queue-3).



<hr/>
<b>spring.cloud.stream.rabbit.my-header-exchange.producer.exchangeType = headers</b>

On startup of producer, creates exchange <b>myHeaderExchange</b> with type <b>headers</b>.



<hr/>
<b>spring.cloud.stream.rabbit.my-header-queue-1.consumer.exchangeType = headers</b>

<b>spring.cloud.stream.rabbit.my-header-queue-1.consumer.queueBindingArguments.x-match = all</b>
<b>spring.cloud.stream.rabbit.my-header-queue-1.consumer.queueBindingArguments.header1 = '1'</b>
<b>spring.cloud.stream.rabbit.my-header-queue-1.consumer.queueBindingArguments.header2 = '1'</b>

On startup of producer, creates exchange <b>myHeaderExchange</b> with type <b>headers</b>.
Also, creates queue <b>myHeaderQueue1</b> and binds it to exchange <b>myHeaderExchange</b>, given <b>ALL</b> headers match the values specified, ie: header1='1' and header2='1'.







<h3>Topic Exchange Properties</h3>
<b>spring.cloud.stream.bindings.my-topic-exchange.destination = myTopicExchange</b>

Binds channel <b>my-topic-exchange</b> to exchange <b>myTopicExchange</b>.



<hr/>
<b>spring.cloud.stream.bindings.my-topic-queue-1.destination = myTopicExchange</b>

<b>spring.cloud.stream.bindings.my-topic-queue-1.group = myTopicQueue1</b>

Binds queue <b>myTopicQueue1</b> to exchange <b>myTopicExchange</b>, binding this to channel <b>my-topic-queue-1</b>.

(The same applies for my-topic-queue-2 and my-topic-queue-3).



<hr/>
<b>spring.cloud.stream.rabbit.my-topic-exchange.producer.exchangeType = topic</b>

<b>spring.cloud.stream.rabbit.my-topic-exchange.producer.routingKeyExpression: 'payload.value1</b>

On startup of producer, creates exchange <b>myTopicExchange</b> with type <b>topic</b>, setting it to pass the value of 'payload.value1'as the routingKey.



<hr/>
<b>spring.cloud.stream.rabbit.my-topic-queue-1.consumer.exchangeType = topic</b>

<b>spring.cloud.stream.rabbit.my-topic-queue-1.consumer.bindingRoutingKey = '1'</b>

On startup of producer, creates exchange <b>myTopicExchange</b> with type <b>topic</b>, setting it to pass the value of 'payload.value1' as the routingKey.
Also, creates queue <b>myTopicQueue1</b> and binds it to exchange <b>myTopicExchange</b> with <b>routingKey='abc'</b>.