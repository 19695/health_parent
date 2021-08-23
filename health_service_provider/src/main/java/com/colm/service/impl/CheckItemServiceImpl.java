package com.colm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.colm.service.CheckItemService;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = CheckItemService.class) // 加了事务注解的情况下这里要明确指明 interfaceClass
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
}
