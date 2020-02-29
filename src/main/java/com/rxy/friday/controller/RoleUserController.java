package com.rxy.friday.controller;

import com.rxy.friday.base.result.Results;
import com.rxy.friday.model.SysRoleUser;
import com.rxy.friday.service.RoleUserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rxy
 * @date 2020/2/22  21:06  星期六
 **/
@Controller
@RequestMapping("roleuser")
public class RoleUserController {
    //注入roleUserServ
    @Autowired
    RoleUserServ roleUserServ;

    /**
     * 根据用户id查询角色
     *
     * @Author: rxy
     * @Param: [userId]用户id
     * @return: {@link Results< SysRoleUser>}
     */
    @PostMapping("/getRoleUserByUserId")
    @ResponseBody
    public Results<SysRoleUser> getRole(Integer userId) {
        return roleUserServ.getUserRoleUserByUserId(userId);
    }
}
