package org.dreamer.examination.repository;

import org.dreamer.examination.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface PaperDao extends JpaRepository<Paper, Long> {

    public List<Paper> findByTemplateId(Long templateId);

    public Page<Paper> findByTemplateId(Long templateId, Pageable pageable);

    @Query(value = "select count(p.id) from Paper p")
    public Long getPaperCount();

    @Modifying
    @Query(value = "delete from Paper where id in " +
            "(select e.paper.id from Examination e where e.schedule.id =?1 ) ")
    public void deleteSchedulePapers(Long scheduleId);
}
