package com.frontboy.dao;

import com.frontboy.model.TTravelagency;

import java.util.List;

/**
 * 旅行社业务接口
 */
public interface AgencyDAO {
    /**
     * 根据旅行社id查询
     * @param id
     * @return
     */
    public TTravelagency findById(long id);

    /**
     * 根据旅行社名称查询
     * @param name
     * @return
     */
    public TTravelagency findByName(String name);

    /**
     * 查询所有旅行社
     * @return
     */
    public List<TTravelagency> findAllAgency(String wherecondition, int currentPage, int pageSize);

    /**
     * 获取旅行社数量
     * @return
     */
    public int findAmount(String wherecondition);

    /**
     * 添加旅行社
     * @param agency
     * @return
     */
    public int create(TTravelagency agency);

    /**
     * 删除旅行社
     * @param id
     * @return
     */
    public boolean delete(long id);

    /**
     * 更新角旅行社
     * @param agency
     * @return
     */
    public boolean update(TTravelagency agency);
}
