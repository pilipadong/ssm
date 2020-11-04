package com.xgd.controller;

import com.xgd.pojo.Role;
import com.xgd.pojo.UserInfo;
import com.xgd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 用户关联角色操作-添加角色
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] roleids){

        userService.addRoleToUser(userId,roleids);
        return "redirect:findAll.do";
    }


    /**
     * 用户关联角色操作
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)String id){
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo=userService.findByid(id);
        List<Role> rolelist=userService.findOtherRoles(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",rolelist);
        mv.setViewName("user-role-add");
        return  mv;
    }



    //查询用户详情
    @RequestMapping("/findById")
    public ModelAndView findByid(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo= userService.findByid(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show1");
        return mv;
    }


    //添加用户
    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='admin'")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //查询全部
    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView  findAll(){
        List<UserInfo>  userInfoList = userService.findAll();
        ModelAndView  mv = new ModelAndView();
        mv.addObject("userList",userInfoList);
        mv.setViewName("user-list");
        return mv;
    }
}
