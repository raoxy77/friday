package com.rxy.friday.controller;

import com.alibaba.fastjson.JSONArray;
import com.rxy.friday.base.result.Results;
import com.rxy.friday.model.SysPermission;
import com.rxy.friday.service.PermissionServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rxy
 * @date 2020/2/25  16:22  星期二
 **/
@Controller
@RequestMapping("permission")
public class PermissionController {
    //注入permissionserv
    @Autowired
    PermissionServ permissionServ;

    /**
     * 获取全部权限值
     *
     * @Author: rxy
     * @Param: []
     * @return: {@link Results< JSONArray>}
     */
    @ResponseBody
    @GetMapping("listAllPermission")
    public Results<JSONArray> getAllPermission() {
        return permissionServ.getAllPermission();
    }

    /**
     * 根据角色id获取角色所有权限值
     *
     * @Author: rxy
     * @Param: [roleId]
     * @return: {@link List < SysPermission>}
     */
    @GetMapping("listAllPermissionByRoleId")
    @ResponseBody
    public Results<SysPermission> getAllPermissionByRoleId(@RequestParam("id") Integer roleId) {
        return permissionServ.getPermissionsByRoleId(roleId);
    }
}
