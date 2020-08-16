package com.frontboy.controller;

import com.frontboy.dao.RedBlockDAO;
import com.frontboy.factory.DAOFactory;
import com.frontboy.model.TRedBlock;
import com.frontboy.util.Expression;
import com.frontboy.util.ResponseJSON;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 红黑榜控制类
 */

@RestController
@RequestMapping(value = "/redBlock")
public class RedBlockController extends BaseController{
    RedBlockDAO redBlockDAO;
    @ModelAttribute
    public void init(){
        redBlockDAO = DAOFactory.getRedBlockDAO();
    }

    /**
     * 获取红黑榜列表
     * @param limit 每页条目
     * @param page 总页数
     * @param person 人物
     * @param type 类型
     * @param time 时间
     */
    @GetMapping(value="/getAllList")
    public void getAllRedBlock(
            int limit,
            int page,
            String person,
            String type,
            String time) {

        //根据传入的参数构建查询条件
        Expression exp = new Expression();
        if(person != null && !person.equals(""))
            exp.orLike("person", person, String.class);
        if(type != null && !type.equals(""))
            exp.orLike("type", type, String.class);
        if(time != null && !time.equals(""))
            exp.orLike("time", time, String.class);
        String wherecondition = exp.toString();

        List list = redBlockDAO.findAllList(wherecondition, page, limit);
        int size = redBlockDAO.findAmount(wherecondition);

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

    @GetMapping(value = "getTimeList")
    public void getTimeList(String time){
        List list = redBlockDAO.findTimeList(time);
        if (list != null) {
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "查询成功";
            rj.data = list;
        } else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "查询失败";
        }
        closeout();
    }

    /**
     * 添加红黑榜信息
     * @param rb 红黑榜对象
     */
    @PostMapping(value = "/add")
    public void createRedBlock(TRedBlock rb) {
        TRedBlock tRedBlock = new TRedBlock();
        tRedBlock.setType(rb.getType());
        tRedBlock.setTime(rb.getTime());
        tRedBlock.setPerson(rb.getReason());
        tRedBlock.setReason(rb.getReason());
        if(redBlockDAO.create(rb) > 0){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "红黑榜添加成功一条记录";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "红黑榜添加记录失败 ";
        }
        closeout();
    }

    /**
     * 删除红黑榜信息
     * @param id 红黑榜编号
     */
    @DeleteMapping(value="/del")
    public void deleteRedBlock(int id){
        if(redBlockDAO.delete(id)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "红黑榜记录删除成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "红黑榜记录删除失败 ";
        }
        closeout();
    }

    /**
     * 更新红黑榜信息
     * @param redBlock 红黑榜对象
     */
    @PutMapping(value="/update")
    public void updateRedBlock(TRedBlock redBlock) {
        if(redBlockDAO.update(redBlock)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "红黑榜记录修改成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "红黑榜记录修改失败";
        }
        closeout();
    }
}
