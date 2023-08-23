package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Album;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.SearchModel;
import com.example.websocketitem.service.AlbumPictureService;
import com.example.websocketitem.service.AlbumService;
import com.example.websocketitem.mapper.AlbumMapper;
import com.example.websocketitem.utils.PageUtil;
import com.example.websocketitem.utils.ResponseMapUtil;
import com.example.websocketitem.utils.WrapperUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author cd
* @description 针对表【tcd_album(相册)】的数据库操作Service实现
* @createDate 2023-08-22 17:25:18
*/
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album>
    implements AlbumService{
    @Resource
    ResponseMapUtil<Album> responseMapUtil;
    @Resource
    PageUtil<Album> pageUtil;
    @Resource
    WrapperUtil<Album> wrapperUtil;
    @Resource
    AlbumPictureService albumPictureService;
    @Override
    public ResponseMap addAlbum(Album album) {
        return responseMapUtil.addEntity(this.save(album));
    }

    @Override
    public ResponseMap updateAlbum(Album album) {
        return responseMapUtil.updateEntity(this.updateById(album));
    }

    @Override
    public ResponseMap listAlbum(Long userId,Integer page, Integer size) {
        Page<Album> pageList = this.page(pageUtil.getModelPage(page, size),wrapperUtil.wrapperUserId(userId));
        Map<String, Object> modelMap = pageUtil.getModelMap(pageList);
        return responseMapUtil.getPageList(pageList,modelMap);
    }

    @Override
    public ResponseMap deleteAlbum(Long id) {
        return responseMapUtil.deleteEntity(this.removeById(id) && albumPictureService.deleteAlbumPictureWithAlbumId(id).getFlag());
    }

    @Override
    public ResponseMap searchAlbum(SearchModel searchModel) {
        Page<Album> pageList = this.page(pageUtil.getModelPage(searchModel.getPage(), searchModel.getSize()),
                wrapperUtil.wrapperNormal(searchModel.getSearch(), searchModel.getStartTime(), searchModel.getEndTime()));
        Map<String, Object> modelMap = pageUtil.getModelMap(pageList);
        return responseMapUtil.getPageList(pageList,modelMap);
    }
}




