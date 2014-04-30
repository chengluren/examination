package org.dreamer.examination.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.dreamer.examination.utils.SpringUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class LuceneFacade {
    private static Version LUCENE_VERSION = Version.LUCENE_47;

    private String P_INDEX_PATH = "index.path";
    private String P_RAM_BUF_SIZE = "index.ramBufferSize";
    private Analyzer analyzer;
    private String indexPath;
    private int ramBufferSize;

    private LuceneFacade() {
        analyzer = new IKAnalyzer(true);
//        indexPath = SpringUtils.getConfigValue(P_INDEX_PATH);
//        ramBufferSize = Integer.valueOf(SpringUtils.getConfigValue(P_RAM_BUF_SIZE, "64"));
        indexPath ="D:/exam/index";
        ramBufferSize = 1;
    }

    private static class LuceneFacadeHolder {
        private static LuceneFacade instance = new LuceneFacade();
    }

    public static LuceneFacade instance() {
        return LuceneFacadeHolder.instance;
    }

    public IndexWriter getIndexWriter() {
        try {
            Directory directory = FSDirectory.open(new File(indexPath));
            IndexWriterConfig config = new IndexWriterConfig(LUCENE_VERSION, analyzer);
            config.setRAMBufferSizeMB(ramBufferSize);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            IndexWriter writer = new IndexWriter(directory, config);
            return writer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public IndexReader getIndexReader() {
        try {
            Directory directory = FSDirectory.open(new File(indexPath));
            IndexReader reader = DirectoryReader.open(directory);
            return reader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public QueryParser newQueryParser(String field){
         return new QueryParser(LUCENE_VERSION,field,analyzer);
    }
}
