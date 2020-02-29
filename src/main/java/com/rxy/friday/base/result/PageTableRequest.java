package com.rxy.friday.base.result;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 分页查询参数
 *
 * @author rxy
 * @date 2020/2/21  11:22  星期五
 **/
@Data
@Slf4j
public class PageTableRequest implements Serializable {
    private static final long serialVersionUID = 7328071045193618467L;
    private Integer page;//当前页
    private Integer limit;//每页显示条数
    private Integer offset;//开始页数

    /**
     * 计算当前页开始页数
     *
     * @Author: rxy
     * @Param: []
     * @return: {@link }
     */
    public void countOffset() {
//        log.info(page.toString());
        if (null == this.page || null == this.limit) {
            this.offset = 0;
            return;
        }
        this.offset = (this.page - 1) * this.limit;
    }

}
