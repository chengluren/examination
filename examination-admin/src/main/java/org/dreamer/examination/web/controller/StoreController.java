package org.dreamer.examination.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.dreamer.examination.entity.*;
import org.dreamer.examination.rbac.ShiroDatabaseRealm;
import org.dreamer.examination.service.*;
import org.dreamer.examination.vo.QuestionStoreVO;
import org.dreamer.examination.search.QuestionIndexer;
import org.dreamer.examination.utils.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.util.ArrayList;
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
    @Autowired
    private ExamScheduleService examScheduleService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private RBACService rbacService;
    @Autowired
    private StorePushSettingService storePushService;

    // private static String[] majors = {"M001", "M002", "M003", "M004", "M005"};
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/list")
    public ModelAndView getStoreInfoList(@PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.store-list");
        Page<QuestionStoreVO> vos = storeService.getStoreAndQuesCountInfo(page);
        mv.addObject("store", vos);
        mv.addObject("page", vos.getNumber() + 1);
        mv.addObject("totalPage", vos.getTotalPages());
        return mv;
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/add")
    public String addStore(ModelMap map) {
        // map.addAttribute("majors", majors);
        return "exam.store-add";
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStore(String name, String comment, Boolean generic, String storeMajor) {
        QuestionStore store = new QuestionStore(name, comment);
        if (generic != null) {
            store.setGeneric(generic);
        } else {
            store.setGeneric(false);
        }
        String[] majors = {};
        if (storeMajor != null) {
            majors = storeMajor.split(",");
        }
        storeService.addQuestionStore(store, majors);
        return "redirect:/store/list";
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editStore(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("exam.store-edit");
        QuestionStore store = storeService.getStore(id);
        List<MajorStoreRelation> rels = storeService.getMajorStoreRelation(id);
        StringBuilder sb = new StringBuilder();
        for (MajorStoreRelation rel : rels) {
            sb.append(rel.getMajor() + ",");
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        mv.addObject("store", store);
        mv.addObject("rels", sb.toString());
        //  mv.addObject("majors", majors);
        return mv;
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editStore(QuestionStore store, String storeMajor) {
        String[] majors = {};
        if (storeMajor != null && storeMajor.length() > 0) {
            majors = storeMajor.split(",");
        }
        storeService.updateQuestionStore(store, majors);
        return "redirect:/store/list";
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/delete/{id}")
    public String deleteStore(@PathVariable("id") long id) {
        storeService.deleteStore(id);
        checkAndDeleteIndex(id);
        return "redirect:/store/list";
    }

    @RequestMapping(value = "/college")
    public ModelAndView getCollegeMajorStore(Long mid) {
        ModelAndView mv = new ModelAndView("exam.store-major");
        ShiroDatabaseRealm.ShiroUser user = rbacService.getCurrentUser();
        if (user != null && user.getCollegeId() != -1) {
            Long collegeId = user.getCollegeId();
            College col = collegeService.getCollege(collegeId);
            List<Major> majors = col.getMajors();
            mv.addObject("majors", majors);
            if (mid == null) {
                if (majors.size() > 0) {
                    mid = majors.get(0).getId();
                }
            }
            if (mid != null) {
                mv.addObject("cmId",mid);
                List<QuestionStore> stores = storeService.getStoreForMajor(mid);
                mv.addObject("stores", stores);
            }
        }
        return mv;
    }

    @RequestMapping(value = "/major")
    @ResponseBody
    public JSONPObject getMajorStore(Long major, String callback) {
        List<QuestionStore> stores = storeService.getStoreForMajor(major);
        JSONPObject jsonp = new JSONPObject(callback, stores);
        return jsonp;
    }

    @RequestMapping(value = "/student")
    @ResponseBody
    public JSONPObject getStoreForStudent(Long stuId, String callback) {
        List<QuestionStore> stores = null;
        Object[] stuInfo = collegeService.getStudentBaseInfo(stuId);
        if (stuInfo!=null){
            Long collegeId = ((BigInteger)stuInfo[0]).longValue();
            Integer degree = Integer.valueOf((String)stuInfo[1]);
            Integer grade = (Integer)stuInfo[2];
            Long majorId = ((BigInteger)stuInfo[3]).longValue();
            Types.DegreeType type = (degree==0) ? Types.DegreeType.Bachelor : Types.DegreeType.Master;
            StorePushSetting setting = storePushService.getSetting(collegeId,grade,type);
            if (setting!=null){
                Integer pushType = setting.getPushType();
                if (pushType==0){
                    stores = storeService.getGenericStore();
                }else if (pushType==1){
                    stores = storeService.getDisciplineStoreForMajor(majorId);
                }else if (pushType == 2){
                    stores =storeService.getGenericStore();
                    stores.addAll(storeService.getDisciplineStoreForMajor(majorId));
                }
            }else {
                stores = new ArrayList<>();
            }
        }else {
            stores = new ArrayList<>();
        }
        JSONPObject jsonp = new JSONPObject(callback, stores);
        return jsonp;
    }

    @RequestMapping(value = "/pushSetting")
    public ModelAndView storePushSetting() {
        ModelAndView mv = new ModelAndView("exam.pushSetting");
        List<Integer> grades = examScheduleService.getStudentSessions();
        mv.addObject("grades", grades);
        ShiroDatabaseRealm.ShiroUser user = rbacService.getCurrentUser();
        if (user != null) {
            Long collegeId = user.getCollegeId();
            if (collegeId != null && collegeId == -1) {
                List<College> colleges = collegeService.getAllColleges();
                mv.addObject("colleges", colleges);
            } else {
                mv.addObject("college", collegeId);
            }
        }
        return mv;
    }
    @RequestMapping(value = "/pushSetting/get")
    @ResponseBody
    public StorePushSetting getStorePushSetting(
            Long collegeId, Integer grade, Integer degree) {
        Types.DegreeType degreeType = null;
        if (degree == 0){
            degreeType = Types.DegreeType.Bachelor;
        }else {
            degreeType = Types.DegreeType.Master;
        }
        StorePushSetting setting = null;
        setting = storePushService.getSetting(collegeId,grade,degreeType);
        if (setting==null){
            setting = new StorePushSetting(collegeId,grade,degreeType);
            setting.setPushType(-1);
        }
        return setting;
    }
    @RequestMapping(value = "/pushSetting/add",method = RequestMethod.POST)
    @ResponseBody
    public Result saveStorePushSetting(Long id,Long collegeId,
                                       Integer grade, Integer degree,Integer pushType){
        Types.DegreeType degreeType = null;
        if (degree == 0){
            degreeType = Types.DegreeType.Bachelor;
        }else {
            degreeType = Types.DegreeType.Master;
        }
        StorePushSetting setting =new StorePushSetting(collegeId,grade,degreeType,pushType);
        if (id!=null){
            setting.setId(id);
        }
        Result result = null;
        try{
            storePushService.addSetting(setting);
            result = new Result(true,"");
        }catch (Exception e){
            result = new Result(false,"");
        }
        return result;
    }

    private void checkAndDeleteIndex(long storeId) {
        String type = SysUtils.getConfigValue("question.list.type", "db");
        boolean index = type.equals("index") ? true : false;
        if (index) {
            QuestionIndexer indexer = SysUtils.getBean(QuestionIndexer.class);
            indexer.deleteByStore(storeId);
        }
    }
}
