package com.colm.dao;

import com.colm.pojo.DataDict;
import org.apache.ibatis.annotations.Param;

public interface DataDictDao {
    DataDict getByType(String type);
    DataDict getByCode(String code);
    DataDict getByTypeAndCode(@Param("type") String type, @Param("code") String code);
}
