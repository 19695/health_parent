package com.colm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.colm.dao.CheckItemDao;
import com.colm.entity.PageResult;
import com.colm.entity.QueryPageBean;
import com.colm.pojo.CheckItem;
import com.colm.service.CheckItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class) // 加了事务注解的情况下这里要明确指明 interfaceClass
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        // 分页
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> result = page.getResult();
        return new PageResult(total, result);
    }

    @Override
    public void delete(Integer id) {
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0) {
            new RuntimeException();
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
