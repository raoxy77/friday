package com.rxy.friday.service;

import com.rxy.friday.base.result.PageTableRequest;
import com.rxy.friday.base.result.Results;
import com.rxy.friday.dto.UserDto;
import com.rxy.friday.model.SysUser;

/**
 * user 服务接口
 *
 * @author rxy
 * @date 2020/2/21  09:14  星期五
 **/
public interface UserServ {

    /**
     * 根据用户名查询信息服务
     *
     * @Author: rxy
     * @Param: [userName] 用户名
     * @return: {@link Results< SysUser>}
     */
    SysUser getUserByUserName(String userName);

    /**
     * 根据用户id查询信息服务
     *
     * @Author: rxy
     * @Param: [id] 用户id
     * @return: {@link Results< SysUser>}
     */
    SysUser getUserByUserId(Long id);

    /**
     * 分页查询服务
     *
     * @Author: rxy
     * @Param: [request] 分页信息
     * @return: {@link Results< SysUser>}
     */
    Results<SysUser> getAllUsersByPage(PageTableRequest request);


    /**
     * 根据用户id删除服务
     *
     * @Author: rxy
     * @Param: [id] userId
     * @return: {@link int}
     */
    Results deleteUserByIdAndRoleUser(Long id);


    /**
     * 添加user服务
     *
     * @Author: rxy
     * @Param: [userDto：userDto, roleId：角色id]
     * @return: {@link Results< SysUser>}
     */
    Results<SysUser> save(SysUser sysUser, Integer roleId);

    /**
     * 根据电话号码查询 用户信息
     *
     * @Author: rxy
     * @Param: [telephone] 电话号码
     * @return: {@link SysUser}
     */
    SysUser getUserByPhone(String telephone);

    /**
     * 根据邮箱查询 用户信息
     *
     * @Author: rxy
     * @Param: [email] 邮箱
     * @return: {@link SysUser}
     */
    SysUser getUserByEmail(String email);

    /**
     * 更新用户信息
     *
     * @Author: rxy
     * @Param: [sysUser] 用户模型
     * @return: {@link Long}
     */
    Results<SysUser> updateUser(SysUser sysUser, Integer roleId);

    /**
     * 分页查询
     * @Author: rxy
     * @Param: [request：分页信息, userName：模糊条件]
     * @return: {@link Results< SysUser>}
     */
    Results<SysUser> findUserByFuzzyUserName(PageTableRequest request, String userName);

//    Results<SysUser> updateUser();
}
