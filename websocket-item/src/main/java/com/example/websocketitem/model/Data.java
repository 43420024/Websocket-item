package com.example.websocketitem.model;

import java.util.List;

@lombok.Data
public class Data {
    private User user;
    private UserInfo userInfo;

    private List<Message> messageList;
    private List<Message> collectList;
}
