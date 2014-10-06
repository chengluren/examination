##### 更新日志2014-10-06
1. 添加 examinations的触发器  
 create trigger delete_exam_paper after delete on examinations for each row  
 BEGIN  
 DELETE FROM papers where id = old.paper_id;  
 END  
2. exam_templates表中添加college字段，表明该方案属于哪个院系。据此，不同院系的管理员只能  
  看见本院系的考试方案。
3. 去除答案统计、未考查询等功能。