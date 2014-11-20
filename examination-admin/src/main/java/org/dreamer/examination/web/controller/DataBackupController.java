package org.dreamer.examination.web.controller;

import org.dreamer.examination.entity.Result;
import org.dreamer.examination.service.ExamScheduleService;
import org.dreamer.examination.utils.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by lcheng on 2014/11/19.
 */
@Controller
@RequestMapping(value = "/data")
public class DataBackupController {

    @Autowired
    private ExamScheduleService scheduleService;

    @RequestMapping(value = "/backup")
    public ModelAndView backup() {
        ModelAndView mv = new ModelAndView("exam.backup");
        return mv;
    }

    @RequestMapping(value = "/backupData", method = RequestMethod.POST)
    @ResponseBody
    public Result backupExamData(Long scheId, String scheName) {
        String dir = SysUtils.getConfigValue("dir.databackup", "c:/mysqlData/");
        dir = dir + scheId + "_" + scheName + "/";
        Path path = Paths.get(dir);
        try {
            Files.deleteIfExists(path);
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = null;
        try {
            scheduleService.backupExamData(scheId, dir);
            result = new Result(true, "");
        } catch (Throwable e) {
            result = new Result(false, "");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/deleteExamData", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteExamData(Long scheId){
        Result result = null;
        try {
            scheduleService.deleteExamData(scheId);
            result = new Result(true, "");
        } catch (Throwable e) {
            result = new Result(false, "");
            e.printStackTrace();
        }
        return result;
    }
}
