package org.dreamer.examination.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.dreamer.examination.entity.QuestionStore;
import org.dreamer.examination.entity.QuestionStoreVO;
import org.dreamer.examination.service.QuestionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/list")
    public ModelAndView getStoreInfoList(Pageable page){
        ModelAndView mv = new ModelAndView("exam.store-list");
        Page<QuestionStoreVO> vos =storeService.getStoreAndQuesCountInfo(page);
        mv.addObject("store",vos);
        return mv;
    }

    @RequestMapping(value = "/major")
    @ResponseBody
    public JSONPObject getMajorStore(String major,String callback){
        List<QuestionStore> stores = storeService.getStoreForMajor(major);
        JSONPObject jsonp = new JSONPObject(callback,stores);
        return jsonp;
    }
}
