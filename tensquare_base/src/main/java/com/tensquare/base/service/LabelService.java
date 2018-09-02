package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import java.util.List;

/**
 * 标签业务类
 */
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有
     *
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public Label findById(String id) {
        labelDao.findById(id);
        return labelDao.findById(id).get();
    }

    /**
     * 新增标签
     *
     * @param label
     */
    public void add(Label label) {
        // 自己手动设置主键
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * 修改标签
     *
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 删除标签
     *
     * @param id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

}
