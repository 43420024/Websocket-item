package com.example.websocketitem.service;

import com.example.websocketitem.model.Album;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.SearchModel;

/**
* @author cd
* @description 针对表【tcd_album(相册)】的数据库操作Service
* @createDate 2023-09-05 14:25:05
*/
public interface AlbumService extends IService<Album> {
    /**
     * 添加相册
     * */
    ResponseMap addAlbum(Album album);
    /**
     * 更新相册(用户修改相册名称，女性用户改变相册开放状态，系统管理修改相册冻结状态
     * */
    ResponseMap updateAlbum(Album album);
    /**
     * 根据用户编号编号获取相册分页列表
     * */
    ResponseMap listAlbum(Long userId, Integer page,Integer size);
    /**
     * 根据相册编号删除相册
     * */
    ResponseMap deleteAlbum(Long id);
    /**
     * 模糊全匹配+按时间相册查询
     * */
//    ResponseMap searchAlbum(SearchModel searchModel);

    Boolean capacityReduce(Long id);

    ResponseMap statAlbum();
    /**
     * 根据用户编号获取该用户全部未审核相册
     * */
    ResponseMap listAllAlbum(Long userId);
    /**
     * APP首页获取随机公开相册
     * */
    ResponseMap shuffleAlbum();
}
