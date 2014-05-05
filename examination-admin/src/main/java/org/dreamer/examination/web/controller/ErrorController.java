package org.dreamer.examination.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    public String unauthorized(){
        return "exam.unauthorized";
    }
}
