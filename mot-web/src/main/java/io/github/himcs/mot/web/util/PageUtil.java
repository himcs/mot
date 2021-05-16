package io.github.himcs.mot.web.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageUtil {
    public static <T> Page<T> instance(Long pageNum, Long perPage) {
        Page<T> page = new Page<>();
        page.setSize(perPage);
        page.setCurrent(pageNum);
        return page;
    }
}
