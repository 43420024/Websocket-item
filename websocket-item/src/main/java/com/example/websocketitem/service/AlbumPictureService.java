package com.example.websocketitem.service;

import com.example.websocketitem.model.AlbumPicture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.model.ResponseMap;

import java.util.List;

/**
* @author cd
* @description 针对表【tcd_album_picture(相册图片)】的数据库操作Service
* @createDate 2023-08-25 16:50:22
*/
public interface AlbumPictureService extends IService<AlbumPicture> {
    /**
     * 添加相册图片
     * */
    ResponseMap addAlbumPicture(AlbumPicture albumPicture);
    /**
     * 删除相册图片同时删除服务器上图片
     * */
    ResponseMap deleteAlbumPicture(Long id);
    /**
     * 多相册图片删除
     * */
    ResponseMap deleteAlbumPictureList(List<AlbumPicture> albumPictures);
    /**
     * 根据相册编号获取相册图片
     * */
    ResponseMap getAlbumPicture(Long albumId);
    /**
     * 根据相册编号删除相册
     * */
    ResponseMap deleteAlbumPictureWithAlbumId(Long albumId);
    /**
     * 根据相册编号统计相册图片数量
     * */
    ResponseMap countAlbumPicture(Long albumId);

    ResponseMap sixthAlbumPicture(Long albumId);
}
