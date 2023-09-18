package com.example.websocketitem.controller;

import com.example.websocketitem.service.AdministratorsService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrators")
public class AdministratorsController {
    @Resource
    private AdministratorsService administratorsService;
    @GetMapping("/logon")
    public Result userLogon(@RequestParam("phone") Long manageAccounts,@RequestParam("password") String passwords){
        return administratorsService.userLogon(manageAccounts, passwords);
    }

}
