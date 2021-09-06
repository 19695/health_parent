package com.colm.service;

import com.colm.pojo.DataDict;

public interface DataDictService {
    DataDict getByCode(String code);
    DataDict getByType(String type);
    DataDict getByTypeAndCode(String type, String code);
}
