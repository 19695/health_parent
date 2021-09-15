package com.colm.service;

import com.colm.entity.PageResult;
import com.colm.entity.QueryPageBean;
import com.colm.entity.Result;
import com.colm.pojo.Setmeal;

public interface SetmealService {
    void add(Integer[] checkgroupIds, Setmeal setmeal);
    PageResult findPage(QueryPageBean queryPageBean);
}
