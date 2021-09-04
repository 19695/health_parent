package com.colm.service;

import com.colm.entity.PageResult;
import com.colm.entity.QueryPageBean;
import com.colm.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    PageResult pageQuery(QueryPageBean queryPageBean);
    CheckGroup findById(Integer groupId);
    List<Integer> findCheckItemIdsByCheckGroupId(String groupId);
}