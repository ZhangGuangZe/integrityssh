package com.frontboy.dao;

import com.frontboy.model.TComplaint;
import com.frontboy.model.VComplaint;

import java.util.List;

/**
 * 投诉业务接口
 */
public interface ComplaintDAO {
    /**
     * 根据id查询投诉信息
     * @param id
     * @return
     */
    public TComplaint findById(int id);

    /**
     * 获取所有投诉信息
     * @param wherecondition
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<TComplaint> findAllList(String wherecondition, int currentPage, int pageSize);

    /**
     * 获取当前用户所有投诉信息
     * @param username 用户名
     * @return
     */
    public List<VComplaint> findUserComplaints(String username);

    /**
     * 获取投诉总量
     * @param wherecondition
     * @return
     */
    public int findAmount(String wherecondition);

    /**
     * 创建投诉
     * @param complaint
     * @return
     */
    public int create(TComplaint complaint);

    /**
     * 删除投诉
     * @param id
     * @return
     */
    public boolean delete(int id);

    /**
     * 修改投诉
     * @param complaint
     * @return
     */
    public boolean update(TComplaint complaint);
}
