package com.rxy.friday.dto;

import com.rxy.friday.model.SysRole;

import java.util.List;

/**
 * @author rxy
 * @date 2020/2/22  19:31  星期六
 **/
public class RoleDto extends SysRole {
    private static final long serialVersionUID = -5784234789156935003L;

    private List<Long> permissionIds;

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
