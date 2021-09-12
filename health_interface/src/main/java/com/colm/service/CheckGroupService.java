package com.colm.service;

import com.colm.entity.PageResult;
import com.colm.entity.QueryPageBean;
import com.colm.entity.Result;
import com.colm.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    PageResult pageQuery(QueryPageBean queryPageBean);
    CheckGroup findById(Integer groupId);
    List<Integer> findCheckItemIdsByCheckGroupId(String groupId);
    Result add(List<Integer> itemIds, CheckGroup checkGroup);
    Result edit(List<Integer> itemIds, CheckGroup checkGroup);
    Result delete(Integer id);
    List<CheckGroup> findAll();
}
