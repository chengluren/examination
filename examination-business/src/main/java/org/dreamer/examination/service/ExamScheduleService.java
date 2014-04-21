package org.dreamer.examination.service;

import org.dreamer.examination.entity.ExamSchedule;
import org.dreamer.examination.entity.ExamScheduleVO;
import org.dreamer.examination.repository.ExamScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamScheduleService {

    @Autowired
    private ExamScheduleDao scheduleDao;

    public void addExamSchedule(ExamSchedule schedule) {
        scheduleDao.save(schedule);
    }

    public ExamSchedule getExamSchedule(long id) {
        return scheduleDao.findOne(id);
    }

    public Long getExamTemplateId(String major) {
        Pageable p = new PageRequest(0, 1);
        List<Long> list = scheduleDao.findScheduleByDate(major, p);
        return (list != null && list.size() == 1) ? list.get(0) : null;
    }

    public List<ExamScheduleVO> getExamSchedule(String major){
        return scheduleDao.findSchedule(major);
    }
}
