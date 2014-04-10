package org.dreamer.examination.web.controller;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "index")
    @ResponseBody
    public JSONPObject index(ModelMap map,@RequestParam String callback,HttpServletRequest request){
        map.put("name","lcheng");
        HttpSession session = request.getSession();
        if (session!=null){
            System.out.println(session.getId());
        }
        return new JSONPObject(callback,map);
    }

}
