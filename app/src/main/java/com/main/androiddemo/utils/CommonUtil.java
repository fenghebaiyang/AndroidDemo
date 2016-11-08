package com.main.androiddemo.utils;

import java.util.Collection;
import java.util.Map;

/**
 * <br/> Description:集合工具类
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/10/27 0027
 */
public class CommonUtil {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean notEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <K, V> boolean notEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }
}
