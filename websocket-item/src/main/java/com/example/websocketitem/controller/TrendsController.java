package com.example.websocketitem.controller;


import com.example.websocketitem.model.Trends;
import com.example.websocketitem.service.TrendsService;
import com.example.websocketitem.utils.DataType;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("Trends")
@Log4j
public class TrendsController {
    @Resource
    private TrendsService trendsService;


    //上传图片等资源文件
    @ResponseBody
    @RequestMapping(value = "/upload/uploadFile")
    public Map uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        if (file != null) {
//            String webapp = request.getSession().getServletContext().getRealPath("/");
            //存放到项目静态资源下
            String webapp = "src/main/resources/";
            try {
                //图片名字
                String substring = file.getOriginalFilename();
//                System.out.println(substring);
                //使用uuid替代原来名字
                String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                // 图片的路径+文件名称
//                String fileName = "/static/upload/" + substring;
                //使用uuid上传将上传的图片重命名，但是遇到改名之后上传较慢，需要等待传输才能回显
                String uuidName = uuid + "." + substring.substring(substring.lastIndexOf(".") + 1);
//                System.out.println(uuidName);
                String fileName = "/static/upload/" + uuidName;
//                System.out.println(fileName);
                // 图片的在服务器上面的物理路径
                File destFile = new File(webapp, fileName);
//                log.info("真实路径{}",destFile);
                // 生成upload目录
                File parentFile = destFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();// 自动生成upload目录
                }
                // 把上传的临时图片，复制到当前项目的webapp路径
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destFile));
                map = new HashMap<>();
                map2 = new HashMap<>();
                map.put("code", 0);//0表示成功，1失败
                map.put("msg", "上传成功");//提示消息
                map.put("data", map2);
//                map2.put("src", fileName);//图片url
//                map2.put("src", "/upload/" + substring);//图片url
                map2.put("src", "/upload/"+uuidName);//图片url
//                map2.put("title", substring);//图片名称，这个会显示在输入框里
                map2.put("title", uuidName);//图片名称，这个会显示在输入框里
                //log.info("图片地址为{}",uuidName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }


    //动态保存
    @PostMapping("addOrEdit")
    @ResponseBody
    public DataType trendsSave(@RequestBody Trends trends) {
        System.out.println(trends+"!!!!!!!!!");
        return trendsService.savaTrends(trends);
    }

    //动态删除
    @DeleteMapping("/trendsDelete/{id}")
    @ResponseBody
    public DataType trendsDelete(@PathVariable("id") Long id){
        return trendsService.deleteTrends(id);
    }


    //查询个人全部动态
    @GetMapping("{userid}")
    @ResponseBody
    public DataType allQueryTrends(@PathVariable("userid") Long userid){
        return trendsService.allQueryTrends(userid);
    }


    //动态点赞（一人点赞一次还是可以点赞多次）
    @GetMapping("/Likes/{id}")
    @ResponseBody
    public DataType LikesTrends(@PathVariable("id") Long id){
        return trendsService.addCount(id);
    }



}
