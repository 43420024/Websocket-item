package com.example.websocketitem.controller;

import com.example.websocketitem.factory.EntityFactory;
import com.example.websocketitem.model.AlbumPicture;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.service.AlbumPictureService;
import com.example.websocketitem.service.AlbumService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/albumPicture")
public class AlbumPictureController {
    @Resource
    AlbumPictureService albumPictureService;
    @Resource
    AlbumService albumService;
    /**
     * 相册图片上传
     * */
    @PostMapping(value = "/upload")
    //@RequestParam指向前端input file的name,加入HttpServletRequest请求
    public Map<String,Object> upload(@RequestParam("File") MultipartFile[] multipartFiles,@RequestParam("AlbumId") Long albumId, HttpServletRequest request) throws IOException {
        //设置当前日期
        String uploaddate= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //设置文件上传保存文件路径：保存在项目运行目录下的uploadFile文件夹+当前日期
        String savepath = "./images/";
        //创建文件夹,当文件夹不存在时，创建文件夹
        File folder = new File(savepath);
        if(!folder.isDirectory()){
            folder.mkdir();
        }
        String realPath = folder.getCanonicalPath();
        //建立一个listmap的返回参数
        Map<String,Object> map=new HashMap<>();
        //建立一个循环分别接收多文件
        for(MultipartFile file:multipartFiles){
            albumService.capacityReduce(albumId);
            //重命名上传的文件,为避免重复,我们使用UUID对文件分别进行命名
            String oldname=file.getOriginalFilename();//getOriginalFilename()获取文件名带后缀
            //UUID去掉中间的"-",并将原文件后缀名加入新文件
            String newname= UUID.randomUUID().toString().replace("-","")
                    +oldname.substring(oldname.lastIndexOf("."));
            //建立每一个文件上传的返回参数
            //文件保存操作
            try {
                file.transferTo(new File(realPath+"/"+newname));
                //建立新文件路径,在前端可以直接访问如http://localhost:8080/uploadFile/2021-07-16/新文件名(带后缀)
                String filepath="/images/"+newname;
                //写入返回参数

                AlbumPicture albumPicture = EntityFactory.createAlbumPicture();
                albumPicture.setAlbumId(albumId);
                albumPicture.setPath(filepath);
                albumPicture.setCreateTime(new Date());
                ResponseMap responseMap = albumPictureService.addAlbumPicture(albumPicture);
                map.put("oldname",oldname);
                map.put("newname",newname);
                map.put("filepath",filepath);
                map.put("result", responseMap.getFlag() ?"图片上传成功,数据库写入成功":"图片上传成功,数据库写入失败");
            }catch (IOException ex){
                //操作失败报错并写入返回参数
                ex.printStackTrace();
                map.put("oldname",oldname);
                map.put("newname",newname);
                map.put("filepath","");
                map.put("result","失败!");
            }
        }
        //将执行结果返回
        return map;
    }
    /**
     * 删除单个相册图片
     * */
    @DeleteMapping("/{id}")
    public ResponseMap deleteAlbumPicture(@PathVariable Long id){
        return albumPictureService.deleteAlbumPicture(id);
    }
    /**
     * 删除多个相册图片
     * */
    @DeleteMapping("/list")
    public ResponseMap deleteAlbumPicture(@RequestBody List<AlbumPicture> albumPictures){
        return albumPictureService.deleteAlbumPictureList(albumPictures);
    }
    /**
     * 根据相册编号获取相册图片
     * */
    @GetMapping("/album/{albumId}")
    public ResponseMap listAlbumPicture(@PathVariable Long albumId){
        return albumPictureService.getAlbumPicture(albumId);
    }
    /**
     * 根据相册编号统计相册图片数量
     * */
    @GetMapping("/count/{albumId}")
    public ResponseMap countAlbumPicture(@PathVariable Long albumId){
        return albumPictureService.countAlbumPicture(albumId);
    }
}
