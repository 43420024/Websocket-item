package com.example.websocketitem.controller;

import com.example.websocketitem.model.Agreement;
import com.example.websocketitem.service.AgreementService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agreement")
public class AgreementController {
    @Resource
    private AgreementService agreementService;
    /**
     * 添加一个协议内容
     * */
    @PostMapping("/agreementAdd")
    public Result agreementAdd(@RequestBody Agreement agreement){
        return agreementService.agreementAdd(agreement);
    }
    /**
     * 删除一个协议
     * */
    @DeleteMapping("/agreementDelete")
    public Result agreementDelete(@RequestParam Integer id){
        return agreementService.agreementDelete(id);
    }
    /**
     * 修改一个协议
     * */
    @PutMapping("/agreementUpdate")
    public Result agreementUpdate(@RequestBody Agreement agreement){
        return agreementService.agreementUpdate(agreement);
    }
    /**
     * 查询单个协议
     * */
    @GetMapping("/agreementById")
    public Result agreementById(@RequestParam Integer id){
        return agreementService.agreementById(id);
    }
    /**
     * 查询全部协议
     * */
    @GetMapping("/agreementByAll")
    public Result agreementByAll(){
        return agreementService.agreementByAll();
    }
}
