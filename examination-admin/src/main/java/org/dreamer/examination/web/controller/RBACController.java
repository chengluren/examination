package org.dreamer.examination.web.controller;

import org.dreamer.examination.entity.Result;
import org.dreamer.examination.entity.User;
import org.dreamer.examination.entity.UserRole;
import org.dreamer.examination.service.RBACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/rbac")
public class RBACController {

    @Autowired
    private RBACService rbacService;

    @RequestMapping(value = "/userRolesList")
    public ModelAndView userRoles(@PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.userRole");
        Page<User> users = rbacService.getAllUser(page);
        mv.addObject("users", users.getContent());
        mv.addObject("page", users.getNumber());
        mv.addObject("totalPage", users.getTotalPages());
        return mv;
    }

    @RequestMapping(value = "/userRoles")
    @ResponseBody
    public List<UserRole> getUserRoles(String userName) {
        return rbacService.getUserRole(userName);
    }

    @RequestMapping(value = "/userRole", method = RequestMethod.POST)
    @ResponseBody
    public Result addOrUpdateUserRole(UserRole userRole) {
        Result result = null;
        try {
            if (userRole != null) {
                rbacService.addUserRole(userRole);
                result = new Result(true, "保存用户角色成功!");
            }
        } catch (Exception e) {
            result = new Result(false, "保存用户角色失败!");
        }
        return result;
    }

    @RequestMapping(value = "/userRole/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result addOrUpdateUserRole(Long id){
        Result result = null;
        try {
            if (id != null) {
                rbacService.deleteUserRole(id);
                result = new Result(true, "删除用户角色成功!");
            }
        } catch (Exception e) {
            result = new Result(false, "删除用户角色失败!");
        }
        return result;
    }
}
