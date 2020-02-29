package com.rxy.friday.dao;

import com.rxy.friday.model.SysRoleUser;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

/**
 * RoleUserDao 接口
 */
@Mapper
public interface RoleUserDao {
    /**
     * 根据userId或roleId删除用户角色表
     * @Author: rxy
     * @Param: [userId, roleId]其中必须有一个参数为null
     * @return: {@link int}
     */
//    @Delete("delete from sys_role_user where userid = #{userid}")
    Integer deleteRoleUserByUserIdOrRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 添加用户角色关系
     *
     * @Author: rxy
     * @Param: [sysRoleUser]
     * @return: {@link Integer}
     */
    @Insert("insert into sys_role_user(userId,roleId) values(#{userId},#{roleId});")
    Integer save(SysRoleUser sysRoleUser);

    /**
     * 根据用户id查询用户角色关系
     *
     * @Author: rxy
     * @Param: [userId]
     * @return: {@link SysRoleUser}
     */
    @Select("select * from sys_role_user where userId = #{userId}")
    SysRoleUser getUserRoleByUserId(@Param("userId") Integer userId);

    /**
     * 关系用户角色关系
     *
     * @Author: rxy
     * @Param: []
     * @return: {@link Integer}
     */
    Integer updateSysRoleUser(SysRoleUser sysRoleUser);
}
