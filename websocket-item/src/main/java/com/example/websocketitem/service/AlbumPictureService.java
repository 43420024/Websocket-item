package com.example.websocketitem.service;

import com.example.websocketitem.model.AlbumPicture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.model.ResponseMap;

import java.util.List;

/**
* @author cd
* @description 针对表【tcd_album_picture(相册图片)】的数据库操作Service
* @createDate 2023-08-22 17:25:01
*/
public interface AlbumPictureService extends IService<AlbumPicture> {
    ResponseMap addAlbumPicture(AlbumPicture albumPicture);

    ResponseMap deleteAlbumPicture(Long id);

    ResponseMap deleteAlbumPictureList(List<AlbumPicture> albumPictures);

    ResponseMap listAlbumPicture(Long albumId,Integer page,Integer size);

    ResponseMap deleteAlbumPictureWithAlbumId(Long albumId);
}
