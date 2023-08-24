package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.factory.DateTypeFactory;
import com.example.websocketitem.model.Trends;
import com.example.websocketitem.service.TrendsService;
import com.example.websocketitem.mapper.TrendsMapper;
import com.example.websocketitem.utils.DataType;
import com.example.websocketitem.utils.ImageUtil;
import com.example.websocketitem.utils.SensitivewordUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
* @author cd
* @description 针对表【tcd_trends(用户动态表)】的数据库操作Service实现
* @createDate 2023-08-23 10:00:33
*/
@Service
@Slf4j
public class TrendsServiceImpl extends ServiceImpl<TrendsMapper, Trends>
    implements TrendsService{

    DataType dataType= DateTypeFactory.create();
    @Resource
    TrendsMapper trendsMapper;

    @Override
    public DataType savaTrends(Trends trends) {
//        log.info("{}",article);
//        判断是修改新闻还是保存
        if (trends.getId()!=null){
            //进行新闻的修改
            Trends trends1 = trendsMapper.selectOne(new QueryWrapper<Trends>().eq("id", trends.getId()));
            trends.setId(trends1.getId());
            trends.setCount(trends1.getCount());
            trends.setCreatetime(trends1.getCreatetime());
            trends.setEdittime(new Date());
            String content = trends.getContent();
            //进行内容过滤,最小匹配规则
            content = SensitivewordUtil.replaceSensitiveWord(content, 1, "*");
            trends.setContent(content);
            //设置文章图片
            List<String> imgStr = ImageUtil.getImgStr(content);
            if (imgStr==null||imgStr.size()==0){

                trends.setImgsrc("/images/white.jpg");
            }else {
                //取第一张图片即可
                String src = imgStr.get(0);
                trends.setImgsrc(src);
            }
            trendsMapper.updateById(trends);
            dataType.setMessage("修改成功");
        }else {
            //是新闻添加
            //设置文章图片
            String content = trends.getContent();
            //进行内容和标题过滤
            content=SensitivewordUtil.replaceSensitiveWord(content,1,"*");
            trends.setContent(content);
            List<String> imgStr = ImageUtil.getImgStr(content);
            if (imgStr==null||imgStr.size()==0){

                trends.setImgsrc("/images/white.jpg");
            }else {
                //取第一张图片即可
                String src = imgStr.get(0);
                trends.setImgsrc(src);
            }
            trends.setCreatetime(new Date());
            trends.setUserid(trends.getUserid());
            trends.setCount(0L);
            trendsMapper.insert(trends);
            dataType.setMessage("添加成功");
        }
        return dataType;
    }

    @Override
    public DataType deleteTrends(Long id) {
        if(removeById(id)){
            dataType.setFlag(true);
            dataType.setMessage("删除成功");
            dataType.setData(true);
        }else {
            dataType.setFlag(false);
            dataType.setMessage("删除失败");
            dataType.setData(false);
        }
        return dataType;
    }

    @Override
    public DataType allQueryTrends(Long userid) {
        List<Trends> trendsList=trendsMapper.findAllByUserid(userid);
        dataType.setMessage("查询成功");
        dataType.setData(trendsList);
        dataType.setFlag(true);
        return dataType;
    }

    @Override
    public DataType addCount(Long id) {
        if (id!=null && id>0){
            Trends trends1 = trendsMapper.selectOne(new QueryWrapper<Trends>().eq("id", id));
            if (trends1==null){
                dataType.setMessage("该用户不存在");
                dataType.setFlag(false);
                return dataType;
            }
            long a=trends1.getCount()+1;
            trends1.setCount(a);
            trendsMapper.updateById(trends1);
            dataType.setFlag(true);
            dataType.setData(trends1.getCount());
            dataType.setMessage("点赞成功");
        }else {
            dataType.setMessage("点赞失败");
            dataType.setFlag(false);
        }
        return dataType;
    }
}




