package com.rxy.friday.dao;

import com.rxy.friday.base.result.Results;
import com.rxy.friday.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * userDao 接口定义
 */
@Mapper
public interface UserDao {
    /**
     * 分页查询
     *
     * @Author: rxy
     * @Param: [startPosition：开始位置, limit：一页条数]
     * @return: {@link List< SysUser>}
     */
    @Select("select * from sys_user order by id limit #{startPosition}, #{limit}")
    List<SysUser> getAllUsersByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

    /**
     * 根据条件模糊查询 记录数
     *
     * @Author: rxy
     * @Param: [userName]模糊条件
     * @return: {@link Long}
     */
    @Select("select count(id) from sys_user where username like '%${userName}%'")
    Long countUserByName(@Param("userName") String userName);

    /**
     * 分页模糊查询
     *
     * @Author: rxy
     * @Param: [userName：模糊条件, startPosition：开始位置, limit：一页条数]
     * @return: {@link List< SysUser>}
     */
    @Select("select * from sys_user where username  like '%${userName}%' Limit #{startPosition},#{limit}")
    List<SysUser> findUserByFuzzyUserName(@Param("userName") String userName, @Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

    /**
     * 返回user总记录数
     *
     * @Author: rxy
     * @Param: []
     * @return: {@link Long}
     */
    @Select("select count(id) from sys_user")
    Long countAllUsers();

    /**
     * 根据用户id删除
     *
     * @Author: rxy
     * @Param: [id] userId
     * @return: {@link int}
     */
    @Delete("delete from sys_user where id = #{id}")
    int deleteUserById(Long id);


    /**
     * 根据用户id查询信息服务
     *
     * @Author: rxy
     * @Param: [id] 用户id
     * @return: {@link Results< SysUser>}
     */
    @Select("select * from sys_user where id = #{id}")
    SysUser getUserByUserId(Long id);

    /**
     * 添加user
     *
     * @Author: rxy
     * @Param: [userDto：userDto]
     * @return: {@link Long}
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
    Long save(SysUser sysUser);

    /**
     * 根据用户userName查询信息服务
     *
     * @Author: rxy
     * @Param: [userName]用户名
     * @return: {@link SysUser}
     */
    @Select("select * from sys_user where username = #{username}")
    SysUser getUserByUserName(String userName);

    /**
     * 根据邮箱查询 用户信息
     *
     * @Author: rxy
     * @Param: [email] 邮箱
     * @return: {@link SysUser}
     */
    @Select("select * from sys_user t where t.email = #{email}")
    SysUser getUserByEmail(String email);

    /**
     * 根据电话号码查询 用户信息
     *
     * @Author: rxy
     * @Param: [telephone] 电话号码
     * @return: {@link SysUser}
     */
    @Select("select * from sys_user t where t.telephone = #{telephone}")
    SysUser getUserByPhone(String telephone);

    /**
     * 更新用户信息
     *
     * @Author: rxy
     * @Param: [sysUser] 用户模型
     * @return: {@link Long}
     */
    Long updateUser(SysUser sysUser);


}
