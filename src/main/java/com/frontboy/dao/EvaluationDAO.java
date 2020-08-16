package com.frontboy.dao;

import com.frontboy.model.TEvaluation;
import com.frontboy.model.VEvaluation;

import java.util.List;

/**
 * 评价业务接口
 */
public interface EvaluationDAO {
    /**
     * 根据id查询评价
     * @param id
     * @return
     */
    public TEvaluation findById(int id);

    /**
     * 获取所有评价信息
     * @param wherecondition
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<TEvaluation> findAllList(String wherecondition, int currentPage, int pageSize);

    /**
     * 获取评价总量
     * @param wherecondition
     * @return
     */
    public int findAmount(String wherecondition);

    /**
     * 创建投诉
     * @param te
     * @return
     */
    public int create(TEvaluation te);

    /**
     * 删除投诉
     * @param id
     * @return
     */
    public boolean delete(int id);

    /**
     * 修改投诉
     * @param te
     * @return
     */
    public boolean update(TEvaluation te);
}
