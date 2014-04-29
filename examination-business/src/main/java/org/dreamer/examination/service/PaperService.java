package org.dreamer.examination.service;

import org.dreamer.examination.entity.Paper;
import org.dreamer.examination.entity.PaperQuestion;
import org.dreamer.examination.repository.PaperDao;
import org.dreamer.examination.repository.PaperQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Service
public class PaperService {

    @Autowired
    private PaperDao paperDao;
    @Autowired
    private PaperQuestionDao paperQuestionDao;

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

    public List<PaperQuestion> getPaperQuestions(long paperId){
         return paperQuestionDao.findByPaperId(paperId);
    }

    public void addPaperQuestions(List<PaperQuestion> pqs){
         paperQuestionDao.save(pqs);
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


    public Long getPaperCount()
    {
       return  paperDao.getPaperCount() == null ? 0L :paperDao.getPaperCount() ;
    }
}
