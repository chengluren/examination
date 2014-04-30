package org.dreamer.examination.service;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.dreamer.examination.search.LuceneFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionIndexAndSearchTest {

    private LuceneFacade facade = LuceneFacade.instance();
    @Autowired
    private QuestionService questionService;

    @Test
    public void testIndexAndSearch() {
        IndexWriter writer = facade.getIndexWriter();

        Page<Object[]> baseInfo = questionService.getQuestionBaseInfo(new PageRequest(0, 20));
        int tp = baseInfo.getTotalPages();
        for (int i = 0; (i < tp && tp < 30); i++) {
            index(writer, baseInfo.getContent());
            baseInfo = questionService.getQuestionBaseInfo(new PageRequest(i,20));
        }
        try {
            writer.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        IndexReader reader = facade.getIndexReader();
        search(reader,"罐装化学品");

        for (int i = 31; (i < tp && tp < 60); i++) {
            index(writer, baseInfo.getContent());
            baseInfo = questionService.getQuestionBaseInfo(new PageRequest(i,20));
        }
        IndexReader newReader = null;
        try {
            newReader = DirectoryReader.openIfChanged((DirectoryReader)reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        search(newReader,"载客的火车、船舶、飞机机舱");
    }

    private void search(IndexReader reader,String queryText){
        IndexSearcher searcher = new IndexSearcher(reader);
        QueryParser qp = facade.newQueryParser("stem");
        try {
            Query q =qp.parse(queryText);
            TopDocs topDocses = searcher.search(q, 20);
            ScoreDoc[]  docs = topDocses.scoreDocs;
            System.out.println("本次命中数据:"+docs.length);
            for (ScoreDoc doc : docs){
                int id = doc.doc;
                Document d = searcher.doc(id);
                System.out.println("--------------------");
                System.out.print(d.get("id"));
                System.out.print("--");
                System.out.print(d.get("answer"));
                System.out.print("--");
                System.out.print(d.get("stem"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void index(IndexWriter writer, List<Object[]> questions) {
        List<Document> docs = new ArrayList<>();
        for (Object[] q : questions) {
            Document doc = createDocument(String.valueOf(q[0]), q[3].toString(), q[1].toString());
            docs.add(doc);
        }
        try {
            writer.addDocuments(docs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Document createDocument(String id, String answer, String stem) {
        Document doc = new Document();
        //只存储不索引
        StoredField idf = new StoredField("id", id);
        StoredField answerf = new StoredField("anser", answer);
        TextField stemf = new TextField("stem", stem, Field.Store.YES);
        doc.add(idf);
        doc.add(answerf);
        doc.add(stemf);
        return doc;
    }

}
