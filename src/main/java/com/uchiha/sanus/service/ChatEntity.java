package com.uchiha.sanus.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会话id
     */
    String chatId;

    /**
     * 会话的角色类型
     */
    String type;

    /**
     * 会话内容
     */
    String text;
}

