package org.dreamer.examination.service;


import org.dreamer.examination.entity.ExaminationViewNotPassVO;
import org.dreamer.examination.entity.ExaminationViewPassRateVO;
import org.dreamer.examination.entity.ExaminationViewVO;
import org.dreamer.examination.repository.ExaminationViewDao;
import org.dreamer.examination.repository.ExaminationViewNotPassDao;
import org.dreamer.examination.repository.ExaminationViewPassRateDao;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.dreamer.examination.sql.model.SqlSortItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xwang
 * 考试管理查询
 * @version 1.0
 *          ${tags}
 */
@Service
public class ExaminationViewService {

    @Autowired
    private ExaminationViewDao examViewDao;

    @Autowired
    private ExaminationViewNotPassDao examViewNotPassDao;

    @Autowired
    private ExaminationViewPassRateDao examinationViewPassRateDao;

    /**
     * 成绩查询
     * @param _paramList
     * @param _sortList
     * @param _page
     * @return
     */
    public Page<ExaminationViewVO> getExaminationByFilter(List<SqlQueryItem> _paramList, List<SqlSortItem> _sortList, Pageable _page  ){
        Page<ExaminationViewVO> examPage =  examViewDao.queryResult(_paramList,_sortList,_page);
        return examPage;
    }

    /**
     * 未通过考试查询
     * @param _paramList
     * @param _sortList
     * @param _page
     * @return
     */
    public Page<ExaminationViewNotPassVO> getExaminationNotPassByFilter(List<SqlQueryItem> _paramList, List<SqlSortItem> _sortList, Pageable _page  ){
        Page<ExaminationViewNotPassVO> examPage =  examViewNotPassDao.queryResult(_paramList,_sortList,_page);
        return examPage;
    }

    /**
     * 考试统计，通过率查询
     * @param _paramList
     * @param _sortList
     * @param _page
     * @return
     */
    public Page<ExaminationViewPassRateVO> getExaminationPassRateByFilter(List<SqlQueryItem> _paramList, List<SqlSortItem> _sortList, Pageable _page  ){
        Page<ExaminationViewPassRateVO> examPage =  examinationViewPassRateDao.queryResult(_paramList,_sortList,_page);
        return examPage;
    }

}
