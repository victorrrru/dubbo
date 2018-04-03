package com.fww.collection;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Administrator
 * @date 2018/01/19 18:06
 */
public interface MyMapper<T> extends MySqlMapper<T>, Mapper<T> {
}
