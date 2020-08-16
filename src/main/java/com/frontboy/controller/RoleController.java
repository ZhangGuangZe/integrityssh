package com.frontboy.controller;

import com.alibaba.fastjson.JSON;
import com.frontboy.dao.RoleDAO;
import com.frontboy.factory.DAOFactory;
import com.frontboy.model.TRole;
import com.frontboy.util.Expression;
import com.frontboy.util.ResponseJSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 角色控制类
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    RoleDAO roleDAO;
    @ModelAttribute
    public void init(){
        roleDAO = DAOFactory.getRoleDAO();
    }

    ResponseJSON rj = new ResponseJSON();

    /**
     * 获取所有角色
     * @param limit
     * @param page
     * @param name
     * @param request
     * @param response
     * @param model
     * @throws IOException
     */
    @GetMapping(value="/getAllRoles")
    public void getAllRoles(
            int limit,//每页条目
            int page,//总页数
            String name,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) throws IOException {

        //根据传入的参数构建查询条件
        Expression exp = new Expression();
        if(name != null && !name.equals(""))
            exp.orLike("name", name, String.class);
        String wherecondition = exp.toString();

        List list = roleDAO.findAllRoles(wherecondition, page, limit);
        int size = roleDAO.findAmount(wherecondition);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        if (list != null) {
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.count = size;
            rj.message = "查询成功，共查出" + list.size() + "条记录";
            rj.data = list;
        } else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "查询失败";
        }
        out.write(JSON.toJSONString(rj));
        out.flush();
        out.close();
    }

    /**
     * 添加角色
     * @param tRole
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping(value = "/add")
    public void create(TRole tRole,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        TRole name = roleDAO.findByName(tRole.getName());
        if(name == null){
            TRole role = new TRole();
            role.setName(tRole.getName());
            role.setDescription(tRole.getDescription());
            if(roleDAO.create(role) > 0){
                rj.code = ResponseJSON.FLAG_SUCC;
                rj.message = "角色添加成功";
            }else{
                rj.code = ResponseJSON.FLAG_FAIL;
                rj.message = "角色添加失败 ";
            }
            //回传json字符串
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(rj));
            out.flush();
            out.close();
        }else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "角色已存在 ";

            //回传json字符串
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(rj));
            out.flush();
            out.close();
        }
    }

    /**
     * 删除角色
     * @param id
     * @param request
     * @param response
     * @throws IOException
     */
    @DeleteMapping(value="/del")
    public void delRole(
            Long id,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException{
        if(roleDAO.delete(id)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "角色删除成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "角色删除失败 ";
        }

        //回传json字符串
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(rj));
        out.flush();
        out.close();
    }

    @PutMapping(value="/update")
    public void updateRole(
            TRole role,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        ResponseJSON rj = new ResponseJSON();

        if(roleDAO.update(role)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "角色修改成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "角色修改失败";
        }

        //回传json字符串
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(rj));
        out.flush();
        out.close();

    }
}
