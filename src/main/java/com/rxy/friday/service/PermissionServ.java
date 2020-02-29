package com.rxy.friday.service;

import com.alibaba.fastjson.JSONArray;
import com.rxy.friday.base.result.Results;
import com.rxy.friday.model.SysPermission;

import java.util.List;

/**
 * 权限 服务定义
 */
public interface PermissionServ {
    /**
     * 获取全部权限值
     * @Author: rxy
     * @Param: []
     * @return: {@link Results< JSONArray>}
     */
    Results<JSONArray> getAllPermission();
    /**
     * 根据角色id获取角色所有权限值
     *
     * @Author: rxy
     * @Param: [roleId]
     * @return: {@link List < SysPermission>}
     */
    Results<SysPermission> getPermissionsByRoleId(Integer roleId);
}
