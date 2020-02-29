package com.rxy.friday.controller;

import com.rxy.friday.base.result.PageTableRequest;
import com.rxy.friday.base.result.Results;
import com.rxy.friday.dto.RoleDto;
import com.rxy.friday.model.SysRole;
import com.rxy.friday.service.RoleServ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author rxy
 * @date 2020/2/22  15:40  星期六
 **/
@Slf4j
@Controller
@RequestMapping("role")
public class RoleController {

    //注入roleServ服务实现
    @Autowired
    RoleServ roleServ;

    /**
     * 获取全部role信息
     *
     * @Author: rxy
     * @Param: []
     * @return: {@link Results< SysRole>}
     */
    @GetMapping("/all")
    @ResponseBody
    public Results<SysRole> getAllRole() {
        return roleServ.getAllRole();
    }


    /**
     * 获取一页数据
     *
     * @Author: rxy
     * @Param: [request] 分页参数
     * @return: {@link Results< SysRole>}
     */
    @ResponseBody
    @GetMapping("/list")
    public Results<SysRole> getRole(PageTableRequest request) {
        return roleServ.getAllRoleByPage(request);
    }


    /**
     * 模糊查询，并分页
     *
     * @Author: rxy
     * @Param: [request：分页参数, userName：模糊条件]
     * @return: {@link Results< SysRole>}
     */
    @GetMapping("/findRoleByFuzzyRoleName")
    @ResponseBody
    public Results<SysRole> findRoleByFuzzyRoleName(PageTableRequest request, @RequestParam("roleName") String roleName) {
//        log.debug("UserController------>findUserByFuzzyUserName() {}", userName);

        return roleServ.findRoleByFuzzyRoleName(request, roleName);
    }

    /**
     * 根据id删除role
     *
     * @Author: rxy
     * @Param: [id] roleId
     * @return: {@link String}
     */
    @ResponseBody
    @GetMapping("/delete")
    public Results<SysRole> deleteRoleById(@RequestParam("id") Integer id) {
        log.debug("RoleController----------->deleteRoleById()  {}", id);
        return roleServ.deleteRoleById(id);
    }


    /**
     * 获取编辑页面
     *
     * @Author: rxy
     * @Param: [id]
     * @return: {@link String}
     */
    @GetMapping("/edit")
    public String getEdit(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("sysRole", roleServ.getRoleByRoleId(id));
        return "role/role-edit";
    }

    /**
     * 获取添加用户页面
     *
     * @Author: rxy
     * @Param: []
     * @return: {@link String}
     */
    @GetMapping("/add")
    public String getAdd(Model model) {
        model.addAttribute("sysRole", new SysRole());
        return "role/role-add";
    }

    /**
     * 修改role信息
     *
     * @Author: rxy
     * @Param: [roleDto] role模型
     * @return: {@link Results< SysRole>}
     */
    @PostMapping("/edit")
    @ResponseBody
    public Results<SysRole> editUser(@RequestBody RoleDto roleDto) {

        return roleServ.updateRole(roleDto);
    }


    /**
     * 添加角色
     *
     * @Author: rxy
     * @Param: [sysUser] userModel
     * @return: {@link Results< SysRole>}
     */
    @PostMapping("/add")
    @ResponseBody
    public Results<SysRole> addUser(@RequestBody RoleDto roleDto) {
        return roleServ.save(roleDto);
    }


    String pattern = "yyyy-MM-dd";

    /**
     * 解析前端日期字符串（转为date格式）
     *
     * @Author: rxy
     * @Param: [binder, request]
     * @return: {@link }
     */
    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern), true));// CustomDateEditor为自定义日期编辑器
    }
}
