package org.dreamer.examination.service;

import org.dreamer.examination.entity.Examination;
import org.dreamer.examination.entity.Paper;
import org.dreamer.examination.repository.ExaminationDao;
import org.dreamer.examination.repository.PaperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Service
public class ExaminationService {

    @Autowired
    private ExaminationDao examDao;
    @Autowired
    private PaperDao paperDao;

    public void addExamination(Examination examination){
        Paper p = examination.getPaper();
        paperDao.save(p);
        examination.setPaper(p);
        examDao.save(examination);
    }

    public Examination getExamination(long examId){
        return examDao.findOne(examId);
    }
}
