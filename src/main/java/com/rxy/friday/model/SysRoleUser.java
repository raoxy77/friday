package com.rxy.friday.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleUser {
    private Integer userId;
    private Integer roleId;
}
