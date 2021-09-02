package com.colm.service;

import com.colm.entity.PageResult;
import com.colm.entity.QueryPageBean;

public interface CheckGroupService {
    PageResult pageQuery(QueryPageBean queryPageBean);
}
