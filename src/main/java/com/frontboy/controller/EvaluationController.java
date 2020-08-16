package com.frontboy.controller;

import com.frontboy.dao.EvaluationDAO;
import com.frontboy.factory.DAOFactory;
import com.frontboy.model.TEvaluation;
import com.frontboy.util.DateUtil;
import com.frontboy.util.Expression;
import com.frontboy.util.ResponseJSON;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/evaluation")
public class EvaluationController extends BaseController{
    EvaluationDAO evaluationDAO;
    @ModelAttribute
    public void init(){
        evaluationDAO = DAOFactory.getEvaluationDAO();
    }

    @GetMapping(value="/getAllList")
    public void getAllList(
            int limit,//每页条目
            int page,//总页数
            String star,
            String time) {

        //根据传入的参数构建查询条件
        Expression exp = new Expression();
        if(star != null && !star.equals(""))
            exp.orLike("star", star, String.class);
        if(time != null && !time.equals(""))
            exp.orLike("time", time, String.class);
        String wherecondition = exp.toString();

        List list = evaluationDAO.findAllList(wherecondition, page, limit);
        int size = evaluationDAO.findAmount(wherecondition);

        if (list != null) {
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.count = size;
            rj.message = "查询成功，共查出" + list.size() + "条记录";
            rj.data = list;
        } else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "查询失败";
        }
        closeout();
    }

    @PostMapping(value = "/add")
    public void create(TEvaluation evaluation) {
        TEvaluation tEvaluation = new TEvaluation();
        tEvaluation.setPerson(evaluation.getPerson());
        tEvaluation.setContent(evaluation.getContent());
        tEvaluation.setTime(DateUtil.tranDate2datetime(new Date()));
        tEvaluation.setStar(evaluation.getStar());
        tEvaluation.setUsername(evaluation.getUsername());
        if(evaluationDAO.create(tEvaluation) > 0){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "评价添加成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "评价添加失败 ";
        }
        closeout();
    }

    @DeleteMapping(value="/del")
    public void delete(int id){
        if(evaluationDAO.delete(id)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "评价删除成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "评价删除失败 ";
        }
        closeout();
    }

    @PutMapping(value="/update")
    public void updateRedBlock(TEvaluation evaluation) {
        if(evaluationDAO.update(evaluation)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "评价修改成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "评价修改失败";
        }
        closeout();
    }
}
