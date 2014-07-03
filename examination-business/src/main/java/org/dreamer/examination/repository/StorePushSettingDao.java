package org.dreamer.examination.repository;

import org.dreamer.examination.entity.StorePushSetting;
import org.dreamer.examination.entity.Types;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lcheng on 2014/7/3.
 */
public interface StorePushSettingDao extends JpaRepository<StorePushSetting,Long> {

    public List<StorePushSetting> findByCollegeIdAndGradeAndDegree(
            Long collegeId,Integer grade,Types.DegreeType degree);
}
