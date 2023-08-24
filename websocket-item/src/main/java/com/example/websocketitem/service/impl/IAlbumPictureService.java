package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.domain.AlbumPicture;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.service.AlbumPictureService;
import com.example.websocketitem.mapper.AlbumPictureMapper;
import com.example.websocketitem.utils.PageUtil;
import com.example.websocketitem.utils.ResponseMapUtil;
import com.example.websocketitem.utils.WrapperUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author cd
* @description 针对表【tcd_album_picture(相册图片)】的数据库操作Service实现
* @createDate 2023-08-22 17:25:01
*/
@Service
public class IAlbumPictureService extends ServiceImpl<AlbumPictureMapper, AlbumPicture>
    implements AlbumPictureService{
    @Resource
    ResponseMapUtil<AlbumPicture> responseMapUtil;
    @Resource
    PageUtil<AlbumPicture> pageUtil;
    @Resource
    WrapperUtil<AlbumPicture> wrapperUtil;
    @Override
    public ResponseMap addAlbumPicture(AlbumPicture albumPicture) {
        albumPicture.setCreateTime(new Date());
        return responseMapUtil.addEntity(this.save(albumPicture));
    }


    @Override
    public ResponseMap deleteAlbumPicture(Long id) {
        AlbumPicture albumPicture = this.getById(id);
        File file = new File("."+albumPicture.getPath());
        Boolean flag = false;
        if(file.isFile()&&file.exists()){
            flag = file.delete();
        }
        return responseMapUtil.deleteEntity(this.removeById(id) && flag);
    }

    @Override
    public ResponseMap deleteAlbumPictureList(List<AlbumPicture> albumPictures) {
        for (AlbumPicture albumPicture:albumPictures){
            File file = new File("."+albumPicture.getPath());
            if(file.isFile()&&file.exists()){
                file.delete();
            }
        }
        return responseMapUtil.deleteList(this.removeBatchByIds(albumPictures));
    }

    @Override
    public ResponseMap listAlbumPicture(Long albumId, Integer page, Integer size) {
        Page<AlbumPicture> pageList = this.page(pageUtil.getModelPage(page, size),wrapperUtil.wrapperAlbumId(albumId));
        Map<String, Object> modelMap = pageUtil.getModelMap(pageList);
        return responseMapUtil.getPageList(pageList,modelMap);
    }

    @Override
    public ResponseMap deleteAlbumPictureWithAlbumId(Long albumId) {
        List<AlbumPicture> list = this.list(wrapperUtil.wrapperAlbumId(albumId));
        for (AlbumPicture albumPicture:list){
            File file = new File("."+albumPicture.getPath());
            if(file.isFile()&&file.exists()){
                file.delete();
            }
        }
        return responseMapUtil.deleteList(this.remove(wrapperUtil.wrapperAlbumId(albumId)));
    }
}




