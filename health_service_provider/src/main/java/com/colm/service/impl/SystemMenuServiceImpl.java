package com.colm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.colm.constant.DictCodeEnum;
import com.colm.constant.DictTypeEnum;
import com.colm.dao.DataDictDao;
import com.colm.dao.SystemMenuDao;
import com.colm.pojo.DataDict;
import com.colm.pojo.Menu;
import com.colm.service.DataDictService;
import com.colm.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(interfaceClass = SystemMenuService.class)
@Transactional
public class SystemMenuServiceImpl implements SystemMenuService {

    @Autowired
    private SystemMenuDao systemMenuDao;

    @Autowired
    private DataDictService dataDictService;

    @Override
    public List<Menu> getAll() {
        List<Menu> all = systemMenuDao.getAll();
        all.stream().forEach(e -> {
            Integer parentMenuId = e.getParentMenuId();
            if(parentMenuId == null) {
                e.setParentMenuId(0);
            }
        });
        Map<Integer, List<Menu>> listMap = all.stream().collect(Collectors.groupingBy(Menu::getParentMenuId));
        List<Menu> parentList = all.stream().filter(menu -> menu.getParentMenuId() == 0).collect(Collectors.toList());
        parentList.stream().forEach(e -> {
            e.setChildren(listMap.get(e.getId()));
        });
        return parentList;
    }

    @Override
    public String getDefault() {
        String type = DictTypeEnum.DEFAULT_PAGE.getType();
        String code = DictCodeEnum.DEFAULT_PAGE.getCode();
        DataDict data = dataDictService.getByTypeAndCode(type, code);
        String value = "";
        if(data != null) {
            value = data.getValue();
        }
        return value;
    }
}
