package org.dreamer.examination.service;

import org.dreamer.examination.entity.StorePushSetting;
import org.dreamer.examination.entity.Types;
import org.dreamer.examination.repository.StorePushSettingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcheng on 2014/7/3.
 */
@Service
public class StorePushSettingService {

    @Autowired
    private StorePushSettingDao storePushDao;

    public void addSetting(StorePushSetting setting){
        storePushDao.save(setting);
    }

    public StorePushSetting getSetting(Long collegeId,Integer grade,Types.DegreeType degree){
        List<StorePushSetting> list = storePushDao.
                findByCollegeIdAndGradeAndDegree(collegeId,grade,degree);
        return list!=null && list.size()>0 ? list.get(0) : null;
    }
}
