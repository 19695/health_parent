package com.colm.dao;

import com.colm.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {
    void add(Setmeal setmeal);
    void setSetmealAssociateGroup(Map<String, Object> map);
}
