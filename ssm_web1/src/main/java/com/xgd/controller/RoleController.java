package com.xgd.controller;

import com.xgd.pojo.Permission;
import com.xgd.pojo.Role;
import com.xgd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] ids ){
      roleService.addPermissionToRole(roleId,ids);
      return "redirect:findAll.do";
    }


    @RequestMapping("/findRoleByIdPermission")
    public ModelAndView findRoleByIdPermission(@RequestParam(name = "id",required = true) String id){
          ModelAndView mv=new ModelAndView();
          mv.addObject("roleId",id);
          List<Permission> permissionlist=roleService.findByRoleIdOtherPermission(id);
          mv.addObject("permissionList",permissionlist);
          mv.setViewName("role-permission-add");
          return mv;
    }


    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Role> list=roleService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("roleList",list);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }
}
