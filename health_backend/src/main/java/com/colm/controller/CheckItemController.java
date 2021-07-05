package com.colm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.colm.service.CheckItemService;

public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

}
