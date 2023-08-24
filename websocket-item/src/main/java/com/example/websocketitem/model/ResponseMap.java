package com.example.websocketitem.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class ResponseMap {
    private Boolean flag;
    private String message;
    private Object data;
}
