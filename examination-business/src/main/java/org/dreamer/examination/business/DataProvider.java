package org.dreamer.examination.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface DataProvider<T> {

    /**
     * 获得下一页数据
     * @param page
     * @return
     */
     public Page<T> getNextPageData(Pageable page);
}
