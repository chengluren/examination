package org.dreamer.examination.business;

import org.dreamer.examination.entity.Question;
import org.dreamer.examination.entity.Types;
import org.dreamer.examination.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class DefaultRandomStrategy implements RandomStrategy {

    private static int DEFAULT_MULTIPLY = 10;

    private QuestionService questionService;
    private int multiply;

    public DefaultRandomStrategy(QuestionService questionService) {
        this(questionService, DEFAULT_MULTIPLY);
    }

    public DefaultRandomStrategy(QuestionService questionService, int multiply) {
        this.questionService = questionService;
        this.multiply = multiply;
    }

    @Override
    public Page<Question> sequenceLoad(long storeId, String quesType, Pageable p) {
        return questionService.getQuestions(storeId,
                Types.QuestionType.getTypeFromShortName(quesType), p);
    }

    @Override
    public Page<Question> sequenceLoad(long storeId, String quesType, int pageNum, int pageSize) {
        return questionService.getQuestions(storeId,
                Types.QuestionType.getTypeFromShortName(quesType), pageNum, pageSize);
    }

    @Override
    public List<Question> randomLoad(long storeId, String quesType, int required) {
        Types.QuestionType type = Types.QuestionType.getTypeFromShortName(quesType);
        long totalCount = questionService.countOfTypeQuestion(storeId, type);
        long multipliedCount = multiply * required;
        if (totalCount <= required) {
            Pageable p = new PageRequest(0, required);
            return questionService.getQuestions(storeId, type, p).getContent();
        } else if (totalCount > required && totalCount <= multipliedCount) {
            Pageable p = new PageRequest(0, Long.valueOf(totalCount).intValue());
            return doRandom(storeId, type, p, required);
        } else if (totalCount > multipliedCount) {
            Long totalPage = totalCount % multipliedCount == 0 ? (totalCount / multipliedCount) : ((totalCount / multipliedCount) + 1);
            Random pr = new Random();
            int pi = pr.nextInt(totalPage.intValue());
            if ((totalCount-pi*multipliedCount)<multipliedCount){
                pi = pi-1;
            }
            Pageable p = new PageRequest(pi,Long.valueOf(multipliedCount).intValue());
            return doRandom(storeId,type,p,required);
        }
        return null;
    }

    private List<Question> doRandom(long storeId, Types.QuestionType type, Pageable p, int required) {
        List<Long> ids = questionService.getIds(storeId, type, p);
        int idSize = ids.size();
        Set<Long> randomIds = new HashSet<>();
        Random r = new Random();
        while (randomIds.size() < required) {
            int i = r.nextInt(idSize);
            randomIds.add(ids.get(i));
        }
        return loadQuestions(randomIds);
    }

    private List<Question> loadQuestions(Set<Long> ids) {
        List<Question> result = new ArrayList<>();
        if (ids != null) {
            for (Long id : ids) {
                result.add(questionService.getQuestion(id));
            }
        }
        return result;
    }
}
