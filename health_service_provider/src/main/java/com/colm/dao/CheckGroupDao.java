package com.colm.dao;

import com.colm.pojo.CheckGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {
    Page<CheckGroup> findByCondition(String queryString);
    CheckGroup findById(Integer groupId);
    List<Integer> findCheckItemIdsByCheckGroupId(String groupId);
    int add(CheckGroup checkGroup);
    int setCheckGroupAssociateCheckItems(@Param("groupId") int groupId, @Param("itemIds") List<Integer> itemIds);
    int edit(CheckGroup checkGroup);
    int deleteAssocication(Integer id);
    int delete(Integer id);
}
