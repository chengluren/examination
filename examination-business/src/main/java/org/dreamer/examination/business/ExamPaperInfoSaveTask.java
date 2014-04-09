package org.dreamer.examination.business;

import org.dreamer.examination.entity.Examination;
import org.dreamer.examination.service.ExamTemplateService;
import org.dreamer.examination.service.ExaminationService;
import org.dreamer.examination.service.PaperService;

import java.util.concurrent.Callable;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class ExamPaperInfoSaveTask implements Callable<Long[]> {

    private ExaminationService examService;
    private Examination examination;
    public ExamPaperInfoSaveTask(ExaminationService examService,Examination examination) {
        this.examService = examService;
        this.examination = examination;
    }

    @Override
    public Long[] call() throws Exception {
        examService.addExamination(examination);
        return new Long[]{examination.getId(),examination.getPaper().getId()};
    }
}
