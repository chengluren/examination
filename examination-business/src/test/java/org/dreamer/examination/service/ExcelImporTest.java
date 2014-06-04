package org.dreamer.examination.service;

import org.dreamer.examination.entity.QuestionStore;
import org.dreamer.examination.importer.DefaultExcelImporter;
import org.dreamer.examination.importer.Importer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ExcelImporTest {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionStoreService storeService;

//    @Test
//    public void testImport() {
////        QuestionStore generic = new QuestionStore("通识题库","通识类题库");
////        generic.setSno(1);
////        QuestionStore special = new QuestionStore("专业题库","专业类题库");
////        special.setSno(2);
////        storeService.addQuestionStore(generic);
////        storeService.addQuestionStore(special);
////        Long gid = generic.getId();
////        Long sid = special.getId();
//
//        String gdir = "E:/题库/通识";
//        String sdir = "E:/题库/专业";
////        doImport(gdir,gid);
////        doImport(sdir,sid);
//        doImport(gdir);
//        doImport(sdir);
//    }

    @Test
    public void testNewImport(){
        Importer importer = new DefaultExcelImporter(questionService);
//        File excel = new File("E:\\题库\\通识\\通识-化学安全-3.xlsx");
//        importer.doImport(excel,1);
        String generic = "E:\\题库\\通识";
        String special = "E:\\题库\\专业";
        doImport(generic);
        doImport(special);
    }

    private void doImport(String rootDir){
        File dir = new File(rootDir);
        File[] files = dir.listFiles();
        Importer importer = new DefaultExcelImporter(questionService);
        for (File file:files){
            System.out.println("================="+file.getName()+"==========================");
            String fileName = file.getName();
            int dotIndex = fileName.indexOf(".");
            fileName = fileName.substring(0,dotIndex);
            String[] fileMeta = fileName.split("-");
            String store = fileMeta[0]+"-"+fileMeta[1];
            QuestionStore s = new QuestionStore(store,store);
            s.setSno(Integer.valueOf(fileMeta[2]));
            //s.setParentId(parentStoreId);
            if(fileMeta[0].equals("通识")){
                s.setGeneric(true);
            }

            storeService.addQuestionStore(s);
            Long sid = s.getId();

            importer.doImport(file,sid);
        }
    }

    private void doImport(String rootDir,long parentStoreId){
        File dir = new File(rootDir);
        File[] files = dir.listFiles();
        Importer importer = new DefaultExcelImporter(questionService);
        for (File file:files){
            System.out.println("================="+file.getName()+"==========================");
            String fileName = file.getName();
            int dotIndex = fileName.indexOf(".");
            fileName = fileName.substring(0,dotIndex);
            String[] fileMeta = fileName.split("-");
            String store = fileMeta[0]+"-"+fileMeta[1];
            QuestionStore s = new QuestionStore(store,store);
            s.setSno(Integer.valueOf(fileMeta[2]));
            s.setParentId(parentStoreId);

            storeService.addQuestionStore(s);
            Long sid = s.getId();

            importer.doImport(file,sid);
        }
    }
}
