package com.colm.dao;

import com.colm.pojo.CheckGroup;
import com.github.pagehelper.Page;

public interface CheckGroupDao {
    Page<CheckGroup> findByCondition(String queryString);
}
