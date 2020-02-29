package com.rxy.friday.dao;

import com.rxy.friday.model.SysPermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RolePermissionDao {
    /**
     * 为角色添加权限值
     *
     * @Author: rxy
     * @Param: [id, permissionIds]
     * @return: {@link Long}
     */
    Long save(@Param("roleId") Integer id, @Param("permissionIds") List<Long> permissionIds);

    /**
     * 指定用户删除权限值
     *
     * @Author: rxy
     * @Param: [id]角色id
     * @return: {@link Integer}
     */
    @Delete("delete from sys_role_permission where roleId = #{id}")
    Integer deleteRolePermission(Integer id);


}
