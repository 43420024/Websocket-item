package com.example.websocketitem.service;

import com.example.websocketitem.model.Agreement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_agreement(协议表)】的数据库操作Service
* @createDate 2023-08-31 10:09:32
*/
public interface AgreementService extends IService<Agreement> {
    Result agreementAdd(Agreement agreement);
    Result agreementDelete(Integer id);
    Result agreementUpdate(Agreement agreement);
    Result agreementById(Integer id);
    Result agreementByAll();
}
