package com.jg.rabbitdefinethroughconfig.api.controller;

import lombok.Data;

@Data
public class MessageRequest {

    private ExchangeType exchangeType;
    private String header1;
    private String header2;
    private String value1;

}
