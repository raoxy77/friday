package com.rxy.friday.service.impl;

import com.rxy.friday.base.result.Results;
import com.rxy.friday.dao.RoleUserDao;
import com.rxy.friday.model.SysRoleUser;
import com.rxy.friday.service.RoleUserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rxy
 * @date 2020/2/22  13:38  星期六
 **/
@Service
public class RoleUserServImpl implements RoleUserServ {
    //注入roleuserDao
    @Autowired
    RoleUserDao roleUserDao;
/**
 * 根据userid查询用户角色关系
 * @Author: rxy
 * @Param: [userId]用户id
 * @return: {@link Results< SysRoleUser>}
 */
    @Override
    public Results<SysRoleUser> getUserRoleUserByUserId(Integer userId) {
        SysRoleUser roleUser = roleUserDao.getUserRoleByUserId(userId);
        if (roleUser == null)
            return Results.success();
        return Results.success(roleUser);
    }
}
