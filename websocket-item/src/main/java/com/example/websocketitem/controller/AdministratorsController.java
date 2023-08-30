package com.example.websocketitem.controller;

import com.example.websocketitem.service.AdministratorsService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("administrators")
public class AdministratorsController {
    @Resource
    private AdministratorsService administratorsService;
    @GetMapping("/logon")
    public Result userLogon(@RequestParam Long manageAccounts,@RequestParam String passwords){
        return administratorsService.userLogon(manageAccounts, passwords);
    }
}
