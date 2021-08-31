package com.colm.service;

import com.colm.entity.PageResult;
import com.colm.entity.QueryPageBean;
import com.colm.pojo.CheckItem;

public interface CheckItemService {
    void add(CheckItem checkItem);
    PageResult findPage(QueryPageBean queryPageBean);
    void delete(Integer id);
    void edit(CheckItem checkItem);
}
