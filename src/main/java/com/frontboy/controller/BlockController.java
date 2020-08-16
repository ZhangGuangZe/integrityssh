package com.frontboy.controller;

import com.frontboy.dao.BlockDAO;
import com.frontboy.factory.DAOFactory;
import com.frontboy.model.TBlock;
import com.frontboy.util.DateUtil;
import com.frontboy.util.Expression;
import com.frontboy.util.ResponseJSON;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 黑名单控制类
 */

@RestController
@RequestMapping(value = "/block")
public class BlockController extends BaseController{
    BlockDAO blockDAO;
    @ModelAttribute
    public void init(){
        blockDAO = DAOFactory.getBlockDAO();
    }

    @GetMapping(value="/getAllBlock")
    public void getAllBlock(
            int limit,//每页条目
            int page,//总页数
            String name) {

        //根据传入的参数构建查询条件
        Expression exp = new Expression();
        if(name != null && !name.equals(""))
            exp.orLike("name", name, String.class);
        String wherecondition = exp.toString();

        List list = blockDAO.findAllBlock(wherecondition, page, limit);
        int size = blockDAO.findAmount(wherecondition);

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
    public void createBlock(TBlock block) {
        TBlock name = blockDAO.findByName(block.getName());
        if(name == null){
            TBlock tBlock = new TBlock();
            tBlock.setName(block.getName());
            tBlock.setTime(DateUtil.tranDate2datetime(new Date()));
            tBlock.setReason(block.getReason());
            if(blockDAO.create(tBlock) > 0){
                rj.code = ResponseJSON.FLAG_SUCC;
                rj.message = "黑名单添加成功";
            }else{
                rj.code = ResponseJSON.FLAG_FAIL;
                rj.message = "黑名单添加失败 ";
            }
        }else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "黑名单名称已存在 ";
        }
        closeout();
    }

    @DeleteMapping(value="/del")
    public void deleteBlock(int id){
        if(blockDAO.delete(id)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "黑名单删除成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "黑名单删除失败 ";
        }
        closeout();
    }

    @PutMapping(value="/update")
    public void updateBlock(TBlock block) {
        if(blockDAO.update(block)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "黑名单修改成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "黑名单修改失败";
        }
        closeout();
    }
}
