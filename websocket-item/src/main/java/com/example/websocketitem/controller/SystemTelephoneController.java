package com.example.websocketitem.controller;

import com.example.websocketitem.model.SystemTelephone;
import com.example.websocketitem.service.SystemTelephoneService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("systemTelephone")
public class SystemTelephoneController {
    @Resource
    private SystemTelephoneService systemTelephoneService;
    /**
     * 添加一个电话号码
     * */
    @PostMapping("/systemTelephoneAdd")
    public Result systemTelephoneAdd(@RequestBody @Valid SystemTelephone systemTelephone){
        return systemTelephoneService.systemTelephoneAdd(systemTelephone);
    }
    /**
     * 删除一个电话
     * */
    @DeleteMapping("/systemTelephoneDelete")
    public Result systemTelephoneDelete(@RequestParam Integer id){
        return systemTelephoneService.systemTelephoneDelete(id);
    }

}
