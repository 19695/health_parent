package com.colm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.colm.constant.DictCodeEnum;
import com.colm.constant.DictTypeEnum;
import com.colm.constant.MessageConstant;
import com.colm.entity.Result;
import com.colm.pojo.DataDict;
import com.colm.service.DataDictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysdata")
public class DataDictController {

    @Reference
    private DataDictService dataDictService;

    @GetMapping("/getOssDomain")
    public Result getOssDomain() {
        try {
            String type = DictTypeEnum.OSS_DOMAIN.getType();
            String code = DictCodeEnum.OSS_DOMAIN.getCode();
            DataDict dataDict = dataDictService.getByTypeAndCode(type, code);
            if(dataDict != null) {
                String value = dataDict.getValue();
                return new Result(true, MessageConstant.GET_OSS_DOMAIN_SUCCESS, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.GET_OSS_DOMAIN_FAIL);
    }

}
