package com.rxy.friday.service;

import com.rxy.friday.base.result.Results;
import com.rxy.friday.model.SysRoleUser;

/**
 * RoleUser服务 接口定义
 */
public interface RoleUserServ {
    //根据userid查询user角色关系表
    Results<SysRoleUser> getUserRoleUserByUserId(Integer userId);
}
