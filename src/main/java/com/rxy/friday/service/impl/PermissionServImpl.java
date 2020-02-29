package com.rxy.friday.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.rxy.friday.base.result.Results;
import com.rxy.friday.dao.PermissionDao;
import com.rxy.friday.model.SysPermission;
import com.rxy.friday.service.PermissionServ;
import com.rxy.friday.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rxy
 * @date 2020/2/28  09:34  星期五
 **/
@Service
public class PermissionServImpl implements PermissionServ {
    //注入permissionDao
    @Autowired
    PermissionDao permissionDao;

    @Override
    public Results<JSONArray> getAllPermission() {
        List<SysPermission> datas = permissionDao.getAllPermission();
        JSONArray array = new JSONArray();
//        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        TreeUtils.setPermissionsTree(0, datas, array);
        return Results.success(array);
    }

    /**
     * 根据角色id获取角色所有权限值
     *
     * @Author: rxy
     * @Param: [roleId]
     * @return: {@link List< SysPermission>}
     */
    @Override
    public Results<SysPermission> getPermissionsByRoleId(Integer roleId) {
        return Results.success(0, permissionDao.getPermissionsByRoleId(roleId));
    }
}
