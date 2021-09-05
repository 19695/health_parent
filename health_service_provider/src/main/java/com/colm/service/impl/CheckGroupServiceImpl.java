package com.colm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.colm.constant.MessageConstant;
import com.colm.dao.CheckGroupDao;
import com.colm.entity.PageResult;
import com.colm.entity.QueryPageBean;
import com.colm.entity.Result;
import com.colm.pojo.CheckGroup;
import com.colm.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
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

    @Override
    public Result add(List<Integer> itemIds, CheckGroup checkGroup) {
        if(checkGroup == null) {
            throw new RuntimeException("检查组信息必须填写！");
        }
        int count = checkGroupDao.add(checkGroup);
        if(count > 0) {
            Integer groupId = checkGroup.getId();
            if(groupId != null && !CollectionUtils.isEmpty(itemIds)) {
                int associateCount = checkGroupDao.setCheckGroupAssociateCheckItems(groupId, itemIds);
                if(associateCount < 0) {
                    return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
                }
            }
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        }
        return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
    }
}
