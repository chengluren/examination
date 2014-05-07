package org.dreamer.examination.search;

import org.apache.lucene.document.*;
import org.apache.lucene.index.Term;
import org.dreamer.examination.entity.*;
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

    public void updateQuestionIndex(QuestionVO vo){
        Document doc = new Document();
        StringField idf = new StringField("id", vo.getId().toString(), Field.Store.YES);
        TextField stemf = new TextField("stem", vo.getStem(), Field.Store.YES);
        String mc = vo.isMustChoose() ? "1" : "0";
        StoredField mcf = new StoredField("mc", mc);
        StoredField answerf = new StoredField("ans", vo.getAnswer());
        StringField storeIdf = new StringField("sid", vo.getStoreId().toString(), Field.Store.YES);
        StringField quesTypef = new StringField("qt", vo.getQuesType(), Field.Store.NO);
        doc.add(idf);
        doc.add(stemf);
        doc.add(mcf);
        doc.add(answerf);
        doc.add(storeIdf);
        doc.add(quesTypef);
        NRTLuceneFacade.instance().reIndex(new Term("id",vo.getId().toString()),doc);
    }

    public void deleteQuestionIndex(Long quesId) {
        NRTLuceneFacade.instance().deleteIndex(new Term("id", quesId.toString()));
    }

    public void optimize() {
        NRTLuceneFacade.instance().optimize();
    }

}
