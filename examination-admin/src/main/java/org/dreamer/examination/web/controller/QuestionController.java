package org.dreamer.examination.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dreamer.examination.entity.*;
import org.dreamer.examination.importer.DefaultExcelImporter;
import org.dreamer.examination.importer.Importer;
import org.dreamer.examination.service.QuestionService;
import org.dreamer.examination.service.QuestionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcheng on 2014/4/22.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService quesService;
    @Autowired
    private QuestionStoreService storeService;

    @RequestMapping("/list")
    public ModelAndView questionList(Long storeId, String quesType, @PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.question-list");
        List<QuestionStore> stores = storeService.getAll();
        if (storeId == null && (stores != null && stores.size() > 0)) {
            storeId = stores.get(0).getId();
        }
        if (quesType == null) {
            quesType = "CH";
        }
        if (storeId != null) {
            Page<Question> questions = quesService.getQuestions(storeId,
                    Types.QuestionType.getTypeFromShortName(quesType), page);
            mv.addObject("questions", questions.getContent());
            mv.addObject("storeId", storeId);
            mv.addObject("quesType", quesType);
            mv.addObject("page", questions.getNumber() + 1);
            mv.addObject("totalPage", questions.getTotalPages());
        }
        mv.addObject("stores", stores);
        return mv;
    }

    @RequestMapping(value = "/mclist")
    @ResponseBody
    public Page<QuestionVO> mustChooseList(Long storeId, String quesType, @PageableDefault Pageable page){
        return quesService.getMustChooseQuestion(storeId,
                Types.QuestionType.getTypeFromShortName(quesType),page);
    }

    @RequestMapping(value = "/mcNotChoosedlist")
    @ResponseBody
    public Page<QuestionVO> mustChooseNotChoosedList(Long storeId, String quesType,Long tempId,
                                                     @PageableDefault Pageable page){
        return quesService.getMustChooseQuestionNotChoosed(storeId,
                Types.QuestionType.getTypeFromShortName(quesType),tempId,page);
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editQuestion(@PathVariable("id") Long id, Long storeId, String quesType, int page) {
        Question question = quesService.getQuestion(id);
        ModelAndView mv = new ModelAndView("exam.question-edit");
        mv.addObject("q", question);
        mv.addObject("storeId", storeId);
        mv.addObject("quesType", quesType);
        mv.addObject("page", page);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result editQuestion(String question) {
        Result result = new Result(true, "");
        ObjectMapper mapper = new ObjectMapper();
        try {
            QuestionVO vo = mapper.readValue(question, QuestionVO.class);
            Question q = quesService.getQuestion(vo.getId());
            q.setStem(vo.getStem());
            q.setAnswer(vo.getAnswer());
            q.setMustChoose(vo.isMustChoose());
            q.setImgPath(vo.getImgPath());

            if (vo.getOptions() != null && vo.getOptions().length > 0) {
                List<QuestionOption> options = Arrays.asList(vo.getOptions());
                ((ChoiceQuestion)q).setQuestionOptions(options);
            }
            quesService.addQuestion(q);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id, Long storeId, String quesType, int page, int size) {
        quesService.deleteQuestion(id);
        String redUrl = "/question/list?storeId=" + storeId + "&quesType=" + quesType + "&page=" + page + "&size=" + size;
        return "redirect:" + redUrl;
    }

    @RequestMapping(value = "/option/delete/{id}")
    @ResponseBody
    public Result deleteQuestionOption(@PathVariable("id") Long id) {
        Result result = null;
        try {
            quesService.deleteQuestionOption(id);
            result = new Result(true, "删除选项成功!");
        } catch (Exception e) {
            result = new Result(false, "删除选项失败!");
        }
        return result;
    }

    @RequestMapping(value = "/import")
    public ModelAndView importQuestions(Long storeId) {
        ModelAndView mv = new ModelAndView("exam.question-import");
        List<QuestionStore> stores = storeService.getAll();
        mv.addObject("stores", stores);
        mv.addObject("storeId", storeId);
        return mv;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importQuestions(Long storeId, MultipartFile file) {

        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            File local = new File(System.getProperty("java.io.tmpdir") + name);
            try {
                file.transferTo(local);
                Importer importer = new DefaultExcelImporter(quesService);
                importer.doImport(local, storeId);
                Files.delete(local.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/question/list?storeId=" + storeId + "&quesType=CH&page=0";
    }

}
