package org.dreamer.examination.web.controller;

import org.dreamer.examination.entity.User;
import org.dreamer.examination.service.RBACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public Page<User> getUserRoles(Pageable page) {
        Page<User> users = rbacService.getAllUser(page);
        return users;
    }


}
