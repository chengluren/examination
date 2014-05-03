package org.dreamer.examination.web.controller;

import org.dreamer.examination.entity.QuestionStore;
import org.dreamer.examination.entity.Result;
import org.dreamer.examination.search.NRTLuceneFacade;
import org.dreamer.examination.search.QuestionIndexer;
import org.dreamer.examination.service.QuestionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by lcheng on 2014/5/3.
 */
@Controller
@RequestMapping(value = "/index")
public class IndexAdminController {

    @Autowired
    private QuestionIndexer indexer;
    @Autowired
    private QuestionStoreService storeService;

    @RequestMapping(value = "/admin")
    public ModelAndView admin(){
        ModelAndView mv = new ModelAndView("exam.indexAdmin");
        List<QuestionStore> stores =  storeService.getAll();
        Map<String,?> indexStats = NRTLuceneFacade.instance().getIndexStats();
        mv.addObject("stores",stores);
        mv.addObject("stats",indexStats);
        return mv;
    }

    @RequestMapping(value = "/optimize")
    @ResponseBody
    public Result optimize(){
        Result r = null;
        try{
            indexer.optimize();
            r = new Result(true,"索引优化成功!");
        }catch (Exception e){
            r = new Result(false,"索引优化失败!");
            e.printStackTrace();
        }
        return r;
    }

    @RequestMapping(value = "/add/all")
    @ResponseBody
    public Result indexAll(){
        Result r = null;
        try{
            indexer.indexAll();
            r = new Result(true,"索引全部试题成功!");
        }catch (Exception e){
            r = new Result(false,"索引全部试题失败!");
            e.printStackTrace();
        }
        return r;
    }

    @RequestMapping(value = "/add/store")
    @ResponseBody
    public Result indexStore(Long storeId){
        Result r = null;
        try{
            indexer.indexByStore(storeId);
            r = new Result(true,"索引题库试题成功!");
        }catch (Exception e){
            r = new Result(false,"索引题库试题失败!");
            e.printStackTrace();
        }
        return r;
    }

    @RequestMapping(value = "/del/all")
    @ResponseBody
    public Result deleteAll(){
        Result r = null;
        try{
            indexer.deleteAll();
            r = new Result(true,"删除全部索引成功!");
        }catch (Exception e){
            r = new Result(false,"删除全部索引失败!");
            e.printStackTrace();
        }
        return r;
    }

    @RequestMapping(value = "/del/store")
    @ResponseBody
    public Result deleteStore(Long storeId){
        Result r = null;
        try{
            indexer.deleteByStore(storeId);
            r = new Result(true,"删除题库索引成功!");
        }catch (Exception e){
            r = new Result(false,"删除题库索引失败!");
            e.printStackTrace();
        }
        return r;
    }
}
