package com.rxy.friday.service.impl;

import com.rxy.friday.base.result.PageTableRequest;
import com.rxy.friday.base.result.Results;
import com.rxy.friday.dao.RoleUserDao;
import com.rxy.friday.dao.UserDao;
import com.rxy.friday.model.SysRoleUser;
import com.rxy.friday.model.SysUser;
import com.rxy.friday.service.UserServ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务接口实现类
 *
 * @author rxy
 * @date 2020/2/21  09:17  星期五
 **/
@Slf4j
@Service

public class UserServImpl implements UserServ {

    /**
     * 注入userDao实现类
     *
     * @Author: rxy
     * @Param:
     * @return: {@link }
     */
    @Autowired
    private UserDao userDao;

    //注入roleUserDao实现类
    @Autowired
    private RoleUserDao roleUserDao;

    /**
     * 根据用户名查询信息服务
     *
     * @Author: rxy
     * @Param: [userName] 用户名
     * @return: {@link Results< SysUser>}
     */
    @Override
    public SysUser getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    /**
     * 根据用户id查询信息服务
     *
     * @Author: rxy
     * @Param: [id] 用户id
     * @return: {@link Results< SysUser>}
     */
    @Override
    public SysUser getUserByUserId(Long id) {
        log.debug("userServ----->getUserByUserId() {}", id);
        return userDao.getUserByUserId(id);
    }

    /**
     * 分页查询服务
     *
     * @Author: rxy
     * @Param: [request]
     * @return: {@link Results< SysUser>}
     */
    @Override
    public Results<SysUser> getAllUsersByPage(PageTableRequest request) {
        request.countOffset();
        return Results.success(userDao.countAllUsers().intValue(), userDao.getAllUsersByPage(request.getOffset(), request.getLimit()));
    }

    /**
     * 根据id删除用户服务
     *
     * @Author: rxy
     * @Param: [id] userId
     * @return: {@link int}
     */
    @Transactional
    @Override
    public Results deleteUserByIdAndRoleUser(Long id) {
        log.debug("UserServ----->deleteUserByIdAndRoleUser() id:{}", id.toString());
        //删除用户之前先删除用户对应的角色 关系
        roleUserDao.deleteRoleUserByUserIdOrRoleId(id.intValue(), null);
        if (0 < userDao.deleteUserById(id))//删除用户
            return Results.success();
        return Results.failure();
    }

    /**
     * 添加user服务
     *
     * @Author: rxy
     * @Param: [userDto：userDto, roleId：角色id]
     * @return: {@link Results< SysUser>}
     */
    @Transactional
    @Override
    public Results<SysUser> save(SysUser sysUser, Integer roleId) {
        if (roleId != null) {
            userDao.save(sysUser);
            roleUserDao.save(new SysRoleUser(sysUser.getId().intValue(), roleId));
            return Results.success();
        }
        return Results.failure();
    }

    /**
     * 根据电话号码查询 用户信息
     *
     * @Author: rxy
     * @Param: [telephone] 电话号码
     * @return: {@link SysUser}
     */
    @Override
    public SysUser getUserByPhone(String telephone) {
        return userDao.getUserByPhone(telephone);
    }

    /**
     * 根据邮箱查询 用户信息
     *
     * @Author: rxy
     * @Param: [email] 邮箱
     * @return: {@link SysUser}
     */
    @Override
    public SysUser getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    /**
     * 更新用户信息
     *
     * @Author: rxy
     * @Param: [sysUser] 用户模型
     * @return: {@link Long}
     */
    @Transactional
    @Override
    public Results<SysUser> updateUser(SysUser sysUser, Integer roleId) {
        log.info(getClass().getName() + "updateUser().sysUser {}", sysUser);
        SysRoleUser roleUser = new SysRoleUser(sysUser.getId().intValue(), roleId);
        log.info(getClass().getName() + "updateUser().roleUser {}", roleUser);
        if (roleUserDao.getUserRoleByUserId(roleUser.getUserId()) != null) {
            roleUserDao.updateSysRoleUser(roleUser);//更新用户权限
        } else {
            roleUserDao.save(roleUser);//添加用户权限
        }
        if (0 < userDao.updateUser(sysUser))//更新用户信息
            return Results.success();
        return Results.failure();
    }

    /**
     * 模糊 分页查询
     *
     * @Author: rxy
     * @Param: [request：分页信息, userName：模糊条件]
     * @return: {@link Results< SysUser>}
     */
    @Override
    public Results<SysUser> findUserByFuzzyUserName(PageTableRequest request, String userName) {
        request.countOffset();//计算开始条数
        return Results.success(userDao.countUserByName(userName).intValue(), userDao.findUserByFuzzyUserName(userName, request.getOffset(), request.getLimit()));
    }
}
