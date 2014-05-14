package org.dreamer.examination.business;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.dreamer.examination.vo.PaperQuestionVO;
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
public class DefaultTemplateRandomStrategy extends AbstractTemplateRandomStrategy implements TemplateRandomStrategy {
    private static final Logger log = LoggerFactory.getLogger(DefaultTemplateRandomStrategy.class);
    private static int DEFAULT_MULTIPLY = 10;

    private QuestionService questionService;
    private int multiply;

    public DefaultTemplateRandomStrategy(QuestionService questionService) {
        this(questionService, DEFAULT_MULTIPLY);
    }

    public DefaultTemplateRandomStrategy(QuestionService questionService, int multiply) {
        super();
        this.questionService = questionService;
        this.multiply = multiply;
    }

    public PaperQuestionVO randomGenerate (TemplateQuestionDef def){
        long storeId = def.getStoreId();
        Types.QuestionType questionType = def.getQuestionType();
        int required = def.getCount();
        int multipiedCount = required * multiply;
        long totalCount = questionService.countOfTypeQuestionNotMust(storeId, questionType);
        log.info("试题随机抽取：");
        if (totalCount<=required){
            //总数小于需要生成的题数
            log.info("---试题总数小于需要的试题数---");
            List<Long> selected =questionService.getIdsNotMust(storeId, questionType, 0, required);
            return new PaperQuestionVO(selected,def.getScorePer());
        } else if (totalCount >required && totalCount<=multipiedCount){
            log.info("---试题总数小于被乘的试题数---");
            return doGenerate(storeId,questionType,0,multipiedCount,required,def.getScorePer());
        } else if (totalCount > multipiedCount){
            log.info("---试题总数大于被乘的试题数---");
            Long page = totalCount%multipiedCount ==0 ? (totalCount/multipiedCount) : ((totalCount/multipiedCount)+1);
            Random pr = new Random();
            int pi = pr.nextInt(page.intValue());
            if ((totalCount-pi*multipiedCount)<multipiedCount){
                pi = pi-1;
            }
            return doGenerate(storeId,questionType,pi,multipiedCount,required,def.getScorePer());

        }
        return null;
    }

    private PaperQuestionVO doGenerate(long storeId,Types.QuestionType type,int pageNum,int pageSize,int required,float score){
        List<Long> space = questionService.getIdsNotMust(storeId, type, pageNum, pageSize);
        List<Long> generated = new ArrayList<>();
        Random r = new Random();
        Set<Long> exist = getGeneratedIds().get(type);
        if (exist != null) {
            Set spaceSet = Sets.newHashSet(space);
            Sets.SetView<Long> intersection = Sets.intersection(exist, spaceSet);
            int offset = space.size() - intersection.size();
            if (offset < required) {
                if (pageNum > 0) {
                    return doGenerate(storeId, type, (pageNum - 1), pageSize, required,score);
                } else {
                    log.warn("---随机试题空间与已随机出的试题交集过大，将不进行随机抽取...");
                    spaceSet.removeAll(intersection);
                    return new PaperQuestionVO(Lists.newArrayList(spaceSet),score);
                }
            } else if (offset >= required) {
                space.removeAll(exist);
            }
        }
        while (generated.size() < required) {
            long g = space.get(r.nextInt(space.size()));
            if (!generated.contains(g)){
                generated.add(g);
            }
        }
        return new PaperQuestionVO(generated,score);
    }
}
