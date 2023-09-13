package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Album;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.service.AlbumPictureService;
import com.example.websocketitem.service.AlbumService;
import com.example.websocketitem.mapper.AlbumMapper;
import com.example.websocketitem.utils.PageUtil;
import com.example.websocketitem.utils.ResponseMapUtil;
import com.example.websocketitem.utils.WrapperUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author cd
* @description 针对表【tcd_album(相册)】的数据库操作Service实现
* @createDate 2023-08-25 16:55:50
*/
@Service
public class IAlbumService extends ServiceImpl<AlbumMapper, Album>
    implements AlbumService{
    @Resource
    ResponseMapUtil<Album> responseMapUtil;
    @Resource
    PageUtil<Album> pageUtil;
    @Resource
    WrapperUtil<Album> wrapperUtil;
    @Resource
    AlbumPictureService albumPictureService;

    /**
     * 添加相册
     * */
    @Override
    public ResponseMap addAlbum(Album album) {
        album.setCreateTime(new Date());
        album.setCapacity(16);
        return responseMapUtil.addEntity(this.save(album));
    }
    /**
     * 更新相册(用户修改相册名称，女性用户改变相册开放状态，系统管理修改相册冻结状态
     * */
    @Override
    public ResponseMap updateAlbum(Album album) {
        return responseMapUtil.updateEntity(this.updateById(album));
    }
    /**
     * 根据用户编号编号获取相册分页列表
     * */
    @Override
    public ResponseMap listAlbum(Long userId,Integer page, Integer size) {
        Page<Album> pageList = this.page(pageUtil.getModelPage(page, size),wrapperUtil.wrapperUserId(userId));
        Map<String, Object> modelMap = pageUtil.getModelMap(pageList);
        return responseMapUtil.getPageList(pageList,modelMap);
    }
    /**
     * 根据相册编号删除相册
     * */
    @Override
    @Transactional
    public ResponseMap deleteAlbum(Long id) {
        return responseMapUtil.deleteEntity(this.removeById(id) && albumPictureService.deleteAlbumPictureWithAlbumId(id).getFlag());
    }
    /**
     * 模糊全匹配+按时间相册查询
     * */
//    @Override
//    public ResponseMap searchAlbum(SearchModel searchModel) {
//        Page<Album> pageList = this.page(pageUtil.getModelPage(searchModel.getPage(), searchModel.getSize()),
//                wrapperUtil.wrapperNormal(searchModel.getSearch(), searchModel.getStartTime(), searchModel.getEndTime()));
//        Map<String, Object> modelMap = pageUtil.getModelMap(pageList);
//        return responseMapUtil.getPageList(pageList,modelMap);
//    }

    @Override
    public Boolean capacityReduce(Long id) {
        Album album = this.getById(id);
        Integer capacity = album.getCapacity();
        if (capacity>0){
            album.setCapacity(capacity-1);
            this.updateById(album);
            return true;
        }else {
            return false;
        }
    }
    /**
     * 获取相册未审核用户编号及未审核相册个数
     * */
    @Override
    public ResponseMap statAlbum() {
        List<Album> userIdList = this.list(wrapperUtil.groupById());
        Map<Long,Integer> map = new HashMap<>();
        userIdList.forEach(album-> map.put(album.getUserId(), (int) this.count(wrapperUtil.wrapperUserId(album.getUserId()))));
        return responseMapUtil.returnMap(map);
    }
    /**
     * 根据用户编号获取该用户全部未审核相册
     * */
    @Override
    public ResponseMap listAllAlbum(Long userId) {
        return responseMapUtil.getList(this.list(wrapperUtil.wrapperUserId(userId)));
    }
    /**
     * APP首页获取随机公开相册
     * */
    @Override
    public ResponseMap shuffleAlbum() {
        List<Album> list = this.list(wrapperUtil.wrapperOpennessAlbum());
        Collections.shuffle(list);
        return responseMapUtil.getList(list.subList(0,5));
    }
}




