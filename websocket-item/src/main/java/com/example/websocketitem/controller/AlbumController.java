package com.example.websocketitem.controller;

import com.example.websocketitem.model.Album;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.SearchModel;
import com.example.websocketitem.service.AlbumPictureService;
import com.example.websocketitem.service.AlbumService;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Resource
    AlbumService albumService;
    @Resource
    AlbumPictureService albumPictureService;
    /**
     * 添加相册
     * */
    @PostMapping
    public ResponseMap addAlbum(@RequestBody Album album){
        return albumService.addAlbum(album);
    }
    /**
     * 更新相册(用户修改相册名称，女性用户改变相册开放状态，系统管理修改相册冻结状态
     * */
    @PutMapping
    public ResponseMap updateAlbum(@RequestBody Album album){
        return albumService.updateAlbum(album);
    }
    /**
     * 根据相册编号删除相册
     * */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseMap deleteAlbum(@PathVariable Long id){
        return albumService.deleteAlbum(id);
    }
    /**
     * 根据用户编号编号获取相册分页列表
     * */
    @GetMapping("/list/{userId}/{page}/{size}")
    public ResponseMap listAlbum(@PathVariable Long userId,
                                 @PathVariable Integer page,
                                 @PathVariable Integer size){
        return albumService.listAlbum(userId,page,size);
    }
    /**
     * 模糊全匹配+按时间相册查询
     * */
//    @GetMapping("/search")
//    public ResponseMap searchAlbum(@RequestBody SearchModel searchModel){
//        return albumService.searchAlbum(searchModel);
//    }
    /**
     * 获取相册未审核用户编号及未审核相册个数
     * */
    @GetMapping("/stat")
    public ResponseMap statAlbum(){
        return albumService.statAlbum();
    }
}
