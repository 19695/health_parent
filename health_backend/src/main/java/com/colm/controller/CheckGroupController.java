package com.colm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.colm.constant.MessageConstant;
import com.colm.entity.PageResult;
import com.colm.entity.QueryPageBean;
import com.colm.entity.Result;
import com.colm.pojo.CheckGroup;
import com.colm.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @GetMapping("/findAll")
    public Result findAll() {
        try {
            List<CheckGroup> result = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, result);
        } catch (Exception e) {
            return Result.FAILURE;
        }
    }

    @PostMapping("/add")
    public Result add(@RequestParam("checkItemIds") List<Integer> itemIds, @RequestBody CheckGroup checkGroup) {
        try {
            return checkGroupService.add(itemIds, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    @PostMapping("/edit")
    public Result edit(@RequestParam("checkItemIds") List<Integer> itemIds, @RequestBody CheckGroup checkGroup) {
        try {
            return checkGroupService.edit(itemIds, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @GetMapping("/delete")
    public Result delete(Integer id) {
        try {
            return checkGroupService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.pageQuery(queryPageBean);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);//????????????
        }
    }

    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(@RequestParam("id") String groupId) {
        try{
            List<Integer> checkitemIds = checkGroupService.findCheckItemIdsByCheckGroupId(groupId);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemIds);//????????????
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);//????????????
        }
    }
}