package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 相册图片
 * @TableName tcd_album_picture
 */
@TableName(value ="tcd_album_picture")
@Data
public class AlbumPicture implements Serializable {
    /**
     * 相册图片编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 相册图片所属相册编号
     */
    private Long albumId;

    /**
     * 相册图片存储路径
     */
    private String path;

    /**
     * 相册图片创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}