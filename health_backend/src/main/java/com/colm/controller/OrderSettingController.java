package com.colm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.colm.constant.MessageConstant;
import com.colm.entity.Result;
import com.colm.service.OrderSettingService;
import com.colm.utils.PoiReadUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("")MultipartFile file) {
        try {
            List<String[]> strings = PoiReadUtils.readExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

}
