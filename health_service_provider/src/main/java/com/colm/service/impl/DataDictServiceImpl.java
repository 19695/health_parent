package com.colm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.colm.dao.DataDictDao;
import com.colm.pojo.DataDict;
import com.colm.service.DataDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = DataDictService.class)
@Transactional
public class DataDictServiceImpl implements DataDictService {

    @Autowired
    private DataDictDao dataDictDao;

    @Override
    public DataDict getByCode(String code) {
        return dataDictDao.getByCode(code);
    }

    @Override
    public DataDict getByType(String type) {
        return dataDictDao.getByType(type);
    }

    @Override
    public DataDict getByTypeAndCode(String type, String code) {
        return dataDictDao.getByTypeAndCode(type, code);
    }
}
