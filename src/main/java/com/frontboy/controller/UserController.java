package com.frontboy.controller;

import com.frontboy.dao.UserDAO;
import com.frontboy.factory.DAOFactory;
import com.frontboy.model.TUser;
import com.frontboy.model.VUserRole;
import com.frontboy.util.Expression;
import com.frontboy.util.ResponseJSON;
import com.frontboy.util.TokenProccessor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 用户控制类
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{
    UserDAO userDAO;
    @ModelAttribute
    public void init(){
        userDAO = DAOFactory.getUserDAO();
    }

    /**
     * 用户登录接口
     * @param username
     * @param password
     * @throws IOException
     */
    @PostMapping(value = "/login")
    public void login(String username, String password){
        VUserRole user = userDAO.login(username, password);

        if (user != null) {
            TokenProccessor to = new TokenProccessor();
            String token = to.makeToken();
            user.setToken(token);
            userDAO.updateToken(username,token);

            rj.code = ResponseJSON.FLAG_SUCC;
            rj.token=token;
            session.setAttribute("user", user);
        } else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "登录失败,账号或密码不能为空";
        }
        closeout();
    }

    /**
     * 获取信息接口
     * @param token
     */
    @GetMapping(value = "/info")
    public void getInfo(String token) {
        VUserRole user = userDAO.getToken(token);
        TUser tu = new TUser();
        tu.setUsername(user.getUsername());
        tu.setRoleid(user.getRoleid());
        tu.setPassword(user.getPassword());
        tu.setPhone(user.getPhone());
        tu.setRealname(user.getRealname());
        tu.setGender(user.getGender());
        tu.setLanguage(user.getLanguage());
        tu.setLevel(user.getLevel());
        tu.setAvatar(user.getAvatar());
        tu.setEnabled(user.getEnabled());
        tu.setToken(user.getToken());
        String[] naxx = new String[1];
        naxx[0] = user.getName();
        tu.setName(naxx);
        if (user != null) {
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "token有效，获取用户对象成功";
            rj.data=tu;
        } else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "token无效，获取用户对象成功失败";
        }
        closeout();
    }

    /**
     * 退出登录接口
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping(value="/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession();
        session.removeAttribute("user");

        if(session.getAttribute("user")==null){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "退出登录成功 ";
        }else{

            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "退出登录失败 ";
        }
        closeout();
    }

    /**
     * 获取所有用户接口
     * @param limit
     * @param page
     * @param username
     * @param roleid
     */
    @GetMapping(value="/getAllUsers")
    public void getAllUsers(
            int limit,//每页条目
            int page,//总页数
            String username,
            Integer roleid,
            String realname,
            String gender,
            String level){

        //根据传入的参数构建查询条件
        Expression exp = new Expression();
        if(username != null && !username.equals(""))
            exp.orLike("username", username, String.class);
        if(roleid != null && roleid !=0)
            exp.andEqu("roleid", roleid, Integer.class);
        if(realname != null && !realname.equals(""))
            exp.orLike("realname", realname, String.class);
        if(level != null && !level.equals(""))
            exp.orLike("level", level, String.class);
        if(gender != null && !gender.equals(""))
            exp.orLike("gender", gender, String.class);
        String wherecondition = exp.toString();


        List list = userDAO.findAllUser(wherecondition, page, limit);
        int size = userDAO.findAmount(wherecondition);
        if(list!=null){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.count = size;
            rj.message = "查询成功，共查出" + rj.count + "条记录";
            rj.data = list;
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "查询失败";
        }
        closeout();
    }

    /**
     * 添加用户接口
     * @param tUser
     */
    @PostMapping(value = "/add")
    public void create(TUser tUser){
        TUser users = userDAO.findById(tUser.getUsername());
        if(users == null){
            TUser user = new TUser();
            user.setUsername(tUser.getUsername());
            user.setPassword("000000");
            user.setGender(tUser.getGender());
            user.setAvatar(tUser.getAvatar());
            user.setRealname(tUser.getRealname());
            user.setPhone(tUser.getPhone());
            user.setLevel(tUser.getLevel());
            user.setLanguage(tUser.getLanguage());
            user.setEnabled((long) 1);
            user.setRoleid(tUser.getRoleid());
            if(userDAO.create(user) > 0){
                rj.code = ResponseJSON.FLAG_SUCC;
                rj.message = "用户添加成功";
            }else{
                rj.code = ResponseJSON.FLAG_FAIL;
                rj.message = "用户添加失败 ";
            }
        }else {
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "用户已存在 ";
        }
        closeout();
    }

    /**
     * 修改用户信息
     * @param user
     */
    @PutMapping(value="/update")
    public void update(TUser user){
        if(userDAO.update(user)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "用户信息修改成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "用户信息修改失败";
        }
        closeout();
    }

    /**
     * 修改个人中心
     * @param user
     */
    @PutMapping(value="/center")
    public void editUser(TUser user){
        if(userDAO.editUser(user)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "修改成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "修改失败";
        }
        closeout();
    }

    /**
     * 修改密码
     * @param username
     * @param oldPass
     * @param newPass
     * @param confirmPass
     */
    @PutMapping(value = "/updatePass")
    public void updatePass(
            String username,
            String oldPass,
            String newPass,
            String confirmPass){

        TUser user = userDAO.findById(username);

        if(!user.getPassword().equals(oldPass)){
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "密码修改失败，旧密码不正确";
        }else {
            userDAO.updatePass("admin", newPass);
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "密码修改成功，请重新输入";
        }
        closeout();
    }

    /**
     * 删除用户
     * @param username
     */
    @DeleteMapping(value="/del")
    public void delete(String username){
        if(userDAO.delete(username)){
            rj.code = ResponseJSON.FLAG_SUCC;
            rj.message = "用户删除成功";
        }else{
            rj.code = ResponseJSON.FLAG_FAIL;
            rj.message = "用户删除失败 ";
        }
        closeout();
    }
}
