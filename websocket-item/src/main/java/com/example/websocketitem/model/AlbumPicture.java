package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName tcd_album_picture
 */
@TableName(value ="tcd_album_picture")
@Data
public class AlbumPicture implements Serializable {
    private Long id;

    private Long albumId;

    private String path;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}