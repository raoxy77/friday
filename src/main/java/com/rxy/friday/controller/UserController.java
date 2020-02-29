package com.rxy.friday.controller;

import com.rxy.friday.base.result.PageTableRequest;
import com.rxy.friday.base.result.ResponseCode;
import com.rxy.friday.base.result.Results;
import com.rxy.friday.dto.UserDto;
import com.rxy.friday.model.SysUser;
import com.rxy.friday.service.UserServ;
import com.rxy.friday.util.MD5;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author rxy
 * @date 2020/2/20  19:28  星期四
 **/
@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {
    //    注入user服务
    @Autowired
    UserServ userServ;

    /**
     * 获取一页数据
     *
     * @Author: rxy
     * @Param: [request]
     * @return: {@link Results< SysUser>}
     */
    @ResponseBody
    @GetMapping("/list")
    public Results<SysUser> getUser(PageTableRequest request) {
        return userServ.getAllUsersByPage(request);
    }

    /**
     * 模糊查询
     *
     * @Author: rxy
     * @Param: [request:分页类, userName：模糊条件]
     * @return: {@link Results< SysUser>}
     */
    @GetMapping("/findUserByFuzzyUserName")
    @ResponseBody
    public Results<SysUser> findUserByFuzzyUserName(PageTableRequest request, @RequestParam("userName") String userName) {
//        log.debug("UserController------>findUserByFuzzyUserName() {}", userName);

        return userServ.findUserByFuzzyUserName(request, userName);
    }

    /**
     * 根据id删除用户
     *
     * @Author: rxy
     * @Param: [id] userId
     * @return: {@link String}
     */
    @ResponseBody
    @GetMapping("/delete")
    public Results<SysUser> deleteUserById(@RequestParam("id") Integer id) {

        return userServ.deleteUserByIdAndRoleUser(id.longValue());
    }

    /**
     * 校验用户数据是否可用
     *
     * @Author: rxy
     * @Param: [sysUser]用户模型
     * @return: {@link Results< SysUser>}
     */
    private Results<SysUser> verifyUser(@RequestBody SysUser sysUser) {
        return null;
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
        model.addAttribute("sysUser", userServ.getUserByUserId(id.longValue()));
        return "user/user-edit";
    }

    /**
     * 修改user信息
     *
     * @Author: rxy
     * @Param: [sysUser] 用户模型
     * @return: {@link Results< SysUser>}
     */
    @PostMapping("/edit")
    @ResponseBody
    public Results<SysUser> editUser(UserDto userDto, @RequestParam("roleId") Integer roleId) {
//        log.debug("UserController------>editUser() {}", sysUser.toString());
        SysUser sysUser = null;
        sysUser = userServ.getUserByUserName(userDto.getUsername());
        if (sysUser != null && !(sysUser.getId().equals(userDto.getId()))) {
            log.info(sysUser.getId().toString() + "||||||||" + userDto.getId());
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(), ResponseCode.USERNAME_REPEAT.getMessage());
        }
        sysUser = userServ.getUserByPhone(userDto.getTelephone());
        if (sysUser != null && !(sysUser.getId().equals(userDto.getId()))) {
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(), ResponseCode.PHONE_REPEAT.getMessage());
        }
        sysUser = userServ.getUserByEmail(userDto.getEmail());
        if (sysUser != null && !(sysUser.getId().equals(userDto.getId()))) {
            return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(), ResponseCode.EMAIL_REPEAT.getMessage());
        }

        return userServ.updateUser(userDto, roleId);
    }

//    @PostMapping("/edit")
//    public Results<SysUser> edituser(){
//        return userServ.updateUser();
//    }


    /**
     * 获取添加用户页面
     *
     * @Author: rxy
     * @Param: []
     * @return: {@link String}
     */
    @GetMapping("/add")
    public String getAdd(Model model) {
        model.addAttribute(new SysUser());
        return "user/user-add";
    }

    /**
     * 添加用户
     *
     * @Author: rxy
     * @Param: [sysUser] userModel
     * @return: {@link Results< SysUser>}
     */
    @PostMapping("/add")
    @ResponseBody
    public Results<SysUser> addUser(UserDto userDto,@RequestParam("roleId") Integer roleId) {
//        log.debug("UserController------>AddUser() {}", userDto.toString());
        userDto.setStatus(1);
//        userDto.setSex(0);
        userDto.setPassword(MD5.crypt(userDto.getPassword()));
        log.debug("userController----->addUser() {}", userDto.toString() + "roleId" + roleId);
        SysUser sysUser = null;
        sysUser = userServ.getUserByUserName(userDto.getUsername());
        if (sysUser != null && !(sysUser.getId().equals(userDto.getId()))) {
            log.info(sysUser.getId().toString() + "||||||||" + userDto.getId());
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(), ResponseCode.USERNAME_REPEAT.getMessage());
        }
        sysUser = userServ.getUserByPhone(userDto.getTelephone());
        if (sysUser != null && !(sysUser.getId().equals(userDto.getId()))) {
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(), ResponseCode.PHONE_REPEAT.getMessage());
        }
        sysUser = userServ.getUserByEmail(userDto.getEmail());
        if (sysUser != null && !(sysUser.getId().equals(userDto.getId()))) {
            return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(), ResponseCode.EMAIL_REPEAT.getMessage());
        }
        return userServ.save(userDto, roleId);
    }


    String pattern = "yyyy-MM-dd";

    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern), true));// CustomDateEditor为自定义日期编辑器
    }
}
