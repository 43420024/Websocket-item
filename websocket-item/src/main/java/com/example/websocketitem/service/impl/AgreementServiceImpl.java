package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Agreement;
import com.example.websocketitem.service.AgreementService;
import com.example.websocketitem.mapper.AgreementMapper;
import com.example.websocketitem.utils.Result;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author cd
* @description 针对表【tcd_agreement(协议表)】的数据库操作Service实现
* @createDate 2023-08-31 10:09:32
*/
@Service
public class AgreementServiceImpl extends ServiceImpl<AgreementMapper, Agreement>
    implements AgreementService{

    @Override
    public Result agreementAdd(Agreement agreement) {
        agreement.setCreateTime(new Date());
        boolean save = super.save(agreement);
        return save?Result.ok():Result.error();
    }

    @Override
    public Result agreementDelete(Integer id) {
        return super.removeById(id)?Result.ok():Result.error();
    }

    @Override
    public Result agreementUpdate(Agreement agreement) {
        return super.updateById(agreement)?Result.ok():Result.error();
    }

    @Override
    public Result agreementById(Integer id) {
        Agreement byId = super.getById(id);
        if(byId==null){
            return Result.error();
        }else {
            return Result.ok().data(byId);
        }
    }

    @Override
    public Result agreementByAll() {
        List<Agreement> list = super.list();
        if(list==null){
            return Result.error();
        }else {
            return Result.ok().data(list);
        }
    }
}




