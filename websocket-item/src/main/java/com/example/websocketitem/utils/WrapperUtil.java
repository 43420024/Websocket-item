package com.example.websocketitem.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.websocketitem.model.Relationship;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.print.DocFlavor;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
public class WrapperUtil<T> {
    public QueryWrapper<T> wrapperTimeDesc(){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        return wrapper;
    }
    /**
     * 搜索通用
     * */
    public QueryWrapper<T> wrapperNormal(String search, String startTime, String endTime) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        String start = null;
        String end = null;
        if (startTime!=null  && !startTime.equals("") && endTime!=null && !endTime.equals("")){
            start = startTime.contains("/") ? startTime.replaceAll("/", "-") : startTime;
            end = endTime.contains("/") ? endTime.replaceAll("/", "-") : endTime;
        }
        wrapper.apply(start!=null,"UNIX_TIMESTAMP(update_time) >= UNIX_TIMESTAMP('" + start + "')");
        wrapper.apply(end!=null,"UNIX_TIMESTAMP(update_time) <= UNIX_TIMESTAMP('" + end + "')");
        if (search != null && !search.equals("")) {
            wrapper.and(QueryWrapper->QueryWrapper.like( "name", search)
                    .or().like("type", search)
                    .or().like( "total", search)
                    .or().like("director", search));
        }

        wrapper.orderByDesc("update_time");
        return wrapper;
    }
    public QueryWrapper<T> wrapperUserId(Long userId){
        QueryWrapper<T> wrapper = this.wrapperTimeDesc();
        wrapper.eq("user_id",userId);
        wrapper.eq("status",0);
        return wrapper;
    }
    public QueryWrapper<T> wrapperAlbumId(Long albumId){
        QueryWrapper<T> wrapper = this.wrapperTimeDesc();
        wrapper.eq("album_id",albumId);
        return wrapper;
    }

    public QueryWrapper<T> wrapperAlbumFrozen(){
        QueryWrapper<T> wrapper = this.wrapperTimeDesc();

        return wrapper;
    }
    public QueryWrapper<T> wrapperReport(String search, String startTime, String endTime) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(startTime) && StringUtils.hasLength(endTime)){
            LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime end = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            wrapper.between("create_time", start, end);
        }
        wrapper.like(StringUtils.hasLength(search), "informer_id", search)
                .or().like(StringUtils.hasLength(search), "reporter_id", search)
                .or().like(StringUtils.hasLength(search), "type", search)
                .or().like(StringUtils.hasLength(search), "description", search)
                .or().like(StringUtils.hasLength(search), "status", search);
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    public QueryWrapper<T> countReport(Long reporterId) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("reporter_id",reporterId);
        wrapper.eq("status",1);
        wrapper.groupBy("content_id");
        return wrapper;
    }

    public QueryWrapper<T> getRelationship(Relationship relationship){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("owner_id",relationship.getOwnerId());
        wrapper.eq("friend_id",relationship.getFriendId());
        return wrapper;
    }

    public QueryWrapper<T> listRelationship(Long ownerId){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("owner_id",ownerId);
        return wrapper;
    }

    public QueryWrapper<T> groupById(){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.select("user_id").eq("status",0).groupBy("user_id");
        return wrapper;
    }

}
