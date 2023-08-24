package com.example.websocketitem.controller;

import com.example.websocketitem.domain.GiftBackpack;
import com.example.websocketitem.service.GiftBackpackService;
import com.example.websocketitem.service.GiftService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gifts")
@Slf4j
public class GiftController {

    @Resource
    private GiftService giftService;
    @Resource
    private GiftBackpackService giftBackpackService;

    /**
     * 礼物展示
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Result query(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ){
       return giftService.select(pageNum,pageSize);
    }

    /**
     * 购买礼物
     * @param giftBackpack
     * @return
     */
    @PostMapping("/buy")
    public Result buyGift(@RequestBody GiftBackpack giftBackpack){
        return giftBackpackService.addGift(giftBackpack);
    }

    @GetMapping("/{userId}")
    public Result myGiftQuery(@PathVariable Long userId){
        return giftBackpackService.select(userId);
    }
}
