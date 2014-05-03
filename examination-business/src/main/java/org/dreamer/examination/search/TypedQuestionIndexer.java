package org.dreamer.examination.search;

import org.dreamer.examination.entity.Question;
import org.dreamer.examination.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by lcheng on 2014/5/3.
 */
public class TypedQuestionIndexer extends AbstractQuestionIndexer {

    public TypedQuestionIndexer(QuestionService questionService,
                                Class<? extends Question> clazzType) {
          super(questionService,clazzType);
    }

    @Override
    protected Page<Object[]> loadQuestions(Pageable page) {
        return questionService.getQuestionBaseInfo(clazzType,page);
    }
}
