package com.colm.dao;

import com.colm.pojo.CheckGroup;
import com.github.pagehelper.Page;

import java.util.List;

public interface CheckGroupDao {
    Page<CheckGroup> findByCondition(String queryString);
    CheckGroup findById(Integer groupId);
    List<Integer> findCheckItemIdsByCheckGroupId(String groupId);
}
