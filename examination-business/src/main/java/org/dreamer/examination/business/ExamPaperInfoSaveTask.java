package org.dreamer.examination.business;

import org.dreamer.examination.entity.Examination;
import org.dreamer.examination.entity.Paper;
import org.dreamer.examination.entity.PaperQuestion;
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
    private PaperService paperService;
    private Examination examination;
    public ExamPaperInfoSaveTask(ExaminationService examService,PaperService paperService,
                                 Examination examination) {
        this.examService = examService;
        this.paperService = paperService;
        this.examination = examination;
    }

    @Override
    public Long[] call() throws Exception {
        Paper p = examination.getPaper();
        examService.addExamination(examination);
        p.toPaperQuestions(examination.getId());
        for(PaperQuestion pq : p.getPaperQuestions()){
            pq.setPaper(p);
        }
        paperService.addPaperQuestions(p.getPaperQuestions());
        return new Long[]{examination.getId(),examination.getPaper().getId()};
    }
}
