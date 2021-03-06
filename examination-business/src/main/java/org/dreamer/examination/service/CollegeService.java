package org.dreamer.examination.service;

import org.dreamer.examination.entity.College;
import org.dreamer.examination.repository.CollegeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcheng on 2014/5/11.
 */
@Service
public class CollegeService {

    @Autowired
    private CollegeDao collegeDao;

    public List<College> getAllColleges() {
        return collegeDao.findAll();
    }

    public College getCollege(Long id) {
        return collegeDao.findOne(id);
    }

    public List<College> getCollegeByName(String name) {
        return collegeDao.findByName(name);
    }

    public Object[] getStudentBaseInfo(Long stuId) {
        List<Object[]> list = collegeDao.findStudentBaseInfo(stuId);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }
}
