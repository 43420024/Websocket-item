package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Album;
import com.example.websocketitem.service.AlbumService;
import com.example.websocketitem.mapper.AlbumMapper;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_album(相册)】的数据库操作Service实现
* @createDate 2023-08-22 17:25:18
*/
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album>
    implements AlbumService{

}




