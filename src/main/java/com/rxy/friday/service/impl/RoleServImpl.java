package com.rxy.friday.service.impl;

import com.rxy.friday.base.result.PageTableRequest;
import com.rxy.friday.base.result.Results;
import com.rxy.friday.dao.PermissionDao;
import com.rxy.friday.dao.RoleDao;
import com.rxy.friday.dao.RolePermissionDao;
import com.rxy.friday.dao.RoleUserDao;
import com.rxy.friday.dto.RoleDto;
import com.rxy.friday.model.SysRole;
import com.rxy.friday.service.RoleServ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.xml.transform.Result;
import java.util.List;

/**
 * role服务实现
 *
 * @author rxy
 * @date 2020/2/22  15:54  星期六
 **/
@Slf4j
@Service
public class RoleServImpl implements RoleServ {
    //注入roleDao
    @Autowired
    private RoleDao roleDao;
    //注入userRoleDao
    @Autowired
    private RoleUserDao roleUserDao;
    //rolePermissionDao
    @Autowired
    private RolePermissionDao rolePermissionDao;

    /**
     * 获取全部角色
     *
     * @Author: rxy
     * @Param: []
     * @return: {@link Results< SysRole>}
     */
    @Override
    public Results<SysRole> getAllRole() {
        return Results.success(roleDao.countRole(), roleDao.getAllRole());
    }

    /**
     * 获取一页数据
     *
     * @Author: rxy
     * @Param: [request] 分页参数
     * @return: {@link Results< SysRole>}
     */
    @Override
    public Results<SysRole> getAllRoleByPage(PageTableRequest request) {
        request.countOffset();
        return Results.success(roleDao.countRole().intValue(), roleDao.getAllRoleByPage(request.getOffset(), request.getLimit()));
    }

    /**
     * 模糊查询，并分页
     *
     * @Author: rxy
     * @Param: [request：分页参数, userName：模糊条件]
     * @return: {@link Results< SysRole>}
     */
    @Override
    public Results<SysRole> findRoleByFuzzyRoleName(PageTableRequest request, String roleName) {
        request.countOffset();
        return Results.success(roleDao.countUserByName(roleName).intValue(), roleDao.findUserByFuzzyRoleName(request.getOffset(), request.getLimit(), roleName));
    }

    /**
     * 根据id删除角色
     *
     * @Author: rxy
     * @Param: [longValue] roleID
     * @return: {@link Results< SysRole>}
     */
    @Override
    @Transactional
    public Results<SysRole> deleteRoleById(Integer id) {
        //删除角色前必须删除 用户对应该角色的关联关系
        roleUserDao.deleteRoleUserByUserIdOrRoleId(null, id);
        log.info("deleteRoleById() {}", id.toString());
        if (0 < roleDao.deleteRoleById(id))
            return Results.success();
        return Results.failure();
    }

    /**
     * 根据roleId查询角色（role）
     *
     * @Author: rxy
     * @Param: [id]roleId
     * @return: {@link Results< SysRole>}
     */
    @Override
    public SysRole getRoleByRoleId(Integer id) {
        return roleDao.getRoleById(id);
    }

    /**
     * 添加角色
     *
     * @Author: rxy
     * @Param: [userDto]角色信息和权限值
     * @return: {@link Results< SysRole>}
     */
    @Override
    @Transactional
    public Results<SysRole> save(RoleDto roleDto) {
        roleDao.save(roleDto);//添加角色
        //添加角色的权限
        List<Long> permissionIds = roleDto.getPermissionIds();
        //移除0,permission id是从1开始
        permissionIds.remove(0L);
        //2、保存角色对应的所有权限
        if (!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.save(roleDto.getId(), permissionIds);
        }
        return Results.success();
    }

    /**
     * 更新角色
     *
     * @Author: rxy
     * @Param: [roleDto]角色信息和权限值
     * @return: {@link Results< SysRole>}
     */
    @Override
    @Transactional
    public Results<SysRole> updateRole(RoleDto roleDto) {
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);
        //先把该用户之前的权限值删除

        rolePermissionDao.deleteRolePermission(roleDto.getId());

        //2、判断该角色是否有赋予权限值，有就添加"
        if (!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.save(roleDto.getId(), permissionIds);
        }

        //3、更新角色表
        int countData = roleDao.update(roleDto).intValue();

        if (countData > 0) {
            return Results.success();
        } else {
            return Results.failure();
        }
    }
}
