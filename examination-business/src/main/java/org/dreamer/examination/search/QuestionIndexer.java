package org.dreamer.examination.search;

import org.apache.lucene.index.Term;
import org.dreamer.examination.entity.ChoiceQuestion;
import org.dreamer.examination.entity.MultipleChoiceQuestion;
import org.dreamer.examination.entity.Question;
import org.dreamer.examination.entity.TrueOrFalseQuestion;
import org.dreamer.examination.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lcheng on 2014/5/3.
 */
@Component
public class QuestionIndexer {

    @Autowired
    private QuestionService questionService;

    public void indexAll() {

        Thread cht = new Thread(new TypedQuestionIndexer(questionService, ChoiceQuestion.class));
        Thread mct = new Thread(new TypedQuestionIndexer(questionService, MultipleChoiceQuestion.class));
        Thread tft = new Thread(new TypedQuestionIndexer(questionService, TrueOrFalseQuestion.class));

        cht.start();
        mct.start();
        tft.start();

        try {
            cht.join();
            mct.join();
            tft.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void indexByStore(Long storeId) {
        Thread cht = new Thread(new TypedAndStoredQuestionIndexer(questionService, ChoiceQuestion.class, storeId));
        Thread mct = new Thread(new TypedAndStoredQuestionIndexer(questionService, MultipleChoiceQuestion.class, storeId));
        Thread tft = new Thread(new TypedAndStoredQuestionIndexer(questionService, TrueOrFalseQuestion.class, storeId));
        cht.start();
        mct.start();
        tft.start();

        try {
            cht.join();
            mct.join();
            tft.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        NRTLuceneFacade.instance().truncate();
    }

    public void deleteByStore(Long storeId) {
        NRTLuceneFacade.instance().deleteIndex(new Term("sid", storeId.toString()));
    }

    public void optimize(){
        NRTLuceneFacade.instance().optimize();
    }

}
