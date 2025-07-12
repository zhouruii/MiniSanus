package com.uchiha.sanus.model;

import lombok.Data;
import lombok.Getter;

@Getter
public class GetStartedRequest {

    public String modelName;

    public String message;

    public String chatId;

}
