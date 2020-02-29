package com.rxy.friday.dao;

import com.rxy.friday.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionDao {
    //获取全部权限值
    @Select("select * from sys_permission")
    List<SysPermission> getAllPermission();

    /**
     * 根据角色id获取角色所有权限值
     *
     * @Author: rxy
     * @Param: [roleId]
     * @return: {@link List< SysPermission>}
     */
    @Select("select * from sys_permission p JOIN sys_role_permission rp on p.id = rp.permissionId where rp.roleId=#{roleId} ORDER BY p.sort")
    List<SysPermission> getPermissionsByRoleId(Integer roleId);
}
