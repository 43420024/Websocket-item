package com.example.websocketitem.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.factory.EntityFactory;
import com.example.websocketitem.mapper.PointsMapper;
import com.example.websocketitem.model.*;
import com.example.websocketitem.service.*;
import com.example.websocketitem.mapper.UserInfoMapper;
import com.example.websocketitem.utils.DataType;
import com.example.websocketitem.utils.ResponseMapUtil;
import com.example.websocketitem.utils.Result;
import com.example.websocketitem.utils.WrapperUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cd
 * @description 针对表【tcd_user_info】的数据库操作Service实现
 * @createDate 2023-08-26 11:05:58
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

    @Resource
    private AlbumService albumService;
    @Resource
    private PointsMapper pointsMapper;
    @Resource
    private RelationshipService relationshipService;
    @Resource
    ResponseMapUtil<UserInfo> responseMapUtil;
    @Resource
    private WrapperUtil<UserInfo> wrapperUtil;
    @Resource
    private WrapperUtil<Report> reportWrapperUtil;
    @Resource
    private WrapperUtil<Album> albumWrapperUtil;
    @Resource
    private ReportService reportService;


    Relationship relationship = EntityFactory.createRelationship();




    @Override
    public UserInfo getInfo(Long userId) {
        return this.getOne(wrapperUtil.wrapperUserInfo(userId));
    }
    /**
     * 获取未处理举报信息用户编号及该用户未处理举报信息个数
     */
    @Override
    public ResponseMap getReportUserInfo() {
        List<Report> reportList = reportService.getReporterIdList();
        List<UserInfo> userInfoList = new ArrayList<>();
        reportList.forEach(report -> {
            UserInfo userInfo = this.getById(report.getReporterId());
            userInfo.setReportCount((int) reportService.count(reportWrapperUtil.wrapperReporterId(report.getReporterId())));
            userInfoList.add(userInfo);
        });
        userInfoList.sort((o1, o2) -> o2.getReportCount()-o1.getReportCount());
        return responseMapUtil.getList(userInfoList);
    }
    /**
     * 根据模糊查询昵称获取未处理举报信息用户编号及该用户未处理举报信息个数
     */
    @Override
    public ResponseMap getSearchReportUserInfo(String value) {
        List<Report> reportList = reportService.getReporterIdList();
        List<UserInfo> userInfoList = new ArrayList<>();
        reportList.forEach(report -> {
            UserInfo userInfo = this.getById(report.getReporterId());
            if (userInfo.getNickname().contains(value)){
                userInfo.setReportCount((int) reportService.count(reportWrapperUtil.wrapperReporterId(report.getReporterId())));
                userInfoList.add(userInfo);
            }
        });
        userInfoList.sort((o1, o2) -> o2.getReportCount()-o1.getReportCount());
        return responseMapUtil.getList(userInfoList);
    }

    /**
     * 获取相册未审核用户编号及未审核相册个数
     * */
    @Override
    public ResponseMap getAlbumUserInfo() {
        List<Album> albumList = albumService.getAlbumOwnerIdList();
        List<UserInfo> userInfoList = new ArrayList<>();
        albumList.forEach(album-> {
            UserInfo userInfo = this.getById(album.getUserId());
            userInfo.setAlbumCount((int) albumService.count(albumWrapperUtil.wrapperUserInfo(album.getUserId())));
            userInfoList.add(userInfo);
        });
        userInfoList.sort((o1, o2) -> o2.getAlbumCount()-o1.getAlbumCount());
        return responseMapUtil.getList(userInfoList);
    }
    /**
     * 根据模糊查询昵称获取相册未审核用户编号及未审核相册个数
     */
    @Override
    public ResponseMap getSearchAlbumUserInfo(String value) {
        List<Album> albumList = albumService.getAlbumOwnerIdList();
        List<UserInfo> userInfoList = new ArrayList<>();
        albumList.forEach(album-> {
            UserInfo userInfo = this.getById(album.getUserId());
            if (userInfo.getNickname().contains(value)){
                userInfo.setAlbumCount((int) albumService.count(albumWrapperUtil.wrapperUserInfo(album.getUserId())));
                userInfoList.add(userInfo);
            }
        });
        userInfoList.sort((o1, o2) -> o2.getAlbumCount()-o1.getAlbumCount());
        return responseMapUtil.getList(userInfoList);
    }
    @Override
    public ResponseMap getUserInfo(Long userId) {
        return responseMapUtil.getEntity(this.getById(userId));
    }
    /**
     * 解封 冻结
     * @param userInfo
     * @return
     */
    @Override
    public Result updateStatus(UserInfo userInfo) {
        if (userInfo.getStatus() == 0) {
            userInfo.setStatus(1);
            int update = this.baseMapper.updateById(userInfo);
            return update > 0 ? Result.success("解封成功！") : Result.error("解封失败！");
        }
        userInfo.setStatus(0);
        int updateById = this.baseMapper.updateById(userInfo);
        return updateById > 0 ? Result.success("封号成功！") : Result.error("封号失败！");
    }

    /**
     * 分页遍历
     * @param pageNum
     * @param pageSize
     * @param gender
     * @param phoneNumber
     * @param nickname
     * @return
     */
    @Override
    public Result selectPage(Integer pageNum, Integer pageSize, Integer gender, Integer phoneNumber, Integer nickname) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        //pageNum当前页，pageSize显示条数
        Page<UserInfo> page = new Page<>(pageNum, pageSize);
        if (phoneNumber != null) {
            wrapper.like("phone_number", phoneNumber);
        }
        if (nickname != null) {
            wrapper.like("nickname", nickname);
        }
        if (gender != null) {
            wrapper.eq("gender", gender);
        }
        Page<UserInfo> userPage = this.baseMapper.selectPage(page, wrapper);
        return Result.success(userPage);

    }

    @Override
    public DataType queryUserInfo(Long userId) {
        return null;
    }
}




