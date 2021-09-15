package com.colm.dao;

import com.colm.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.Map;

public interface SetmealDao {
    void add(Setmeal setmeal);
    void setSetmealAssociateGroup(Map<String, Object> map);
    Page<Setmeal> findByCondition(String queryString);
}
