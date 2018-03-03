package com.example.company.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 父类Mapper
 * @param <T>
 */
public interface SuperMapper<T> extends BaseMapper<T> {
    //可以写一些通用的方法
}
