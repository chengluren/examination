package org.dreamer.examination.service;

import org.apache.lucene.document.*;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.dreamer.examination.entity.ChoiceQuestion;
import org.dreamer.examination.search.NRTLuceneFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LuceneFacadeTest {

//    private LuceneFacade facade = LuceneFacade.instance();
    private NRTLuceneFacade nrtFacade = NRTLuceneFacade.instance();

    @Autowired
    private QuestionService questionService;

    @Test
    public void testUseNRT() {
        Page<Object[]> baseInfo = questionService.getQuestionBaseInfo(ChoiceQuestion.class,new PageRequest(0, 20));
        int tp = baseInfo.getTotalPages();
        for (int i = 0; (i < tp && i < 30); i++) {
            indexUseNRT(baseInfo.getContent());
            baseInfo = questionService.getQuestionBaseInfo(ChoiceQuestion.class,new PageRequest(i, 20));
        }
        searchUseNRT("罐装化学品");
        nrtFacade.deleteIndex(new Term("id","22"));
        searchUseNRT("罐装化学品");
        for (int i = 31; (i < tp && i < 60); i++) {
            indexUseNRT(baseInfo.getContent());
            baseInfo = questionService.getQuestionBaseInfo(ChoiceQuestion.class,new PageRequest(i, 20));
        }
        searchUseNRT("火车 船舶");
        nrtFacade.optimize();
        nrtFacade.release();
    }

//    @Test
//    public void testNotUseNRT() {
//        Page<Object[]> baseInfo = questionService.getQuestionBaseInfo(new PageRequest(0, 20));
//        int tp = baseInfo.getTotalPages();
//        for (int i = 0; (i < tp && i < 30); i++) {
//            indexNotUseNRT(baseInfo.getContent());
//            baseInfo = questionService.getQuestionBaseInfo(new PageRequest(i, 20));
//        }
//        searchNotUseNRT("罐装化学品");
//        for (int i = 31; (i < tp && i < 60); i++) {
//            indexNotUseNRT(baseInfo.getContent());
//            baseInfo = questionService.getQuestionBaseInfo(new PageRequest(i, 20));
//        }
//        searchNotUseNRT("火车 船舶");
//        facade.optimize();
//        facade.release();
//    }

    private void searchUseNRT(String queryText) {
        QueryParser qp = nrtFacade.newQueryParser("stem");
        try {
            Query query = qp.parse(queryText);
            long count = nrtFacade.count(query);
            System.out.println("======================================");
            System.out.print("总查询记录数:"+count);
            List<Document> docs = nrtFacade.search(query, null, 0, 20);
            for (Document d : docs) {
                System.out.println("--------------------");
                System.out.print(d.get("id"));
                System.out.print("--");
                System.out.print(d.get("answer"));
                System.out.print("--");
                System.out.print(d.get("stem"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void indexUseNRT(List<Object[]> questions) {
        List<Document> docs = new ArrayList<>();
        for (Object[] q : questions) {
            Document doc = createDocument(String.valueOf(q[0]), q[3].toString(), q[1].toString());
            docs.add(doc);
        }
        nrtFacade.index(docs);
    }

//    private void searchNotUseNRT(String queryText) {
//        QueryParser qp = facade.newQueryParser("stem");
//        try {
//            Query query = qp.parse(queryText);
//            long count = facade.count(query);
//            System.out.println("======================================");
//            System.out.println("总查询记录数:"+count);
//            List<Document> docs = facade.search(query, null, 0, 20);
//            for (Document d : docs) {
//                System.out.println("--------------------");
//                System.out.print(d.get("id"));
//                System.out.print("--");
//                System.out.print(d.get("answer"));
//                System.out.print("--");
//                System.out.print(d.get("stem"));
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private void indexNotUseNRT(List<Object[]> questions) {
//        List<Document> docs = new ArrayList<>();
//        for (Object[] q : questions) {
//            Document doc = createDocument(String.valueOf(q[0]), q[3].toString(), q[1].toString());
//            docs.add(doc);
//        }
//        facade.index(docs);
//    }

    private Document createDocument(String id, String answer, String stem) {
        Document doc = new Document();
        //只存储不索引
        //StoredField idf = new StoredField("id", id);
        StringField idf = new StringField("id", id, Field.Store.YES);
        StoredField answerf = new StoredField("answer", answer);
        TextField stemf = new TextField("stem", stem, Field.Store.YES);
        doc.add(idf);
        doc.add(answerf);
        doc.add(stemf);
        return doc;
    }

}
