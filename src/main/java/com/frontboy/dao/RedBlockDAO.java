package com.frontboy.dao;

import com.frontboy.model.TRedBlock;

import java.util.List;

/**
 * 红黑榜业务接口
 */
public interface RedBlockDAO {

    /**
     * 根据id查询红黑榜单
     * @param id
     * @return
     */
    public TRedBlock findById(int id);

    /**
     * 查询所有红黑榜
     * @param wherecondition
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<TRedBlock> findAllList(String wherecondition, int currentPage, int pageSize);

    /**
     * 根据时间显示红黑榜信息
     * @param time
     * @return
     */
    public List<TRedBlock> findTimeList(String time);

    /**
     * 查询红黑榜总数
     * @param wherecondition
     * @return
     */
    public int findAmount(String wherecondition);

    /**
     * 创建红黑榜
     * @param rb
     * @return
     */
    public int create(TRedBlock rb);

    /**
     * 删除红黑榜
     * @param id
     * @return
     */
    public boolean delete(int id);

    /**
     * 修改红黑榜
     * @param rb
     * @return
     */
    public boolean update(TRedBlock rb);
}
