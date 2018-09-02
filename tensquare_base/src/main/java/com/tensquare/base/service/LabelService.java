package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Specification<Label> createSpecification(Map searchMap) {

        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                // 添加条件
                if (null != searchMap.get("labelname") && !"".equals(searchMap.get("labelname").toString().trim())) {
                    list.add(criteriaBuilder.like(root.get("labelname").as(String.class), "%" + searchMap.get("labelname") + "%"));
                }
                if (null != searchMap.get("state") && !"".equals(searchMap.get("state").toString().trim())) {
                    list.add(criteriaBuilder.equal(root.get("state").as(String.class), (String) searchMap.get("state")));
                }
                if (null != searchMap.get("recommend") && !"".equals(searchMap.get("recommend").toString().trim())) {
                    list.add(criteriaBuilder.equal(root.get("recommend").as(String.class), (String) searchMap.get("recommend")));
                }

                return criteriaBuilder.and((Predicate[]) list.toArray());
            }
        };
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    public List<Label> findSearch(Map searchMap) {
        Specification<Label> specification = createSpecification(searchMap);
        return labelDao.findAll(specification);
    }

    /**
     * 分页条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<Label> findSearch(Map searchMap, int page, int size) {
        Specification<Label> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page, size);
        return labelDao.findAll(specification, pageRequest);
    }

}
