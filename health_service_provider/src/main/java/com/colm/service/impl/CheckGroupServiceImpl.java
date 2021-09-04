package com.colm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.colm.dao.CheckGroupDao;
import com.colm.entity.PageResult;
import com.colm.entity.QueryPageBean;
import com.colm.pojo.CheckGroup;
import com.colm.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public CheckGroup findById(Integer groupId) {
        return checkGroupDao.findById(groupId);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(String groupId) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(groupId);
    }
}
