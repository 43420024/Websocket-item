package com.example.websocketitem.service;

import com.example.websocketitem.model.Album;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.SearchModel;

/**
* @author cd
* @description 针对表【tcd_album(相册)】的数据库操作Service
* @createDate 2023-08-25 16:55:50
*/
public interface AlbumService extends IService<Album> {
    ResponseMap addAlbum(Album album);

    ResponseMap updateAlbum(Album album);

    ResponseMap listAlbum(Long userId, Integer page,Integer size);

    ResponseMap deleteAlbum(Long id);

    ResponseMap searchAlbum(SearchModel searchModel);
}
