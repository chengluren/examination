package org.dreamer.examination.web.controller;

import org.dreamer.examination.entity.College;
import org.dreamer.examination.entity.Major;
import org.dreamer.examination.rbac.ShiroDatabaseRealm;
import org.dreamer.examination.service.CollegeService;
import org.dreamer.examination.service.RBACService;
import org.dreamer.examination.vo.MajorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcheng on 2014/5/11.
 */
@Controller
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private CollegeService collegeService;
    @Autowired
    private RBACService rbacService;

    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<MajorVO> majors() {
        List<College> colleges = null;
        List<MajorVO> result = null;
        ShiroDatabaseRealm.ShiroUser user = rbacService.getCurrentUser();
        if (user!=null){
            List<String> userRoles = rbacService.getUserRoleStr(user.getUserName());
            if (userRoles.contains("admin")){
                colleges = collegeService.getAllColleges();
                result = toVO(colleges);
            }else{
                Long cid = user.getCollegeId();
                College college  = collegeService.getCollege(cid);
                List<College> list = new ArrayList<>();
                list.add(college);
                result = toVO(list);
            }
        }else {
            result = new ArrayList<>();
        }
        return result;
    }
    @RequestMapping(value = "/tree/college")
    @ResponseBody
    public List<MajorVO> collegeMajors(Long id){
        College college = collegeService.getCollege(id);
        List<College> list = new ArrayList<>();
        list.add(college);
        List<MajorVO> result = toVO(list);
        return result;
    }

    private List<MajorVO> toVO(List<College> colleges) {
        List<MajorVO> result = new ArrayList<>();
        if (colleges != null) {
            for (College college : colleges) {
                MajorVO vo = toVO(college);
                result.add(vo);
            }
        }
        return result;
    }

    private MajorVO toVO(College college) {
        MajorVO vo = new MajorVO(null,college.getName(),false);
        vo.setNocheck(true);
        List<Major> majors = college.getMajors();
        if (majors!=null && majors.size()>0){
            List<MajorVO> children = new ArrayList<>();
            for (Major major : majors){
                MajorVO v = toVO(major);
                children.add(v);
            }
           vo.setChildren(children);
        }
        return vo;
    }

    private MajorVO toVO(Major major) {
        MajorVO vo = new MajorVO(major.getId(),major.getName());
        return vo;
    }
}
