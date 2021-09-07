package com.colm.controller;

import com.colm.constant.DateFormatEnum;
import com.colm.constant.MessageConstant;
import com.colm.entity.Result;
import com.colm.utils.QiniuUtils;
import com.colm.utils.TimeFormatUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @PostMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String fileType = originalFilename.substring(index);
        LocalDateTime now = LocalDateTime.now();
        // yyyyMMddHHmmssSSS 17位
        String formatTime = TimeFormatUtil.formatTime(now, DateFormatEnum.DATE_TIME_MILLS);
        // 7 位
        String subUuid = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        // 24 位 + 文件后缀
        String storeName = formatTime + subUuid + fileType;
        try {
            QiniuUtils.upload2Qiniu(file.getBytes(), storeName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, storeName);
    }


}
