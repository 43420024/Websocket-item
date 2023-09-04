package com.example.websocketitem.fileupload;

import cn.hutool.core.lang.UUID;
import com.example.websocketitem.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
@CrossOrigin
@Slf4j
public class UploadController {
    /**
     * 上传文档文件
     *
     * @param file
     * @return
     */
    @PostMapping("/file")
    public Result uploadFile(@RequestParam(value = "file") MultipartFile file, HttpServletRequest req) {
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        log.info("文件上传地址 {}",realPath);
        File folder = new File(realPath);
        if (!folder.isDirectory()) {
            if (!folder.mkdirs()) {
                return Result.error("文件夹创建失败");
            }
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String oldName = file.getOriginalFilename();
        String type = oldName.substring(oldName.lastIndexOf("."));
//        if (!".docx".equals(type) && !".pdf".equals(type) && !".xlsx".equals(type) && !".zip".equals(type)) {
//            return R.notFound("文件类型错误");
//        }
//        long size = file.getSize();
//        if (size > 5 * 1024 * 1024) {
//            return R.notFound("文件超过5M");
//        }

        String newName = uuid + type;
        try {
            file.transferTo(new File(folder, newName));
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + newName;
            Map<String, Object> map = new HashMap<>();
            map.put("oldName",oldName);
            map.put("newName",newName);
            map.put("filePath",filePath);
            return Result.ok("上传文件成功", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("上传失败!");
    }

    /*@PostMapping("/files")
    public Result uploadFiles(@RequestParam(value = "files") MultipartFile[] files, HttpServletRequest req) {
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        System.out.println(realPath);
        File folder = new File(realPath);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        ArrayList<Files> fileList = new ArrayList<>();
        String filePath;
        String type;
        if (files.length>0){
            for (MultipartFile file : files) {
                long size = file.getSize();
                String oldName = file.getOriginalFilename();
                type = oldName.substring(oldName.lastIndexOf("."));

                if (!".docx".equals(type) && !".pdf".equals(type) && !".xlsx".equals(type) && !".zip".equals(type)) {
                    filePath = "文件类型错误";
                    return Result.error(filePath);
                }
                if (size > 5 * 1024 * 1024) {
                    filePath = "文件超过5M";
                    return Result.error(filePath);
                }
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String newName = uuid + type;
                try {
                    file.transferTo(new File(folder, file.getOriginalFilename()));
                    filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + newName;
                    fileList.add(new Files(oldName,newName,filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return Result.success(fileList);
        }
        return Result.error("上传失败！");
    }*/
}