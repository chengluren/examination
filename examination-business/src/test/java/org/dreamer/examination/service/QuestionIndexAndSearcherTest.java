package org.dreamer.examination.service;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.dreamer.examination.search.NRTLuceneFacade;
import org.dreamer.examination.search.QuestionIndexer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

/**
 * Created by lcheng on 2014/5/3.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class QuestionIndexAndSearcherTest {

    @Autowired
    private QuestionIndexer indexAndSearcher;
    @Autowired
    private QuestionService questionService;

//    @Test
//    public void testIndexAll() {
//        indexAndSearcher.indexAll();
//        NRTLuceneFacade facade = NRTLuceneFacade.instance();
//        facade.optimize();
//        TermQuery termQuery = new TermQuery(new Term("id", "22"));
//        long count = facade.count(termQuery);
//        System.out.println("id 为22的个数为："+count);
//
//        QueryParser queryParser = facade.newQueryParser("stem");
//        try {
//            Query q = queryParser.parse("火车 船舶");
//            List<Document> result = facade.search(q,null,0,10);
//            Assert.assertNotNull(result);
//            printDoc(result.get(0));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public void testIndexByStore(){
//        indexAndSearcher.indexByStore(10L);
//        NRTLuceneFacade facade = NRTLuceneFacade.instance();
//        facade.optimize();
//
//        BooleanQuery bq = new BooleanQuery();
//        bq.add(new TermQuery(new Term("sid", "10")), BooleanClause.Occur.MUST);
//        bq.add(new TermQuery(new Term("qt", "CH")), BooleanClause.Occur.MUST);
//        QueryParser qp = facade.newQueryParser("stem");
//        try {
//            Query q = qp.parse("毒物危害");
//            bq.add(q, BooleanClause.Occur.MUST);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long count = facade.count(bq);
//
//        System.out.println("sid 为10的单选题个数为：" + count);
//        List<Document> result = facade.search(bq, null, 0, 10);
//        for (Document doc : result) {
//            printDoc(doc);
//        }
//    }

//    @Test
//    public void testCompositeQuery(){
//        NRTLuceneFacade facade = NRTLuceneFacade.instance();
//        BooleanQuery bq = new BooleanQuery();
//        bq.add(new TermQuery(new Term("sid","10")), BooleanClause.Occur.MUST);
//        bq.add(new TermQuery(new Term("qt","CH")), BooleanClause.Occur.MUST);
//        QueryParser qp = facade.newQueryParser("stem");
//        try {
//           Query q =  qp.parse("毒物危害");
//           bq.add(q, BooleanClause.Occur.MUST);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long count = facade.count(bq);
//
//        System.out.println("sid 为10的单选题个数为："+count);
//        List<Document> result = facade.search(bq,null,0,10);
//        for (Document doc :result){
//            printDoc(doc);
//        }
//    }

    @Test
    public void testSearchAll(){
        NRTLuceneFacade facade = NRTLuceneFacade.instance();
        QueryParser qp = facade.newQueryParser("stem");
        Query q = null;
        try {
            q =  qp.parse("*:*");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long count = facade.count(q);

        System.out.println("模糊查询个数为："+count);
        List<Document> result = facade.search(q,null,0,10);
        for (Document doc :result){
            printDoc(doc);
        }
    }

    private void printDoc(Document doc){
        System.out.println("==============================");
        System.out.print(doc.get("id"));
        System.out.print("-|-");
        System.out.print(doc.get("stem"));
        System.out.print("-|-");
        System.out.print(doc.get("ans"));
        System.out.print("-|-");
        System.out.print(doc.get("mc"));
        System.out.print("-|-");
        System.out.print(doc.get("sid"));
        System.out.println("-|-");
        System.out.println("==============================");
    }
}
