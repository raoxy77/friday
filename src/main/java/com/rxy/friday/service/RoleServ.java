package com.rxy.friday.service;

import com.rxy.friday.base.result.PageTableRequest;
import com.rxy.friday.base.result.Results;
import com.rxy.friday.dto.RoleDto;
import com.rxy.friday.model.SysRole;

/**
 * role服务接口定义
 */
public interface RoleServ {
    //获取全部角色
    Results<SysRole> getAllRole();

    /**
     * 获取一页数据
     *
     * @Author: rxy
     * @Param: [request] 分页参数
     * @return: {@link Results< SysRole>}
     */
    Results<SysRole> getAllRoleByPage(PageTableRequest request);

    /**
     * 模糊查询，并分页
     *
     * @Author: rxy
     * @Param: [request：分页参数, userName：模糊条件]
     * @return: {@link Results< SysRole>}
     */
    Results<SysRole> findRoleByFuzzyRoleName(PageTableRequest request, String roleName);

    /**
     * 根据id删除role
     *
     * @Author: rxy
     * @Param: [id] roleId
     * @return: {@link String}
     */
    Results<SysRole> deleteRoleById(Integer id);

    /**
     * 根据roleId查询角色（role）
     * @Author: rxy
     * @Param: [id]roleId
     * @return: {@link Results< SysRole>}
     */
    SysRole getRoleByRoleId(Integer id);

    /**
     * 添加角色
     *
     * @Author: rxy
     * @Param: [userDto]角色信息和权限值
     * @return: {@link Results< SysRole>}
     */
    Results<SysRole> save(RoleDto roleDto);

    /**
     * 更新角色
     *
     * @Author: rxy
     * @Param: [roleDto]角色信息和权限值
     * @return: {@link Results< SysRole>}
     */
    Results<SysRole> updateRole(RoleDto roleDto);
}
