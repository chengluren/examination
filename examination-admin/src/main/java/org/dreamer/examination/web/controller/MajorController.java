package org.dreamer.examination.web.controller;

import org.dreamer.examination.entity.College;
import org.dreamer.examination.entity.Major;
import org.dreamer.examination.service.CollegeService;
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

    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<MajorVO> majors(Long id) {

        List<College> colleges = collegeService.getAllColleges();
        List<MajorVO> result = toVO(colleges);
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
