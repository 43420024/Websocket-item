package com.example.websocketitem.controller;


import com.example.websocketitem.service.TrendsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("Trends")
public class TrendsController {
    @Resource
    private TrendsService trendsService;


}
