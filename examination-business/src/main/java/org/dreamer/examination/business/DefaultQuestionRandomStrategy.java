package org.dreamer.examination.business;

import org.dreamer.examination.entity.TemplateQuestionDef;
import org.dreamer.examination.entity.Types;
import org.dreamer.examination.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 默认的试题随机生成策略
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class DefaultQuestionRandomStrategy implements RandomStrategy {
    private static final Logger log = LoggerFactory.getLogger(DefaultQuestionRandomStrategy.class);
    private static int DEFAULT_MULTIPLY = 10;

    private QuestionService questionService;
    private int multiply;

    public DefaultQuestionRandomStrategy(QuestionService questionService) {
        this(questionService, DEFAULT_MULTIPLY);
    }

    public DefaultQuestionRandomStrategy(QuestionService questionService, int multiply) {
        this.questionService = questionService;
        this.multiply = multiply;
    }

    @Override
    public List<Long> randomGenerate(TemplateQuestionDef def) {
        long storeId = def.getStoreId();
        Types.QuestionType questionType = def.getQuestionType();
        int required = def.getCount();
        int multipiedCount = required * multiply;
        long totalCount = questionService.countOfStoreTypedQues(storeId,questionType);
        log.info("试题随机抽取：");
        if (totalCount<required){
            //总数小于需要生成的题数
            log.info("---试题总数小于需要的试题数---");
            return questionService.getQuesIdsOfStoreWithType(storeId,questionType,0,required);
        } else if (totalCount >=required && totalCount<multipiedCount){
            log.info("---试题总数小于被乘的试题数---");
            return doGenerate(storeId,questionType,0,multipiedCount,required);
        } else if (totalCount > multipiedCount){
            log.info("---试题总数大于被乘的试题数---");
            Long page = totalCount%multipiedCount ==0 ? (totalCount/multipiedCount) : ((totalCount/multipiedCount)+1);
            Random pr = new Random();
            int pi = pr.nextInt(page.intValue());
            if ((totalCount-pi*multipiedCount)<multipiedCount){
                pi = pi-1;
            }
            return doGenerate(storeId,questionType,pi,multipiedCount,required);

        }
        return null;
    }

    private List<Long> doGenerate(long storeId,Types.QuestionType type,int pageNum,int pageSize,int required){
        List<Long> space = questionService.getQuesIdsOfStoreWithType(storeId,type,pageNum,pageSize);
        Set<Long> generated = new LinkedHashSet<>();
        Random r = new Random();
        while(generated.size()<required){
            generated.add(r.nextLong()+1);
        }
        return new ArrayList(generated);
    }
}
