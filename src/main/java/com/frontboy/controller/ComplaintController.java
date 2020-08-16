package com.frontboy.controller;

import com.frontboy.dao.ComplaintDAO;
import com.frontboy.factory.DAOFactory;
import com.frontboy.model.TComplaint;
import com.frontboy.util.DateUtil;
import com.frontboy.util.Expression;
import com.frontboy.util.ResponseJSON;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 投诉控制类
 */

@RestController
@RequestMapping(value = "/complaint")
public class ComplaintController extends BaseController{
    ComplaintDAO complaintDAO;
    @ModelAttribute
    public void init(){
        complaintDAO = DAOFactory.getComplaintDAO();
    }

    /**
     * 获取所有投诉信息
     * @param limit 每页条目
     * @param page 总页数
     * @param status 根据状态查询
     * @param time 根据时间查询
     */
    @GetMapping(value="/getAllList")
    public void getAllList(
            int limit,
            int page,
            String status,
            String time) {

        //根据传入的参数构建查询条件
        Expression exp = new Expression();
        if(status != null && !status.equals(""))
            exp.orLike("status", status, String.class);
        if(time != null && !time.equals(""))
            exp.orLike("time", time, String.class);
        String wherecondition = exp.toString();

        List list = complaintDAO.findAllList(wherecondition, page, limit);
        int size = complaintDAO.findAmount(wherecondition);

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

    @GetMapping(value="/getUserComplaints")
    public void getUserComplaints(String username){
        List list = complaintDAO.findUserComplaints(username);
        if (list != null) {
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "用户投诉信息获取成功";
            rj.data = list;
        } else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "用户投诉信息获取失败";
        }
        closeout();
    }
    /**
     * 添加投诉信息
     * @param complaint 投诉对象
     */
    @PostMapping(value = "/add")
    public void create(TComplaint complaint) {
        TComplaint tComplaint = new TComplaint();
        tComplaint.setUsername(complaint.getUsername());
        tComplaint.setObject(complaint.getObject());
        tComplaint.setTime(DateUtil.tranDate2datetime(new Date()));
        tComplaint.setReason(complaint.getReason());
        tComplaint.setStatus("未处理");
        tComplaint.setOperator(complaint.getOperator());
        tComplaint.setResult(complaint.getResult());
        if(complaintDAO.create(tComplaint) > 0){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "投诉添加成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "投诉添加失败 ";
        }
        closeout();
    }

    /**
     * 删除投诉信息
     * @param id 投诉编号
     */
    @DeleteMapping(value="/del")
    public void delete(int id){
        if(complaintDAO.delete(id)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "投诉删除成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "投诉删除失败 ";
        }
        closeout();
    }

    /**
     * 修改投诉信息
     * @param complaint 投诉对象
     */
    @PutMapping(value="/update")
    public void update(TComplaint complaint) {
        if(complaintDAO.update(complaint)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "投诉修改成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "投诉修改失败";
        }
        closeout();
    }
}
