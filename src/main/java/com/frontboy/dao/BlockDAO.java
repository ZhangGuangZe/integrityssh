package com.frontboy.dao;

import com.frontboy.model.TBlock;

import java.util.List;

/**
 * 黑名单业务接口
 */
public interface BlockDAO {

    /**
     * 根据id查询黑名单
     * @param id
     * @return
     */
    public TBlock findById(int id);

    /**
     * 根据黑名单名称查询
     * @param name
     * @return
     */
    public TBlock findByName(String name);

    /**
     * 查询黑名单列表
     * @param wherecondition
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<TBlock> findAllBlock(String wherecondition, int currentPage, int pageSize);

    /**
     * 黑名单总数量
     * @param wherecondition
     * @return
     */
    public int findAmount(String wherecondition);

    /**
     * 创建一条黑名单
     * @param block
     * @return
     */
    public int create(TBlock block);

    /**
     * 删除一条黑名单
     * @param id
     * @return
     */
    public boolean delete(int id);

    /**
     * 更新一条黑名单
     * @param block
     * @return
     */
    public boolean update(TBlock block);
}
