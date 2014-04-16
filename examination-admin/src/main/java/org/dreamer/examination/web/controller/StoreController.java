package org.dreamer.examination.web.controller;

import org.codehaus.jackson.map.util.JSONPObject;
import org.dreamer.examination.entity.QuestionStore;
import org.dreamer.examination.service.QuestionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/store")
public class StoreController {

    @Autowired
    private QuestionStoreService storeService;

    @RequestMapping(value = "/{major}")
    @ResponseBody
    public JSONPObject getMajorStore(@PathVariable("major")String major,String callback){
        List<QuestionStore> stores = storeService.getStoreForMajor(major);
        JSONPObject jsonp = new JSONPObject(callback,stores);
        return jsonp;
    }
}
