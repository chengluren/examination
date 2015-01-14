package org.dreamer.examination.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.dreamer.examination.entity.ExaminationViewPassVO;
import org.dreamer.examination.service.ExaminationViewService;
import org.dreamer.examination.sql.builder.SqlQueryModelBuilder;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.dreamer.examination.sql.model.SqlSortItem;
import org.dreamer.examination.sql.model.SqlSortType;
import org.dreamer.examination.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class CertificateCreator {

    private ExaminationViewService examService;
    private ExaminationViewPassVO param;

    public CertificateCreator(ExaminationViewService examService, ExaminationViewPassVO param) {
        this.examService = examService;
        this.param = param;
    }

    public XWPFDocument create() {
        XWPFDocument doc = new XWPFDocument();

        List<SqlQueryItem> filter = buildFilter();
        Pageable page = new PageRequest(0, Constants.DEFAULT_PAGE_SIZE);
//        List<SqlSortItem> sorts = new ArrayList<>();
//        sorts.add(new SqlSortItem("className", SqlSortType.ASC));
//        sorts.add(new SqlSortItem("stuNo", SqlSortType.ASC));
        Page<ExaminationViewPassVO> data = examService.getExaminationPassByFilter(filter, null, page);
        doCreate(doc, data.getContent(), false);
        int totalPage = data.getTotalPages();
        for (int p = 1; p < totalPage; p++) {
            page = new PageRequest(p, Constants.DEFAULT_PAGE_SIZE);
            data = examService.getExaminationPassByFilter(filter, null, page);
            doCreate(doc, data.getContent(), true);
        }
        return doc;
    }

    public XWPFDocument create(List<ExaminationViewPassVO> data) {
        XWPFDocument doc = new XWPFDocument();
        doCreate(doc, data, true);
        return doc;
    }

    private List<SqlQueryItem> buildFilter() {
        Map<String, Object> map = new HashMap<>();
        if (param.getScheduleid() != null) {
            map.put("scheduleid", param.getScheduleid());
        }
        if (StringUtils.isNotEmpty(param.getMajorName())) {
            map.put("majorName-li", param.getMajorName());
        }
        if (StringUtils.isNotEmpty(param.getClassName())) {
            map.put("className-li", param.getClassName());
        }
        if (StringUtils.isNotEmpty(param.getCollege())) {
            map.put("college-li", param.getCollege());
        }
        if (StringUtils.isNotEmpty(param.getStuNo())) {
            map.put("stuNo-li", param.getStuNo());
        }
        if (param.getCollegeId() != null && param.getCollegeId() != -1) {
            map.put("collegeId", param.getCollegeId());
        }
        if (param.getPromise() != null && param.getPromise() != -1) {
            map.put("promise", param.getPromise());
        }
        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);
        return itemList;
    }

    private void doCreate(XWPFDocument doc, List<ExaminationViewPassVO> data, boolean firstPageBreak) {
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (i == 0 && firstPageBreak) {
                    doCreate(doc, data.get(i), firstPageBreak);
                } else {
                    doCreate(doc, data.get(i), true);
                }
            }
        }
    }

    private void doCreate(XWPFDocument doc, ExaminationViewPassVO vo, boolean pageBreak) {
        XWPFParagraph title = doc.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        if (pageBreak) {
            title.setPageBreak(true);
        }

        XWPFRun r1 = title.createRun();
        r1.setText("安全知识培训考试");
        r1.addCarriageReturn();
        r1.setText("合格证书");
        r1.setBold(true);
        r1.setFontFamily("SimHei");
        r1.setFontSize(42);

        XWPFParagraph content = doc.createParagraph();
        content.setIndentationFirstLine(600);

        String stuName = vo.getStuName();
        Date cTime = vo.getExamCommitTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(cTime);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        XWPFRun r2 = content.createRun();

        r2.setText(stuName + " 同学于 " + year + " 年" + month + " 月参加了北京交通大学安全知识培训" +
                "及通识安全/专业安全知识考试，考试合格，特发此证。");
        r2.setFontFamily("SimHei");
        r2.setFontSize(16);

        XWPFParagraph name = doc.createParagraph();
        name.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun r3 = name.createRun();
        r3.setFontFamily("SimHei");
        r3.setFontSize(16);
        r3.setText("北京交通大学");


        XWPFParagraph promise = doc.createParagraph();
        XWPFRun r4 = promise.createRun();
        r4.setFontSize(16);
        r4.setText("承诺书");
        r4.addCarriageReturn();
        r4.setText("本人承诺在今后的学习活动中做到以下几点：");
        r4.addCarriageReturn();
        r4.setText("1、遵守学校各种安全管理规章制度及规定，维护校园安全,建设和谐校园。");
        r4.addCarriageReturn();
        r4.setText("2、认真执行实验室安全管理的各项规章制度和操作规程，为自己的安全负责。");
        r4.addCarriageReturn();
        r4.setText("3、积极参加学校举办的各种培训和安全学习活动，不断提高安全生产技能，增强事故预防和应急处理能力。" +
                "我已接受实验室安全教育，并掌握各种安全知识，愿意认真履行，并接受老师和同学的监督。");
        r4.addCarriageReturn();
        r4.addCarriageReturn();
        //r4.setTextPosition(200);

        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r5 = p.createRun();
        r5.setFontSize(16);
        r5.setText("承诺人签字：");
        r5.addCarriageReturn();
        r5.setText("指导老师签字：");
        r5.addCarriageReturn();
        r5.setText("    年   月   日");
    }

//    public static void main(String[] args) {
//        CertificateCreator creator = new CertificateCreator(null, null);
//        List<ExaminationViewPassVO> data = new ArrayList<>();
//        Date date = new Date();
//        for (int i = 0; i < 400; i++) {
//            ExaminationViewPassVO vo = new ExaminationViewPassVO();
//            vo.setClassName("机制026");
//            vo.setCollege("机械学院");
//            vo.setMajorName("机械设计制造");
//            vo.setFinalScore(96F);
//            vo.setStuName("程亮");
//            vo.setStuNo("0001" + i);
//            vo.setExamCommitTime(date);
//            data.add(vo);
//        }
//        try (FileOutputStream fos = new FileOutputStream(new File("D:/test.docx"));
//             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
//            XWPFDocument doc = creator.create(data);
//            doc.write(bos);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
