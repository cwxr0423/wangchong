package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    /**
     * 添加
     *
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改
     *
     * @param label
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result update(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }


}
