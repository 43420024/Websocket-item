package com.example.websocketitem.controller;

import com.example.websocketitem.model.Album;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.SearchModel;
import com.example.websocketitem.service.AlbumService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Resource
    AlbumService albumService;

    @PostMapping
    public ResponseMap addAlbum(@RequestBody Album album){
        return albumService.addAlbum(album);
    }
    @PutMapping
    public ResponseMap updateAlbum(@RequestBody Album album){
        return albumService.updateAlbum(album);
    }
    @DeleteMapping("/{id}")
    public ResponseMap deleteAlbum(@PathVariable Long id){
        return albumService.deleteAlbum(id);
    }
    @GetMapping("/list/{userId}/{page}/{size}")
    public ResponseMap listAlbum(@PathVariable Long userId,
                                 @PathVariable Integer page,
                                 @PathVariable Integer size){
        return albumService.listAlbum(userId,page,size);
    }
    @GetMapping("/search")
    public ResponseMap searchAlbum(@RequestBody SearchModel searchModel){
        return albumService.searchAlbum(searchModel);
    }
}
