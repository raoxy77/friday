package com.rxy.friday.dao;

import com.rxy.friday.base.result.PageTableRequest;
import com.rxy.friday.base.result.Results;
import com.rxy.friday.dto.RoleDto;
import com.rxy.friday.model.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Queue;

/**
 * RoleDao 接口定义
 */
@Mapper
public interface RoleDao {
    /**
     * 获取全部role
     *
     * @Author: rxy
     * @Param: []
     * @return: {@link List< SysRole>}
     */
    @Select("select * from sys_role")
    List<SysRole> getAllRole();

    /**
     * 获取角色数量
     *
     * @Author: rxy
     * @Param: []
     * @return: {@link Integer}
     */
    @Select("select count(id) from sys_role")
    Integer countRole();

    /**
     * 获取一页数据
     *
     * @Author: rxy
     * @Param: [startPosition：开始位置, limit：一页条数]
     * @return: {@link List< SysRole>}
     */
    @Select("select * from sys_role order by id limit #{startPosition}, #{limit}")
    List<SysRole> getAllRoleByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

    /**
     * 分页模糊查询
     *
     * @Author: rxy
     * @Param: [roleName：模糊条件, offset：开始位置, limit：一页条数]
     * @return: {@link List< SysRole>}
     */
    @Select("select * from sys_role where name  like '%${name}%' Limit #{startPosition},#{limit}")
    List<SysRole> findUserByFuzzyRoleName(@Param("startPosition") Integer offset, @Param("limit") Integer limit, @Param("name") String roleName);

    /**
     * 根据条件模糊查询 记录数
     *
     * @Author: rxy
     * @Param: [name]模糊条件
     * @return: {@link Long}
     */
    @Select("select count(id) from sys_role where name like '%${roleName}%'")
    Long countUserByName(@Param("roleName") String roleName);

    /**
     * 根据id删除role
     *
     * @Author: rxy
     * @Param: [id] roleid
     * @return: {@link Integer}
     */
    @Delete("delete from sys_role where id = #{id}")
    Integer deleteRoleById(Integer id);

    /**
     * 添加角色
     *
     * @Author: rxy
     * @Param: [role]
     * @return: {@link Long}
     */
    Long save(SysRole role);

    /**
     * 更新用户和权限值
     *
     * @Author: rxy
     * @Param: [roleDto]用户信息和权限值
     * @return: {@link Long}
     */
    Long update(RoleDto roleDto);

    /**
     * 根据roleId删除角色（role）
     * @Author: rxy
     * @Param: [id]
     * @return: {@link SysRole}
     */
    @Select("select * from sys_role where id = #{id}")
    SysRole getRoleById(Integer id);
}
