package org.dreamer.examination.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
    public String login(){
        return "login";
    }
}
