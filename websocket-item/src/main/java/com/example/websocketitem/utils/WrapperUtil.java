package com.example.websocketitem.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.websocketitem.factory.EntityFactory;
import com.example.websocketitem.factory.MapFactory;
import com.example.websocketitem.model.Relationship;
import com.example.websocketitem.model.ResponseMap;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.print.DocFlavor;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
public class WrapperUtil<T> {
    private QueryWrapper<T> wrapper = new QueryWrapper<>();

    public QueryWrapper<T> wrapperTimeDesc(){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
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

    public QueryWrapper<T> wrapperOpennessAlbum(){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("openness",1).between("status",0,1);
        return wrapper;
    }
    public QueryWrapper<T> wrapperReport(String search, String startTime, String endTime) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        String start = null;
        String end = null;
        if (startTime!=null  && !startTime.equals("") && endTime!=null && !endTime.equals("")){
            start = startTime.contains("/") ? startTime.replaceAll("/", "-") : startTime;
            end = endTime.contains("/") ? endTime.replaceAll("/", "-") : endTime;
        }
        wrapper.apply(start!=null,"UNIX_TIMESTAMP(create_time) >= UNIX_TIMESTAMP('" + start + "')");
        wrapper.apply(end!=null,"UNIX_TIMESTAMP(create_time) <= UNIX_TIMESTAMP('" + end + "')");
        if (search != null && !search.equals("")) {
            wrapper.and(QueryWrapper->QueryWrapper.like( "informer_id", search)
                    .or().like("reporter_id", search)
                    .or().like( "type", search)
                    .or().like("description", search))
                    .or().like("status", search);
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    public QueryWrapper<T> countReport(Long reporterId) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("reporter_id",reporterId)
                .eq("status",1);
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
    public QueryWrapper<T> groupByReporterId(){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.select("reporter_id").eq("status",0).groupBy("reporter_id");
        return wrapper;
    }
    public QueryWrapper<T> wrapperReporterId(Long reporterId){
        QueryWrapper<T> wrapper = this.wrapperTimeDesc();
        wrapper.eq("reporter_id",reporterId).eq("status",0);
        return wrapper;
    }
    public QueryWrapper<T> wrapperSixthPicture(Long albumId){
        QueryWrapper<T> wrapper = this.wrapperTimeDesc();
        wrapper.eq("album_id",albumId);
        return wrapper;
    }
}
