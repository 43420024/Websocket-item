package com.example.websocketitem.controller;

import com.example.websocketitem.domain.Gift;
import com.example.websocketitem.domain.GiftType;
import com.example.websocketitem.service.GiftService;
import com.example.websocketitem.service.GiftTypeService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("gifts")
@Slf4j
public class GiftController {

    @Resource
    private GiftService giftService;
    @Resource
    private GiftTypeService giftTypeService;


    /**
     * 礼物展示
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Result query(
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String name,
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return giftService.select(pageNum, pageSize, type,status,name);
    }

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public Result select(@PathVariable Integer id) {
        return giftService.queryOne(id);
    }

    /**
     * 添加礼物
     *
     * @param gift
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Gift gift) {
        return giftService.addGift(gift);
    }

    /**
     * 修改礼物
     *
     * @param gift
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Gift gift) {
        return giftService.updateGift(gift);
    }

    @PutMapping("/updateStatus")
    public Result updateStatus(@RequestBody Gift gift) {
        return giftService.updateStatus(gift);
    }

    /**
     * 删除礼物
     *
     * @param id
     * @return
     */
    @DeleteMapping("/gift/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean delete = giftService.removeById(id);
        return delete ? Result.success("删除成功！") : Result.error("删除失败！");
    }

    //礼物类型

    /**
     * 添加类型
     *
     * @param giftType
     * @return
     */
    @PostMapping("/addType")
    public Result addType(@RequestBody GiftType giftType) {
        return giftTypeService.add(giftType);
    }

    /**
     * 修改类型
     *
     * @param giftType
     * @return
     */
    @PutMapping("/updateType")
    public Result updateType(@RequestBody GiftType giftType) {
        return giftTypeService.updateType(giftType);
    }

    /**
     * 礼物类型无分页遍历
     * @return
     */
    @GetMapping("/queryList")
    public Result queryList() {
        List<GiftType> list = giftTypeService.list();
        return Result.success(list);
    }

    @DeleteMapping("/deleteType/{id}")
    public Result deleteType(@PathVariable Integer id){
        final boolean type = giftTypeService.removeById(id);
        return type?Result.success("删除成功！"):Result.error("删除失败！");
    }
    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @PostMapping("/file")
    public Result uploadFile(@RequestParam(value = "file") MultipartFile file, HttpServletRequest req) {
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        File folder = new File(realPath);
        if (!folder.isDirectory()) {
            if (!folder.mkdirs()) {
                return Result.error("文件夹创建失败");
            }
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String oldName = file.getOriginalFilename();
        String type = oldName.substring(oldName.lastIndexOf("."));
        if (!".jpg".equals(type) && !".png".equals(type) && !".jpeg".equals(type) && !".gif".equals(type)) {
            return Result.error("文件类型错误");
        }


        String newName = uuid + type;
        try {
            file.transferTo(new File(folder, newName));
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + newName;
            return Result.success(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("上传失败!");
    }
}
