package com.colm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.colm.constant.MessageConstant;
import com.colm.entity.Result;
import com.colm.pojo.Menu;
import com.colm.service.SystemMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class SystemMenuController {

    @Reference
    private SystemMenuService systemMenuService;

    @GetMapping("/getAll")
    public Result getAll() {
        try {
            List<Menu> list = systemMenuService.getAll();
            return new Result(true, MessageConstant.GET_SYS_MENU_SUCCESS, list);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SYS_MENU_FAIL);
        }
    }
    @GetMapping("/default")
    public Result defaultPage() {
        try {
            String defaultPage = systemMenuService.getDefault();
            return new Result(true, MessageConstant.GET_DEFAULT_PAGE_SUCCESS, defaultPage);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_DEFAULT_PAGE_FAIL);
        }
    }

}
