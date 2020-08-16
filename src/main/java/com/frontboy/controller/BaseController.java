package com.frontboy.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.frontboy.util.ResponseJSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;

@Controller
public class BaseController implements HttpSessionListener{

    public static final Map<String, String> USER_SESSIONID = new HashMap<String, String>();//key为userid,value为session.getid()
    public static final Map<String, String> SESSIONID_USER = new HashMap<String, String>();//key为session.getid(),value为userid
    static boolean loginstate = false;

    protected PrintWriter out;
    protected ResponseJSON rj=null;
    protected HttpSession session;

    /**
     * 初始调用（处理器方法执行之前执行）
     * @param response
     * @param request
     */
    @ModelAttribute
    public void init(HttpServletResponse response,HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        rj = new ResponseJSON();
        try {
            out = response.getWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,Access-Control-Allow-Headers");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed","1");
        session = request.getSession();



    }

    /**
     * 关闭out（PrintWriter）
     */
    public void closeout(){
        out.write(JSON.toJSONString(rj));
        out.flush();
        out.close();
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        String sessionId = se.getSession().getId();
        System.out.println("*************启动");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        System.out.println("*************销毁");
        String sessionId = se.getSession().getId();
        //根据sessionid 获取 userid
        String userid = SESSIONID_USER.get(sessionId);
        //根据userid移除USER_SESSIONID中的信息
        USER_SESSIONID.remove(userid);
        //根据sessionid移除SESSIONID_USER中的信息
        SESSIONID_USER.remove(sessionId);
    }
}

