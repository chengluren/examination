package org.dreamer.examination.search;

import org.apache.lucene.document.*;
import org.dreamer.examination.entity.ChoiceQuestion;
import org.dreamer.examination.entity.MultipleChoiceQuestion;
import org.dreamer.examination.entity.Question;
import org.dreamer.examination.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcheng on 2014/5/3.
 */
public abstract class AbstractQuestionIndexer implements Runnable {
    private static Logger log = LoggerFactory.getLogger(AbstractQuestionIndexer.class);

    protected static int P_SIZE = 200;

    protected QuestionService questionService;
    protected Class<? extends Question> clazzType;
    protected IndexAndSearchWrapper indexer;
    protected String quesType = "";

    public AbstractQuestionIndexer(){}

    public AbstractQuestionIndexer(QuestionService questionService,
                                   Class<? extends Question> clazzType) {
        this.questionService = questionService;
        this.clazzType = clazzType;
        this.indexer = NRTLuceneFacade.instance();
        this.quesType = clazzType.equals(ChoiceQuestion.class) ? "CH" :
                (clazzType.equals(MultipleChoiceQuestion.class) ? "MC" : "TF");
    }

    protected abstract Page<Object[]> loadQuestions(Pageable page);

    @Override
    public void run() {
        Pageable p = new PageRequest(0, P_SIZE);
        Page<Object[]> quesInfo = loadQuestions(p);
        int totalPage = quesInfo.getTotalPages();
        for (int i = 0; i < totalPage; i++) {
            log.info("正在索引<<" + clazzType.getName() + ">>, 第[" + (i + 1) + "]页...");
            List<Object[]> qlist = quesInfo.getContent();
            List<Document> docs = toDoc(qlist);
            indexer.index(docs);
            p = new PageRequest((i + 1), P_SIZE);
            quesInfo = questionService.getQuestionBaseInfo(clazzType, p);
        }
    }

    private List<Document> toDoc(List<Object[]> quesList) {
        List<Document> result = new ArrayList<>();
        if (quesList != null) {
            for (Object[] ques : quesList) {
                Document doc = new Document();
                StringField idf = new StringField("id", String.valueOf(ques[0]), Field.Store.YES);
                TextField stemf = new TextField("stem", (String) ques[1], Field.Store.YES);
                String mc = ques[2].equals(true) ? "1" : "0";
                StoredField mcf = new StoredField("mc", mc);
                StoredField answerf = new StoredField("ans", (String) ques[3]);
                StringField storeIdf = new StringField("sid", String.valueOf(ques[4]), Field.Store.YES);
                StringField quesTypef = new StringField("qt", quesType, Field.Store.NO);

                doc.add(idf);
                doc.add(stemf);
                doc.add(mcf);
                doc.add(answerf);
                doc.add(storeIdf);
                doc.add(quesTypef);
                result.add(doc);
            }
        }
        return result;
    }
}
