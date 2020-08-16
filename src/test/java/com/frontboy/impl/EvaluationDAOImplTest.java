package com.frontboy.impl;

import com.frontboy.dao.EvaluationDAO;
import com.frontboy.model.TEvaluation;
import com.frontboy.model.VEvaluation;
import com.frontboy.util.DateUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class EvaluationDAOImplTest extends UserDAOImpl {

    EvaluationDAO evaluationDAO = null;
    @Before
    public void init(){
        evaluationDAO = new EvaluationDAOImpl();
    }

    @Test
    public void testFindById() {
        TEvaluation evaluation = evaluationDAO.findById(1);
        System.out.println(evaluation.getContent() + " " + evaluation.getStar() + " " + evaluation.getTime());
    }

    @Test
    public void findAllList() {
        List<TEvaluation> evaluations = evaluationDAO.findAllList("", 1, 10);
        for (TEvaluation list: evaluations){
            System.out.println(list.getContent() + " " + list.getStar() + " " + list.getTime());
        }
    }

    @Test
    public void testFindAmount() {
        System.out.println(evaluationDAO.findAmount(""));
    }

    @Test
    public void testCreate() {
        TEvaluation evaluation = new TEvaluation();
        evaluation.setContent("XX都有挺客气的，下次还要叫他当我的导游");
        evaluation.setTime(DateUtil.tranDate2datetime(new Date()));
        evaluation.setStar(5);
        System.out.println(evaluationDAO.create(evaluation));
    }

    @Test
    public void testDelete() {
        System.out.println(evaluationDAO.delete(2));
    }

    @Test
    public void testUpdate() {
        TEvaluation evaluation = evaluationDAO.findById(1);
        evaluation.setContent("说话凶巴巴的");
        evaluation.setTime("2020-3-28");
        evaluation.setStar(1);
        System.out.println(evaluationDAO.update(evaluation));
    }
}