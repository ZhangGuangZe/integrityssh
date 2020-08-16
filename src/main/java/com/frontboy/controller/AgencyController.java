package com.frontboy.controller;

import com.frontboy.dao.AgencyDAO;
import com.frontboy.factory.DAOFactory;
import com.frontboy.model.TTravelagency;
import com.frontboy.util.Expression;
import com.frontboy.util.ResponseJSON;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 旅行社控制类
 */

@RestController
@RequestMapping(value = "/agency")
public class AgencyController extends BaseController{
    AgencyDAO agencyDAO;
    @ModelAttribute
    public void init(){
        agencyDAO = DAOFactory.getAgencyDAO();
    }

    @GetMapping(value="/getAllAgency")
    public void getAllAgency(
            int limit,//每页条目
            int page,//总页数
            String name) {

        //根据传入的参数构建查询条件
        Expression exp = new Expression();
        if(name != null && !name.equals(""))
            exp.orLike("name", name, String.class);
        String wherecondition = exp.toString();

        List list = agencyDAO.findAllAgency(wherecondition, page, limit);
        int size = agencyDAO.findAmount(wherecondition);

        if (list != null) {
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.count = size;
            rj.message = "旅行社查询成功，共查出" + list.size() + "条记录";
            rj.data = list;
        } else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "旅行社查询失败";
        }
        closeout();
    }

    @PostMapping(value = "/add")
    public void createAgency(TTravelagency agency) {
        TTravelagency name = agencyDAO.findByName(agency.getName());
        if(name == null){
            TTravelagency travel = new TTravelagency();
            travel.setName(agency.getName());
            travel.setAddress(agency.getAddress());
            travel.setTel(agency.getTel());
            travel.setPerson(agency.getPerson());
            travel.setStatus(agency.getStatus());
            travel.setIntroduction(agency.getIntroduction());
            if(agencyDAO.create(travel) > 0){
                rj.code = ResponseJSON.FLAG_SUCC;
                rj.message = "旅行社添加成功";
            }else{
                rj.code = ResponseJSON.FLAG_FAIL;
                rj.message = "旅行社添加失败 ";
            }
        }else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "旅行社已存在 ";
        }
        closeout();
    }

    @DeleteMapping(value="/del")
    public void deleteAgency(Long id){
        if(agencyDAO.delete(id)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "旅行社删除成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "旅行社删除失败 ";
        }
        closeout();
    }

    @PutMapping(value="/update")
    public void updateAgency(TTravelagency agency) {
        if(agencyDAO.update(agency)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "旅行社修改成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "旅行社修改失败";
        }
        closeout();
    }
}
