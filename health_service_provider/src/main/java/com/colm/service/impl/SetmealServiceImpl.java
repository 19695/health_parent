package com.colm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.colm.constant.RedisConstant;
import com.colm.dao.SetmealDao;
import com.colm.entity.PageResult;
import com.colm.entity.QueryPageBean;
import com.colm.pojo.Setmeal;
import com.colm.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(Integer[] checkgroupIds, Setmeal setmeal) {
        setmealDao.add(setmeal);
        Integer setmealId = setmeal.getId();
        if(checkgroupIds != null && checkgroupIds.length > 0){
            Map<String, Object> map = new HashMap<>();
            map.put("setmealId", setmealId);
            map.put("checkgroupIds", checkgroupIds);
            setmealDao.setSetmealAssociateGroup(map);
        }

        // 落库的图片
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setmealDao.findByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }
}