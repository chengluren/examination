package org.dreamer.examination.service;

import org.dreamer.examination.entity.Paper;
import org.dreamer.examination.entity.PaperQuestionVO;
import org.dreamer.examination.entity.Types;
import org.dreamer.examination.repository.PaperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Service
public class PaperService {

    @Autowired
    private PaperDao paperDao;

    public void addPaper(Paper paper) {
        paperDao.save(paper);
    }

    public List<Paper> getPaperByTemplate(long tempId) {
        return paperDao.findByTemplateId(tempId);
    }

    public Page<Paper> getPaperByTemplate(long tempId, int pageNum, int pageSize) {
        Pageable p = new PageRequest(pageNum, pageSize);
        return paperDao.findByTemplateId(tempId, p);
    }

    public Paper getPaper(long paperId){
       Paper p = paperDao.getOne(paperId);
       if (p!=null){

       }
       return p;
    }

//    public Paper getPaperById(long paperId) {
//        Paper p = paperDao.findOne(paperId);
//        String txt = p.getQuesIdTxt();
//        if (txt != null) {
//            Map<Types.QuestionType,List<PaperQuestionVO>> paperQuesMap = new HashMap<>();
//            String[] typedQues = txt.split("/");
//            for (String perType : typedQues) {
//                String[] ques = perType.split("|");
//                Types.QuestionType t = getQuestionType(ques[ques.length-1]);
//                List<PaperQuestionVO>
//                for (int i=0;i<ques.length-1;i++){
//
//                }
//            }
//        }
//        return p;
//    }

    public void deletePaper(long id) {
        paperDao.delete(id);
    }

    public void deletePaper(Paper paper) {
        paperDao.delete(paper);
    }

    private Types.QuestionType getQuestionType(String abbre){
        switch (abbre){
            case "CH":
                return Types.QuestionType.Choice;
            case "MC":
                return Types.QuestionType.MultipleChoice;
            case "TF":
                return Types.QuestionType.TrueFalse;
            case "CO":
                return Types.QuestionType.Completion;
            case "SA":
                return Types.QuestionType.ShortAnswer;
            default:
                return null;
        }
    }
}
