package com.example.websocketitem.controller;

import com.example.websocketitem.service.GiftService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gifts")
@Slf4j
public class GiftController {

    @Resource
    private GiftService giftService;

    @GetMapping("/list")
    public Result query(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ){
       return giftService.select(pageNum,pageSize);

    }
}
