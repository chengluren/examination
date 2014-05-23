package org.dreamer.examination.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.dreamer.examination.entity.*;
import org.dreamer.examination.service.ExamScheduleService;
import org.dreamer.examination.service.ExamTemplateService;
import org.dreamer.examination.service.QuestionStoreService;
import org.dreamer.examination.vo.ComboGridData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/template")
public class ExamTemplateController {

    @Autowired
    private QuestionStoreService storeService;
    @Autowired
    private ExamTemplateService templateService;
    @Autowired
    private ExamScheduleService scheduleService;

    @RequestMapping(value = "/all")
    @ResponseBody
    public ComboGridData<ExamTemplate> getAllExamTemplate(Integer page,Integer rows,String searchTerm){
        Pageable p = new PageRequest(page-1,rows);
        String name = "%";
        if (StringUtils.isNotEmpty(searchTerm)){
            try {
                searchTerm =new String(searchTerm.getBytes("ISO8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            name = "%"+searchTerm+"%";
        }
        Page<ExamTemplate> templates = templateService.getExamTemplateByNameLike(name,p);
        ComboGridData<ExamTemplate> result = new ComboGridData<>();
        result.setPage(templates.getNumber()+1);
        result.setTotal(templates.getTotalPages());
        result.setRecords(templates.getNumberOfElements());
        result.setRows(templates.getContent());
        return result;
    }

    @RequestMapping(value = "/new")
    public ModelAndView newExamTemplate() {
        ModelAndView mv = new ModelAndView("exam.temp-new");
        List<QuestionStore> stores = storeService.getAll();
        mv.addObject("stores", stores);
        return mv;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Result newExamTemplate(String template) {
        Result result = null;
        JSONObject json = new JSONObject(template);
        ExamTemplate examTemplate = parseJsonToTemplate(json);
        templateService.addExamTemplate(examTemplate);
        result = new Result(true, "ddd");
        return result;
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView examTemplateList(String name, Pageable page) {
        ModelAndView mv = new ModelAndView("exam.temp-list");
        Page<ExamTemplate> templates = null;
        if (name != null) {
            templates = templateService.getExamTemplateByNameLike("%" + name + "%", page);
        } else {
            templates = templateService.getExamTemplate(page);
        }
        List<ExamTemplate> list = templates.getContent();
        mv.addObject("temps", getTemplateStatInfo(list));
        mv.addObject("page", templates.getNumber() + 1);
        mv.addObject("totalPage", templates.getTotalPages());
        return mv;
    }

    @RequestMapping(value = "/update", method =  RequestMethod.POST)
    @ResponseBody
    public Result updateTemplate(Long tempId,String name,float passScore,boolean mixedIn){
         Result result = null;
         try{
             templateService.updateTemplate(tempId,name,passScore,mixedIn);
             result = new Result(true,"更新方案成功！");
         }catch (Exception e){
             e.printStackTrace();
             result = new Result(false,"更新方案失败!");
         }
        return result;
    }

    @RequestMapping(value = "/mc/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> tempMustChoseList(Long tempId, Pageable page) {
        Page<List<Object[]>> mustChoose = templateService.getExamTemplateMustChooseDef(tempId, page);
        Map<String, Object> result = new HashMap<>();
        result.put("totalPage", mustChoose.getTotalPages());
        result.put("page", mustChoose.getNumber());
        result.put("data", mustChoose.getContent());
        return result;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView editTemplate(Long tempId) {
        ModelAndView mv = new ModelAndView("exam.temp-edit");
        List<QuestionStore> stores = storeService.getAll();
        List<Object[]> baseInfo = templateService.getExamTemplateInfo(tempId);

        Pageable p = new PageRequest(0, 10);
        Page<List<Object[]>> mcDefs = templateService.getExamTemplateMustChooseDef(tempId, p);
        List<Object[]> tempQuesDefs = templateService.getExamTemplateQuesDef(tempId);
        if (baseInfo != null && baseInfo.size() > 0) {
            mv.addObject("baseInfo", baseInfo.get(0));
            mv.addObject("mcDefs", mcDefs.getContent());
            mv.addObject("mcDefsPage", 0);
            mv.addObject("mcDefsTotalPage", mcDefs.getTotalPages());
            mv.addObject("tempQuesDefs", tempQuesDefs);
        }
        mv.addObject("stores", stores);
        return mv;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result deleteTemplate(Long tempId) {
        Result result = null;
        int countOfSchedule = scheduleService.getScheduleCountUseTemp(tempId);
        if (countOfSchedule > 0) {
            result = new Result(false, "该考试方案已被应用到考试日程安排，目前无法删除！");
        } else {
            templateService.deleteExamTemplate(tempId);
            result = new Result(true, "成功删除方案!");
        }
        return result;
    }

    @RequestMapping(value = "/mc/delete")
    @ResponseBody
    public Result deleteTemplateMustChoose(Long id) {
        Result result = null;
        try {
            templateService.deleteTemplateMustChoose(id);
            result = new Result(true, "删除成功!");
        } catch (Exception e) {
            result = new Result(false, "删除失败!");
        }
        return result;
    }

    @RequestMapping(value = "/mc/add")
    @ResponseBody
    public Result addMustChoose(String mcs){
        Result result = null;
        try {
            JSONObject json = new JSONObject(mcs);
            Long tempId = json.getLong("tempId");
            JSONArray jarr = json.getJSONArray("mcs");
            List<MustChooseQuestionDef> defs = new ArrayList<>();
            for (int i = 0; i < jarr.length(); i++) {
                JSONObject jo = jarr.getJSONObject(i);
                Long quesId = jo.getLong("quesId");
                String quesType = jo.getString("quesType");
                String score = jo.getString("score");
                MustChooseQuestionDef def = new MustChooseQuestionDef(quesId,
                        Types.QuestionType.getTypeFromShortName(quesType), Float.valueOf(score));
                defs.add(def);
            }
            templateService.addMustChooseDefs(defs, tempId);
            result = new Result(true,"添加必考题成功！");
        }catch(Exception e){
            result = new Result(false,"添加必考题失败！");
        }
        return result;
    }

    @RequestMapping(value = "/quesDef/delete")
    @ResponseBody
    public Result deleteTemplateQuesDef(Long id) {
        Result result = null;
        try {
            templateService.deleteTemplateQuestionDef(id);
            result = new Result(true, "删除成功!");
        } catch (Exception e) {
            result = new Result(false, "删除失败!");
        }
        return result;
    }

    @RequestMapping(value = "/quesDef/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addTemplateQuesDef(Long storeId,String quesType,int count, float score, Long tempId) {
        Result result = null;
        try{
            Types.QuestionType type = Types.QuestionType.getTypeFromShortName(quesType);
            TemplateQuestionDef def = new TemplateQuestionDef(storeId,type,count,score);
            templateService.addExamTempQuesDef(def,tempId);
            result = new Result(true,String.valueOf(def.getId()));
        }catch(Exception e){
            result = new Result(false,"添加随机题定义失败!");
        }

        return result;
    }

    private List<Map<String, ?>> getTemplateStatInfo(List<ExamTemplate> temps) {
        List<Map<String, ?>> result = new ArrayList<>();
        for (ExamTemplate temp : temps) {
            Map<String, Object> record = new HashMap<>();
            record.put("name", temp.getName());
            record.put("id", temp.getId());
            record.put("passScore", temp.getPassScore());
            int quesCount = 0;
            float totalScore = 0;
            List<TemplateQuestionDef> tqDefList = temp.getQuestionDefs();
            if (tqDefList != null && tqDefList.size() > 0) {
                for (TemplateQuestionDef def : tqDefList) {
                    quesCount += def.getCount();
                    totalScore += (def.getScorePer() * def.getCount());
                }
            }
            List<MustChooseQuestionDef> mcDefList = temp.getMustChooseDefs();
            if (mcDefList != null && mcDefList.size() > 0) {
                for (MustChooseQuestionDef def : mcDefList) {
                    quesCount += 1;
                    totalScore += def.getQuesScore();
                }
            }
            record.put("totalCount", quesCount);
            record.put("totalScore", totalScore);
            result.add(record);
        }
        return result;
    }


    private ExamTemplate parseJsonToTemplate(JSONObject json) {
        ExamTemplate template = new ExamTemplate();
        String name = json.getString("name");
        boolean mcMixedIn = json.getBoolean("multiChooseMixedIn");
        String passSoreStr = json.getString("passScore");
        float passSore = Float.valueOf(passSoreStr);

        template.setName(name);
        template.setPassScore(passSore);
        template.setMultiChoiceMixedInChoice(mcMixedIn);

        JSONArray mcdefJson = json.getJSONArray("mustChooseDefs");
        if (mcdefJson != null) {
            int len = mcdefJson.length();
            List<MustChooseQuestionDef> mcdefList = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                JSONObject mcdef = mcdefJson.getJSONObject(i);
                MustChooseQuestionDef def = new MustChooseQuestionDef();
                def.setQuestionId(mcdef.getLong("quesId"));
                String scoreStr = mcdef.getString("score");
                def.setQuesScore(Float.valueOf(scoreStr));
                def.setQuestionType(Types.QuestionType.getTypeFromShortName(mcdef.getString("quesType")));
                def.setTemplate(template);
                mcdefList.add(def);
            }
            template.setMustChooseDefs(mcdefList);
        }

        JSONArray tempDefJson = json.getJSONArray("tempQuesDefs");
        if (tempDefJson != null) {
            int len = tempDefJson.length();
            List<TemplateQuestionDef> tempDefList = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                JSONObject tempDef = tempDefJson.getJSONObject(i);
                Long storeId = tempDef.getLong("storeId");
                int count = tempDef.getInt("count");
                Types.QuestionType type = Types.QuestionType.getTypeFromShortName(tempDef.getString("quesType"));
                String score = tempDef.getString("score");
                TemplateQuestionDef def = new TemplateQuestionDef(storeId, type, count, Float.valueOf(score));
                def.setTemplate(template);
                tempDefList.add(def);
            }
            template.setQuestionDefs(tempDefList);
        }

        return template;

    }
}
